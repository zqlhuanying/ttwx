package com.fjx.wechat.base.admin.action;

import com.fjx.common.action.BaseController;
import com.fjx.common.framework.base.dao.IBaseDao;
import com.fjx.common.framework.system.pagination.Pagination;
import com.fjx.common.utils.CommonUtils;
import com.fjx.common.utils.WebUtil;
import com.fjx.wechat.base.admin.entity.UserFundBindEntity;
import com.fjx.wechat.base.admin.entity.UserFundEntity;
import com.fjx.wechat.base.admin.service.UserFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * Created by Zhuang on 2015/4/24.
 */
@Controller
@RequestMapping(value = "/admin/fund")
public class FundController extends BaseController{

    @Autowired
    private UserFundService userFundService;

    @RequestMapping(value = "")
    public String viewFund(){
        return "/wechat/admin/fund/fund";
    }

    @RequestMapping(value = "bind")
    public String viewBind(){
        return "/wechat/admin/fund/searchBind";
    }

    @RequestMapping(value = "/pageListFund")
    @ResponseBody
    public Pagination<UserFundEntity> pageListFund(HttpServletRequest request, UserFundEntity userFundEntity){
        return userFundService.pageList(userFundEntity);
    }

    @RequestMapping(value = "/pageListBind")
    @ResponseBody
    public Pagination<UserFundBindEntity> pageListBind(HttpServletRequest request, UserFundBindEntity userFundBindEntity){
        return userFundService.pageList(userFundBindEntity);
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public Map<String, String> save(HttpServletRequest request){
        setErrorMsg(request,"保存失败");
        Map<String, String> reqMap = WebUtil.getRequestParams(request);
        UserFundEntity userFundEntity = new UserFundEntity();
        userFundEntity.setFund_account(reqMap.get("fund_account"));
        userFundEntity.setAmount(Float.parseFloat((reqMap.get("amount"))));
        userFundEntity.setTime(CommonUtils.date2String(new Date(), "yyyyMM"));
        userFundService.save(userFundEntity);
        return retSuccess();
    }

    @RequestMapping(value = "/deleteFund")
    @ResponseBody
    public Map<String, String> deleteFund(HttpServletRequest request, String ids){
        setErrorMsg(request,"删除失败");
        userFundService.delete(UserFundEntity.class, ids);
        return retSuccess();
    }

    @RequestMapping(value = "/deleteBind")
    @ResponseBody
    public Map<String, String> deleteBind(HttpServletRequest request, String ids){
        setErrorMsg(request,"删除失败");
        userFundService.delete(UserFundBindEntity.class, ids);
        return retSuccess();
    }
}
