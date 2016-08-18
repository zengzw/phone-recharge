package com.tsh.vas.po.bill;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name = "push_order_history")
public class PushOrderHistoryPo implements Serializable{

    private static final long serialVersionUID = 1L;

    /**  主键*/
    private Long id;
    /**  推送状态：0：失败，1：成功*/
    private Long pushStatus;
    /**  推送时间*/
    private Date pushTime;
    /**  外部订单号*/
    private String tradingCode;
    /**  推送参数*/
    private String pushParams;
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id =id;
    }
    @Column(name = "push_status")
    public Long getPushStatus() {
        return pushStatus;
    }

    public void setPushStatus(Long pushStatus) {
        this.pushStatus =pushStatus;
    }
    @Column(name = "push_time")
    public Date getPushTime() {
        return pushTime;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime =pushTime;
    }
    @Column(name = "trading_code")
    public String getTradingCode() {
        return tradingCode;
    }

    public void setTradingCode(String tradingCode) {
        this.tradingCode =tradingCode;
    }
    @Column(name = "push_params")
    public String getPushParams() {
        return pushParams;
    }

    public void setPushParams(String pushParams) {
        this.pushParams =pushParams;
    }

}
