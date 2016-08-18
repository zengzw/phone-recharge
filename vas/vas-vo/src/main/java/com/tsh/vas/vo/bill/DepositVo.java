package com.tsh.vas.vo.bill;

import java.io.Serializable;
import java.util.Date;

public class DepositVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;
    /**  */
    private String depositCode;

    /** 外部订单ID */
    private Long tradingId;

    /** 外部订单CODE */
    private String tradingCode;

    /** 权重 */
    private Double weight;

    /** 接口供应商ID */
    private Long supplierId;

    /** 供应商编码 */
    private String supplierCode;

    /** 供应商名称 */
    private String supplierName;

    /** 手机号码 */
    private String mobile;

    /** 手机号码所属供应商（移动 联通 电信） */
    private Long numberType;

    /** 提交金额 */
    private Double postAmount;

    /** 调用接口后返回的实付金额 */
    private Double payAmount;

    /** 提交时间 */
    private Date postTime;

    /** 充值状态 */
    private Long state;

    /** 交易来源 */
    private Long source;

    /** 来源编码 */
    private String sourceCode;

    /** 交易类型 */
    private Long type;

    /** 备注 */
    private String remarks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepositCode() {
        return depositCode;
    }

    public void setDepositCode(String depositCode) {
        this.depositCode = depositCode;
    }

    public Long getTradingId() {
        return tradingId;
    }

    public void setTradingId(Long tradingId) {
        this.tradingId = tradingId;
    }

    public String getTradingCode() {
        return tradingCode;
    }

    public void setTradingCode(String tradingCode) {
        this.tradingCode = tradingCode;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getNumberType() {
        return numberType;
    }

    public void setNumberType(Long numberType) {
        this.numberType = numberType;
    }

    public Double getPostAmount() {
        return postAmount;
    }

    public void setPostAmount(Double postAmount) {
        this.postAmount = postAmount;
    }

    public Double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Double payAmount) {
        this.payAmount = payAmount;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public Long getSource() {
        return source;
    }

    public void setSource(Long source) {
        this.source = source;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
