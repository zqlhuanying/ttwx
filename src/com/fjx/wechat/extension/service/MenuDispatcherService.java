package com.fjx.wechat.extension.service;

import com.fjx.wechat.base.admin.entity.WechatMenuEntity;
import com.fjx.wechat.base.admin.service.WechatMenuService;
import com.fjx.wechat.mysdk.context.WechatContext;
import com.fjx.wechat.mysdk.process.ext.MenuExtService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Zhuang on 2015/4/20.
 * 根据菜单名转发
 */
@Service
public class MenuDispatcherService {

    @Autowired
    private WechatMenuService wechatMenuService;
    @Autowired
    private SearchFundService searchFundService;

    public String menuDispatch(){
        String respMessage = "";       //消息回应内容
        MenuExtService menuExtService = null;

        Map<String, String> reqMap = WechatContext.getWechatPostMap();
        String menu_key = reqMap.get("EventKey");
        if(menu_key != null && StringUtils.isNotBlank(menu_key)){
            String hql = "from WechatMenuEntity w where w.menu_key = ?";
            WechatMenuEntity wechatMenuEntity = wechatMenuService.findOneByHql(hql, "key_" + menu_key);
            if("查询公积金".equals(wechatMenuEntity.getName())){
                menuExtService = searchFundService;
            }
        }
        if(menuExtService != null) respMessage = menuExtService.extProcess();
        return respMessage;
    }
}
