package com.fjx.wechat.base.admin.action;

import com.fjx.common.action.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Zhuang on 2015/4/17.
 */
@RequestMapping("/admin/group")
@Controller
public class MsgGroupController extends BaseController {

    @RequestMapping(value = "")
    public String view(){
        return "/wechat/admin/msg_group/group";
    }
}
