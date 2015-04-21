package com.fjx.wechat.base.admin.service;

import com.fjx.common.framework.base.service.IBaseAbstractService;
import com.fjx.common.framework.system.exception.MyException;
import com.fjx.wechat.base.admin.entity.*;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Zhuang on 2015/4/18.
 */
public interface MsgGroupService extends IBaseAbstractService<RespMsgActionEntity>{
    /**
     * 执行群发消息
     * @param json 群发消息内容
     * @param sysUser 用户，也即公众账号
     * @param mark 1：根据用户组群发；0：根据OpenId群发
     */
    public void send(SysUserEntity sysUser, String json, String mark) throws MyException;

    /**
     * 将Map转换成符合群发消息要求的JSON数据
     * @param reqMap
     */
    public String reqMap2Json(SysUserEntity sysUser, Map<String, String> reqMap) throws IOException;

    /**
     * 保存群发消息内容，(原)消息内容都被保存在表wechat_req_msg_log，通过AOP的方式进行保存
     */
    public void save(String jsonStr, WechatPublicAccountEntity wechatPublicAccountEntity);
}
