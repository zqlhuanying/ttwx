package com.fjx.wechat.base.admin.service;

import com.fjx.common.framework.base.service.impl.BaseAbstractService;
import com.fjx.wechat.base.admin.entity.UserFundBindEntity;
import com.fjx.wechat.base.admin.entity.UserFundEntity;
import com.fjx.wechat.base.admin.entity.WechatUserEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * Created by Zhuang on 2015/4/22.
 */
@Service
public class UserFundServiceImpl extends BaseAbstractService<UserFundBindEntity> implements UserFundService {

    /**
     * 查询公积金
     */
    @Override
    public String loadUserFundByEntity(WechatUserEntity wechatUserEntity){
        String hql = "from UserFundBindEntity u where u.wechatUserEntity = ?";
        UserFundBindEntity userFundBindEntity = findOneByHql(hql, wechatUserEntity);
        hql = "from UserFundEntity u where u.userFundBindEntity = ? order by u.time desc";
        UserFundEntity userFundEntity = findOneByHql(hql, userFundBindEntity);
        if(userFundEntity == null || userFundEntity.getAmount() == null)
            return "0";
        return String.valueOf(userFundEntity.getAmount());
    }

    /**
     * 查询公积金
     */
    @Override
    public String loadUserFundByOpenId(String openid){
        String hql = "from WechatUserEntity w where w.openid = ?";
        WechatUserEntity wechatUserEntity = findOneByHql(hql, openid);
        return loadUserFundByEntity(wechatUserEntity);
    }

    /**
     * 判断是否绑定手机号
     * 若用户手机号不存在，则视为未绑定
     * @return
     */
    @Override
    public Boolean isBindPhone(WechatUserEntity wechatUserEntity){
        String hql = "from UserFundBindEntity u where u.wechatUserEntity = ?";
        UserFundBindEntity userFundBindEntity = findOneByHql(hql, wechatUserEntity);
        if(userFundBindEntity != null && StringUtils.isNotBlank(userFundBindEntity.getPhone()))
            return true;
        return false;
    }

    /**
     * 判断是否绑定手机号
     * 若用户手机号不存在，则视为未绑定
     * @return
     */
    @Override
    public Boolean isBindPhone(String openid){
        String hql = "from WechatUserEntity w where w.openid = ?";
        WechatUserEntity wechatUserEntity = findOneByHql(hql, openid);
        return isBindPhone(wechatUserEntity);
    }
}
