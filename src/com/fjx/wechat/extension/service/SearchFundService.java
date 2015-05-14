package com.fjx.wechat.extension.service;

import com.fjx.wechat.base.admin.entity.WechatUserEntity;
import com.fjx.wechat.base.admin.service.UserFundService;
import com.fjx.wechat.base.admin.service.WechatUserService;
import com.fjx.wechat.config.AppConfig;
import com.fjx.wechat.mysdk.beans.resp.RespTextMessage;
import com.fjx.wechat.mysdk.constants.WechatRespMsgtypeConstants;
import com.fjx.wechat.mysdk.context.WechatContext;
import com.fjx.wechat.mysdk.process.ext.MenuExtService;
import com.fjx.wechat.mysdk.tools.MessageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * Created by Zhuang on 2015/4/20.
 */
@Service
public class SearchFundService implements MenuExtService {

    @Autowired
    private UserFundService userFundService;


    @Override
    public String extProcess(){
        Map<String, String> reqMap = WechatContext.getWechatPostMap();
        // 发送方帐号（open_id）
        String fromUserName = reqMap.get("FromUserName");
        if(fromUserName == null || StringUtils.isBlank(fromUserName)){
            return "";
        }
        // 构建回复消息
        RespTextMessage respTextMessage = new RespTextMessage();
        respTextMessage.setToUserName(fromUserName);
        respTextMessage.setFromUserName(reqMap.get("ToUserName"));
        respTextMessage.setCreateTime(new Date().getTime());
        respTextMessage.setMsgType(WechatRespMsgtypeConstants.RESP_MESSAGE_TYPE_TEXT);
        respTextMessage.setFuncFlag(0);

        // 若用户手机号不存在，则视为未绑定
        if( !userFundService.isBindPhone(fromUserName) ){
            respTextMessage.setContent("发现您未绑定手机号码，<a href=\"" + AppConfig.DOMAIN_PAGE + "/admin/extmenu/bindPhone?id=" + fromUserName + "\">点击这里，进行绑定</a>");
        }else {
            respTextMessage.setContent("您的公积金为" + userFundService.loadUserFundByOpenId(fromUserName) + "元");
        }

        return MessageUtil.textMessageToXml(respTextMessage);
    }
}
