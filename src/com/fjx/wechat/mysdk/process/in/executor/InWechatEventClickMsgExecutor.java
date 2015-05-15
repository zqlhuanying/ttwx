package com.fjx.wechat.mysdk.process.in.executor;

import com.fjx.wechat.base.admin.entity.RespMsgActionEntity;
import com.fjx.wechat.base.admin.service.RespMsgActionService;
import com.fjx.wechat.config.MsgTemplateConstants;
import com.fjx.wechat.extension.service.MenuDispatcherService;
import com.fjx.wechat.mysdk.constants.WechatReqEventConstants;
import com.fjx.wechat.mysdk.constants.WechatReqMsgtypeConstants;
import com.fjx.wechat.mysdk.context.WechatContext;
import com.fjx.wechat.mysdk.process.ext.MenuExtService;
import com.fjx.wechat.mysdk.tools.NameTool;
import com.fjx.wechat.mysdk.beans.req.ReqEventMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 菜单点击消息处理器
 *
 * @author fengjx xd-fjx@qq.com
 * @date 2014年9月11日
 */
public class InWechatEventClickMsgExecutor extends InServiceExecutor {

    @Autowired
    private RespMsgActionService respMsgActionService;
    @Autowired
    private MenuDispatcherService menuDispatcherService;

    @Override
    public String execute() throws Exception {
        ReqEventMessage eventMessage = new ReqEventMessage(WechatContext.getWechatPostMap());
        logger.info("进入菜单点击消息处理器fromUserName=" + eventMessage.getFromUserName());
        RespMsgActionEntity actionEntity = respMsgActionService.loadMsgAction(null, eventMessage.getMsgType(), eventMessage.getEvent(), eventMessage.getEventKey(), WechatContext.getPublicAccount().getSysUser());
        if (actionEntity == null) {
            String res = menuDispatcherService.menuDispatch();
            if (StringUtils.isNotBlank(res)) {
                return res;
            }
            //返回默认回复消息
            actionEntity = msgActionService.loadMsgAction(MsgTemplateConstants.WECHAT_DEFAULT_MSG, null, null, null, WechatContext.getPublicAccount().getSysUser());
        }
        return doAction(actionEntity);
    }

    @Override
    public String getExecutorName() {
        return NameTool.buildInServiceName(WechatReqMsgtypeConstants.REQ_MSG_TYPE_EVENT,
                WechatReqEventConstants.EVENT_TYPE_CLICK);
    }

}
