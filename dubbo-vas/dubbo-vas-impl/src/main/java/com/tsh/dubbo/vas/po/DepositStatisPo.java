package com.tsh.dubbo.vas.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "deposit_statis")
public class DepositStatisPo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
		/**  */
	private Long id;
		/**  接口供应商ID*/
	private Long supplierId;
		/**  供应商编码*/
	private String supplierCode;
		/**  供应商名称*/
	private String supplierName;
		/**  调用成功次数*/
	private Long succCount;
		/**  失败次数*/
	private Long failCount;
		/**  */
	private Long source;
		/**  来源编码*/
	private String sourceCode;
		
			@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id =id;
	}
				@Column(name = "supplier_id")
	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId =supplierId;
	}
				@Column(name = "supplier_code")
	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode =supplierCode;
	}
				@Column(name = "supplier_name")
	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName =supplierName;
	}
				@Column(name = "succ_count")
	public Long getSuccCount() {
		return succCount;
	}

	public void setSuccCount(Long succCount) {
		this.succCount =succCount;
	}
				@Column(name = "fail_count")
	public Long getFailCount() {
		return failCount;
	}

	public void setFailCount(Long failCount) {
		this.failCount =failCount;
	}
				@Column(name = "source")
	public Long getSource() {
		return source;
	}

	public void setSource(Long source) {
		this.source =source;
	}
				@Column(name = "source_code")
	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode =sourceCode;
	}
		}
