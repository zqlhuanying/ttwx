package com.fjx.wechat.base.admin.action;

import com.fjx.common.action.BaseController;
import com.fjx.common.utils.WebUtil;
import com.fjx.wechat.base.admin.entity.UserFundBindEntity;
import com.fjx.wechat.base.admin.entity.UserFundEntity;
import com.fjx.wechat.base.admin.entity.WechatUserEntity;
import com.fjx.wechat.base.admin.service.UserFundService;
import com.fjx.wechat.base.admin.service.WechatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zhuang on 2015/4/22.
 */
@Controller
@RequestMapping(value = "/admin/extmenu")
public class ExtMenuController extends BaseController{

    @Autowired
    private WechatUserService wechatUserService;
    @Autowired
    private UserFundService userFundService;

    @RequestMapping(value = "/bindAccount")
    public String viewBind(){
        return "/wechat/admin/extmenu/bindAccount";
    }

    @RequestMapping(value = "/bindok")
    public String viewBindOk(){
        return "/wechat/admin/extmenu/bindOk";
    }

    @RequestMapping(value = "/bind")
    @ResponseBody
    public Map<String, String> bind(HttpServletRequest request){
        Map<String, String> reqMap = WebUtil.getRequestParams(request);
        String openid = reqMap.get("id");
        String fund_account = reqMap.get("fund_account");
        String hql = "from UserFundEntity u where u.fund_account = ?";
        UserFundEntity userFundEntity = (UserFundEntity)userFundService.findOneByHql(hql, fund_account);
        // 若此用户已绑定手机和公积金账号，或此用户的信息暂未录入到信息库中，则始终显示绑定失败。
        if(userFundService.isBindAccount(openid) || userFundEntity == null) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("code","0");
            return map;
        }
        hql = "from WechatUserEntity w where w.openid = ?";
        WechatUserEntity wechatUserEntity = wechatUserService.findOneByHql(hql, openid);
        UserFundBindEntity userFundBindEntity = new UserFundBindEntity();
        userFundBindEntity.setPhone(reqMap.get("phone"));
        userFundBindEntity.setFund_account(reqMap.get("fund_account"));
        userFundBindEntity.setWechatUserEntity(wechatUserEntity);
        userFundService.save(userFundBindEntity);
        return retSuccess();
    }
}
