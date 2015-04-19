package com.fjx.wechat.mysdk.api;

import com.fjx.wechat.mysdk.tools.HttpUtil;

/**
 * Created by Administrator on 2015/4/18.
 */
public class GroupClient extends AbstractClient {

    private static String toOpenId =  "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=";
    private static String toUserGroup = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=";

    /**
     * 根据OpenId群发
     */
    public ApiResult toOpenId(String jsonStr){
        AccessToken accessToken = getAccessToken();
        String jsonResult = HttpUtil.post(toOpenId + accessToken.getAccessToken(), jsonStr);
        return proceResult(jsonResult);
    }

    /**
     * 根据用户组群发
     */
    public ApiResult toUserGroup(String jsonStr){
        AccessToken accessToken = getAccessToken();
        String jsonResult = HttpUtil.post(toUserGroup + accessToken.getAccessToken(), jsonStr);
        return proceResult(jsonResult);
    }
}
