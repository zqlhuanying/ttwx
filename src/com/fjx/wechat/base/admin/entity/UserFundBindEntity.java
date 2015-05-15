package com.fjx.wechat.base.admin.entity;

import com.fjx.common.bean.ToStringBase;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Zhuang on 2015/4/22.
 *
用户公积金绑定表
------------------------------
id                PK          //id
userid            String(100) //普通用户的标识，对当前公众号唯一
phone             String(11)  //手机号码
fund_account      String(100) //公积金账号
 */


@Entity
@Table(name = "user_fund_bind")
public class UserFundBindEntity extends ToStringBase{

    private String id;
    private String phone;
    private String fund_account;

    private WechatUserEntity wechatUserEntity;

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy="uuid")
    @Column(length=32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @OneToOne
    @JoinColumn(name = "userid", nullable = false)
    public WechatUserEntity getWechatUserEntity() {
        return wechatUserEntity;
    }

    public void setWechatUserEntity(WechatUserEntity wechatUserEntity) {
        this.wechatUserEntity = wechatUserEntity;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFund_account() {
        return fund_account;
    }

    public void setFund_account(String fund_account) {
        this.fund_account = fund_account;
    }

}
