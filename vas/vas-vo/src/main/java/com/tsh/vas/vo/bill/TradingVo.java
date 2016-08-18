package com.tsh.vas.vo.bill;

import java.io.Serializable;
import java.util.Date;



public class TradingVo implements Serializable{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;
    /**  交易编码*/
    private String tradingCode;

    /**  网点ID*/
    private Long shopId;

    /**  网点名称*/
    private String shopName;

    /**  县域ID*/
    private Long areaId;

    /**  县域名称*/
    private String areaName;

    /**  订单ID*/
    private Long orderId;

    /**  订单编码*/
    private String orderCode;

    /**  接口供应商ID*/
    private Long supplierId;
    /**  商品ID*/
    private Long goodsId;
    /**  skuId*/
    private Long skuId;

    /**  供应商编码*/
    private String supplierCode;

    /**  供应商名称*/
    private String supplierName;

    /**  手机号码*/
    private String mobile;

    /**  手机号码所属供应商（移动 联通 电信）*/
    private Long numberType;

    /**  提交金额*/
    private Double postAmount;

    /**  调用接口后返回的实付金额*/
    private Double payAmount;

    /**  提交时间*/
    private Date postTime;

    /**  充值状态*/
    private Long state;

    /**  交易来源*/
    private Long source;

    /**  来源编码*/
    private String sourceCode;

    /**  交易类型*/
    private Long type;

    /**  备注*/
    private String remarks;


    /**
     * 推送状态
     */
    private Integer pushStatus;



    /**
     * 第三方供应商订单号
     */
    private String depositNo;

    public String getDepositNo() {
        return depositNo;
    }

    public void setDepositNo(String depositNo) {
        this.depositNo = depositNo;
    }
    
    public Integer getPushStatus() {
        return pushStatus;
    }

    public void setPushStatus(Integer pushStatus) {
        this.pushStatus = pushStatus;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id =id;
    }
    public String getTradingCode() {
        return tradingCode;
    }

    public void setTradingCode(String tradingCode) {
        this.tradingCode =tradingCode;
    }
    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId =shopId;
    }
    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName =shopName;
    }
    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId =areaId;
    }
    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName =areaName;
    }
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId =orderId;
    }
    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode =orderCode;
    }
    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId =supplierId;
    }
    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode =supplierCode;
    }
    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName =supplierName;
    }
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile =mobile;
    }
    public Long getNumberType() {
        return numberType;
    }

    public void setNumberType(Long numberType) {
        this.numberType =numberType;
    }
    public Double getPostAmount() {
        return postAmount;
    }

    public void setPostAmount(Double postAmount) {
        this.postAmount =postAmount;
    }
    public Double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Double payAmount) {
        this.payAmount =payAmount;
    }
    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime =postTime;
    }
    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state =state;
    }
    public Long getSource() {
        return source;
    }

    public void setSource(Long source) {
        this.source =source;
    }
    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode =sourceCode;
    }
    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type =type;
    }
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks =remarks;
    }

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}
}
