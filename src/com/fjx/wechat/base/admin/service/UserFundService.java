package com.fjx.wechat.base.admin.service;

import com.fjx.common.framework.base.service.IBaseAbstractService;
import com.fjx.common.framework.system.pagination.Pagination;
import com.fjx.wechat.base.admin.entity.UserFundBindEntity;
import com.fjx.wechat.base.admin.entity.UserFundEntity;
import com.fjx.wechat.base.admin.entity.WechatUserEntity;

/**
 * Created by Zhuang on 2015/4/22.
 */
public interface UserFundService extends IBaseAbstractService{

    /**
     * 查询公积金
     */
    public String loadUserFundByEntity(WechatUserEntity wechatUserEntity);

    /**
     * 查询公积金
     */
    public String loadUserFundByOpenId(String openid);

    /**
     * 判断是否绑定手机号
     * @return
     */
    public Boolean isBindPhone(WechatUserEntity wechatUserEntity);

    /**
     * 判断是否绑定手机号
     * @return
     */
    public Boolean isBindPhone(String openid);

    /**
     * 分页查询
     */
    public Pagination<UserFundEntity> pageList(UserFundEntity userFundEntity);

}
