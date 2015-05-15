package com.fjx.wechat.base.admin.service;

import com.fjx.common.framework.base.service.impl.BaseAbstractService;
import com.fjx.common.framework.system.pagination.Pagination;
import com.fjx.common.utils.BeanUtil;
import com.fjx.wechat.base.admin.entity.UserFundBindEntity;
import com.fjx.wechat.base.admin.entity.UserFundEntity;
import com.fjx.wechat.base.admin.entity.WechatUserEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhuang on 2015/4/22.
 */
@Service
public class UserFundServiceImpl extends BaseAbstractService implements UserFundService {

    /**
     * 查询公积金
     */
    @Override
    public String loadUserFundByEntity(WechatUserEntity wechatUserEntity){
        String hql = "from UserFundBindEntity u where u.wechatUserEntity = ?";
        UserFundBindEntity userFundBindEntity = (UserFundBindEntity)findOneByHql(hql, wechatUserEntity);
        hql = "from UserFundEntity u where u.fund_account = ? order by u.time desc";
        UserFundEntity userFundEntity = (UserFundEntity)findOneByHql(hql, userFundBindEntity.getFund_account());
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
        WechatUserEntity wechatUserEntity = (WechatUserEntity)findOneByHql(hql, openid);
        return loadUserFundByEntity(wechatUserEntity);
    }

    /**
     * 判断是否绑定公积金账号
     * 若公积金账号不存在，则视为未绑定
     * @return
     */
    @Override
    public Boolean isBindAccount(WechatUserEntity wechatUserEntity){
        String hql = "from UserFundBindEntity u where u.wechatUserEntity = ?";
        UserFundBindEntity userFundBindEntity = (UserFundBindEntity)findOneByHql(hql, wechatUserEntity);
        if(userFundBindEntity != null && StringUtils.isNotBlank(userFundBindEntity.getFund_account()))
            return true;
        return false;
    }

    /**
     * 判断是否绑定公积金账号
     * 若公积金账号不存在，则视为未绑定
     * @return
     */
    @Override
    public Boolean isBindAccount(String openid){
        String hql = "from WechatUserEntity w where w.openid = ?";
        WechatUserEntity wechatUserEntity = (WechatUserEntity)findOneByHql(hql, openid);
        return isBindAccount(wechatUserEntity);
    }

    @Override
    public Pagination<UserFundEntity> pageList(UserFundEntity userFundEntity){
        List<Object> params = new ArrayList<Object>();
        StringBuilder hql = new StringBuilder();
        hql.append("from UserFundEntity u where ");
        if(StringUtils.isNotBlank(userFundEntity.getFund_account())){
            hql.append("and u.fund_account = ?");
            params.add(userFundEntity.getFund_account());
        }
        String queryHql = hql.toString();
        // 若params中没有参数，则表示全表查询
        if(params.size() == 0){
            queryHql = queryHql.replace("where", "");
        } else {
            queryHql = queryHql.replaceFirst("and", "");
        }
        //System.out.println(queryHql);
        return pageByHql(queryHql, params);
    }

    @Override
    public Pagination<UserFundBindEntity> pageList(UserFundBindEntity userFundBindEntity){
        List<Object> params = new ArrayList<Object>();
        StringBuilder hql = new StringBuilder();
        hql.append("from UserFundBindEntity u where ");
        if(StringUtils.isNotBlank(userFundBindEntity.getFund_account())){
            hql.append("and u.fund_account = ?");
            params.add(userFundBindEntity.getFund_account());
        }
        String queryHql = hql.toString();
        // 若params中没有参数，则表示全表查询
        if(params.size() == 0){
            queryHql = queryHql.replace("where", "");
        } else {
            queryHql = queryHql.replaceFirst("and", "");
        }
        //System.out.println(queryHql);
        return pageByHql(queryHql, params);
    }

}
