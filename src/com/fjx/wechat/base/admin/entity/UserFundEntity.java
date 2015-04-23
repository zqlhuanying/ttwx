package com.fjx.wechat.base.admin.entity;

import com.fjx.common.bean.ToStringBase;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Zhuang on 2015/4/23.
 *
 用户公积金表
 ------------------------------
 id                PK          //id
 fund_account      String(100) //公积金账号
 amount            Float       //公积金
 */
@Entity
@Table(name = "user_fund")
public class UserFundEntity extends ToStringBase{

    private String id;
    private Float  amount;
    private String time;
    private UserFundBindEntity userFundBindEntity;

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
    @JoinColumn(name = "fund_account", nullable = false)
    public UserFundBindEntity getUserFundBindEntity() {
        return userFundBindEntity;
    }

    public void setUserFundBindEntity(UserFundBindEntity userFundBindEntity) {
        this.userFundBindEntity = userFundBindEntity;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
