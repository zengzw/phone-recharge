package com.tsh.vas.vo.bill;

import java.io.Serializable;


public class DepositStatisVo implements Serializable{
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
		
				
	
	
			public Long getId() {
			return id;
		}
	
		public void setId(Long id) {
			this.id =id;
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
			public Long getSuccCount() {
			return succCount;
		}
	
		public void setSuccCount(Long succCount) {
			this.succCount =succCount;
		}
			public Long getFailCount() {
			return failCount;
		}
	
		public void setFailCount(Long failCount) {
			this.failCount =failCount;
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
	}
