package com.fjx.wechat.base.admin.action;

import com.fjx.common.action.BaseController;
import com.fjx.common.framework.system.exception.MyException;
import com.fjx.common.utils.WebUtil;
import com.fjx.wechat.base.admin.entity.SysUserEntity;
import com.fjx.wechat.base.admin.service.MsgGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Zhuang on 2015/4/17.
 */
@RequestMapping("/admin/group")
@Controller
public class MsgGroupController extends BaseController {

    @Autowired
    private MsgGroupService msgGroupService;

    @RequestMapping(value = "")
    public String view() {
        return "/wechat/admin/msg_group/group";
    }

    @RequestMapping(value = "/send")
    @ResponseBody
    public Map<String, String> send(HttpServletRequest request) throws MyException, IOException {
        Map<String, String> reqMap = WebUtil.getRequestParams(request);
        SysUserEntity sysUser = getLoginSysUser(request);
        //  构建JSON数据
        String jsonStr = null;
        try {
            jsonStr = msgGroupService.reqMap2Json(sysUser, reqMap);
        } catch (IOException e) {
            setErrorMsg(request, e.getMessage());
            throw e;
        }

        /*String json = "{" +
                "'touser':[" +
                    "oRcsIuAojZ80QiNzMwdsc8Iph2qU" +
                        "]," +
        "'msgtype': 'text'," +
                "'text': { 'content': " + "'" + reqMap.get("materiaContent") + "'" + "}" +
        "}";
        System.out.println(json);*/

        // 执行群发
        try {
            if ("0".equals(reqMap.get("is_to_all"))) {
                msgGroupService.send(getLoginSysUser(request), jsonStr, "0");
            } else {
                msgGroupService.send(getLoginSysUser(request), jsonStr, "1");
            }
        } catch (MyException e) {
            setErrorMsg(request, e.getMessage());
            throw e;
        }

        //保存群发消息
        msgGroupService.save(jsonStr, sysUser.getWechatPublicAccount());

        return retSuccess();
    }
}
