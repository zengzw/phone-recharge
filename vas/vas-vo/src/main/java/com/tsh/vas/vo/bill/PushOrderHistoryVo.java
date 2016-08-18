package com.tsh.vas.vo.bill;

import java.io.Serializable;
import java.util.Date;


public class PushOrderHistoryVo implements Serializable{
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




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id =id;
    }
    public Long getPushStatus() {
        return pushStatus;
    }

    public void setPushStatus(Long pushStatus) {
        this.pushStatus =pushStatus;
    }
    public Date getPushTime() {
        return pushTime;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime =pushTime;
    }
    public String getTradingCode() {
        return tradingCode;
    }

    public void setTradingCode(String tradingCode) {
        this.tradingCode =tradingCode;
    }
    public String getPushParams() {
        return pushParams;
    }

    public void setPushParams(String pushParams) {
        this.pushParams =pushParams;
    }
}
