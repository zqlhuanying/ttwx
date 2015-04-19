package com.fjx.wechat.mysdk.process.in.aop;

import java.util.Date;
import java.util.Map;

import com.fjx.common.framework.system.exception.MyRuntimeException;
import com.fjx.wechat.mysdk.constants.WechatReqEventConstants;
import com.fjx.wechat.mysdk.constants.WechatReqMsgtypeConstants;
import com.fjx.wechat.mysdk.context.WechatContext;
import com.fjx.wechat.mysdk.tools.MessageUtil;
import com.fjx.wechat.mysdk.tools.WeChatUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.fjx.common.utils.CommonUtils;
import com.fjx.wechat.base.admin.entity.ReqMsgLogEntoty;
import com.fjx.wechat.base.admin.service.ReqMsgLogService;


/**
 * 日志记录切面逻辑
 * 统计微信发送过来的请求
 * @author fengjx xd-fjx@qq.com
 * @date 2014年10月28日
 */
public class WechatLogAop {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private ReqMsgLogService reqMsgLogService;
	
	public void addReqLog(){
		
		logger.info("记录微信请求发送数据日志addReqLog begin...");
		try {
			//微信发送的参数
			Map<String, String> requestMap = WechatContext.getWechatPostMap();
			// 消息类型
			String msgType = requestMap.get("MsgType");
			ReqMsgLogEntoty reqMsgLog = new ReqMsgLogEntoty();
			if(StringUtils.isNotBlank(msgType) && msgType.equals(WechatReqMsgtypeConstants.REQ_MSG_TYPE_EVENT)){//如果是事件类型
				String event_type = requestMap.get("Event");
                if((WechatReqEventConstants.EVENT_TYPE_MASSSENDJOBFINISH).equals(event_type)){
                    reqMsgLog = WechatContext.getReqMsgLog();
                }
                reqMsgLog.setEvent_type(event_type);
			}
            reqMsgLog.setReq_type(msgType);
            reqMsgLog.setMsg_id(Long.parseLong(StringUtils.defaultString(requestMap.get("MsgId"), "0")));
            reqMsgLog.setIn_time(new Date());
            // 若微信返回的是群发消息，则需要重新设置
            if((WechatReqEventConstants.EVENT_TYPE_MASSSENDJOBFINISH).equals(reqMsgLog.getEvent_type())){
                reqMsgLog.setTo_user_name(requestMap.get("FromUserName"));
                reqMsgLog.setFrom_user_name(requestMap.get("ToUserName"));
                reqMsgLog.setResp_xml(requestMap.get("xml"));
                reqMsgLog.setResp_time(new Date());
                reqMsgLogService.update(reqMsgLog);
            } else {
                reqMsgLog.setTo_user_name(requestMap.get("ToUserName"));
                reqMsgLog.setFrom_user_name(requestMap.get("FromUserName"));
                reqMsgLog.setCreate_time(CommonUtils.string2Date(MessageUtil.formatCreateTime(requestMap.get("CreateTime")), "yyyy-MM-dd HH:mm:ss"));
                reqMsgLog.setReq_xml(requestMap.get("xml"));
                reqMsgLog.setWechatPublicAccount(WechatContext.getPublicAccount());
                reqMsgLogService.save(reqMsgLog);
                WechatContext.setReqMsgLog(reqMsgLog);
            }
		} catch (Exception e) {
			logger.error("记录微信请求发送数据日志出现异常", e);
			throw new MyRuntimeException("记录微信请求发送数据日志出现异常",e);
		}
	}
	
	public void addRespLog(Object returnValue){
		logger.info("记录微信请求发送数据日志addRespLog begin...");
		try {
			//微信发送的参数
			ReqMsgLogEntoty reqMsgLog = WechatContext.getReqMsgLog();
            if( ! (WechatReqEventConstants.EVENT_TYPE_MASSSENDJOBFINISH).equals(reqMsgLog.getEvent_type())){
                reqMsgLog.setResp_xml(null == returnValue ? "":returnValue.toString());
                reqMsgLog.setResp_time(new Date());
                reqMsgLogService.update(reqMsgLog);
            }
		} catch (Exception e) {
			logger.error("记录微信请求响应数据日志出现异常", e);
			throw new MyRuntimeException("记录微信请求响应数据日志出现异常",e);
		}
	}
	
}
