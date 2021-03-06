package com.tsh.vas.po.bill;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "deposit")
public class DepositPo implements Serializable {

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
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "deposit_code")
	public String getDepositCode() {
		return depositCode;
	}

	public void setDepositCode(String depositCode) {
		this.depositCode = depositCode;
	}

	@Column(name = "trading_id")
	public Long getTradingId() {
		return tradingId;
	}

	public void setTradingId(Long tradingId) {
		this.tradingId = tradingId;
	}

	@Column(name = "trading_code")
	public String getTradingCode() {
		return tradingCode;
	}

	public void setTradingCode(String tradingCode) {
		this.tradingCode = tradingCode;
	}

	@Column(name = "weight")
	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	@Column(name = "supplier_id")
	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	@Column(name = "supplier_code")
	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	@Column(name = "supplier_name")
	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	@Column(name = "mobile")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "number_type")
	public Long getNumberType() {
		return numberType;
	}

	public void setNumberType(Long numberType) {
		this.numberType = numberType;
	}

	@Column(name = "post_amount")
	public Double getPostAmount() {
		return postAmount;
	}

	public void setPostAmount(Double postAmount) {
		this.postAmount = postAmount;
	}

	@Column(name = "pay_amount")
	public Double getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(Double payAmount) {
		this.payAmount = payAmount;
	}

	@Column(name = "post_time")
	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	@Column(name = "state")
	public Long getState() {
		return state;
	}

	public void setState(Long state) {
		this.state = state;
	}

	@Column(name = "source")
	public Long getSource() {
		return source;
	}

	public void setSource(Long source) {
		this.source = source;
	}

	@Column(name = "source_code")
	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	@Column(name = "type")
	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	@Column(name = "remarks")
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
