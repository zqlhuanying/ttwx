package com.fjx.wechat.base.admin.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fjx.common.framework.base.service.impl.BaseAbstractService;
import com.fjx.common.framework.system.exception.MyException;
import com.fjx.wechat.base.admin.entity.*;
import com.fjx.wechat.mysdk.api.ApiResult;
import com.fjx.wechat.mysdk.api.ClientFactory;
import com.fjx.wechat.mysdk.api.GroupClient;
import com.fjx.wechat.mysdk.context.WechatContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.*;


/**
 * Created by Zhuang on 2015/4/18.
 */
@Service
public class MsgGroupServiceImpl extends BaseAbstractService<RespMsgActionEntity> implements MsgGroupService {

    @Autowired
    private WechatUserService wechatUserService;
    @Autowired
    private ReqMsgLogService reqMsgLogService;

    @Override
    public void send(SysUserEntity sysUser, String jsonStr, String mark) throws MyException{
        ApiResult result = null;
        String appid = sysUser.getWechatPublicAccount().getApp_id();
        String appsecret = sysUser.getWechatPublicAccount().getApp_secret();
        String token = sysUser.getWechatPublicAccount().getToken();
        GroupClient groupClient = ClientFactory.createGroupClient(appid, appsecret, token);
        if("1".equals(mark))  result = groupClient.toUserGroup(jsonStr);
        if("0".equals(mark))  result = groupClient.toOpenId(jsonStr);
        if(!result.isSucceed()){
            //System.out.println("群发消息失败：errcode="+result.getErrorCode()+" and errmsg="+result.getErrorMsg());
            throw new MyException("群发消息失败：errcode="+result.getErrorCode()+" and errmsg="+result.getErrorMsg());
        }
    }

    @Override
    public String reqMap2Json(SysUserEntity sysUser,Map<String, String> reqMap) throws IOException{
        Map<String, Object> suitMap = new HashMap<String, Object>();
        String jsonStr = new String();
        if("0".equals(reqMap.get("is_to_all"))){
            WechatUserEntity wechatUserEntity = new WechatUserEntity();
            List<WechatUserEntity> lists = wechatUserService.getList(wechatUserEntity, null, sysUser.getWechatPublicAccount());
            List<String> arrayList = new ArrayList<String>();
            for (WechatUserEntity w : lists){
                arrayList.add(w.getOpenid());
            }

            suitMap.put("touser", arrayList.toArray());
            Map map = new HashMap();
            if("text".equals(reqMap.get("req_send_type"))){
                map.put(reqMap.get("msg_group_type"),reqMap.get("materiaContent"));
            } else {
                map.put(reqMap.get("msg_group_type"),reqMap.get("materiaId"));
            }
            suitMap.put(reqMap.get("req_send_type"), map);
            suitMap.put("msgtype", reqMap.get("req_send_type"));

            jsonStr = new ObjectMapper().writeValueAsString(suitMap);
        } else {
            //todo
        }
        return jsonStr;
    }

    public void save(String jsonStr, WechatPublicAccountEntity wechatPublicAccountEntity){
        ReqMsgLogEntoty reqMsgLogEntoty = new ReqMsgLogEntoty();
        Date now = new Date();
        reqMsgLogEntoty.setCreate_time(now);
        reqMsgLogEntoty.setReq_xml(jsonStr);
        reqMsgLogEntoty.setWechatPublicAccount(wechatPublicAccountEntity);
        reqMsgLogService.save(reqMsgLogEntoty);
        WechatContext.setReqMsgLog(reqMsgLogEntoty);
    }
}
