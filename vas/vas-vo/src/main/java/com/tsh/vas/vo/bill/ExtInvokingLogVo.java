package com.tsh.vas.vo.bill;

import java.io.Serializable;
import java.util.Date;


public class ExtInvokingLogVo implements Serializable{
	private static final long serialVersionUID = 1L;

						/**  外部日志ID*/
			private Long id;
								/**  充值ID*/
			private Long depositId;
		
								/**  充值CODE*/
			private String depositCode;
		
								/**  操作行为(查询、充值、回调)*/
			private Long opAction;
		
								/**  入参*/
			private String opParam;
		
								/**  操作结果信息*/
			private String opMessage;
		
								/**  调用状态*/
			private Long state;
		
								/**  来源（淘实惠、智慧商店）*/
			private Long source;
		
								/**  来源编码*/
			private String sourceCode;
		
								/**  预留*/
			private Long type;
		
								/**  提交时间*/
			private Date postTime;
		
								/**  备注*/
			private String remarks;
		
				
	
	
			public Long getId() {
			return id;
		}
	
		public void setId(Long id) {
			this.id =id;
		}
			public Long getDepositId() {
			return depositId;
		}
	
		public void setDepositId(Long depositId) {
			this.depositId =depositId;
		}
			public String getDepositCode() {
			return depositCode;
		}
	
		public void setDepositCode(String depositCode) {
			this.depositCode =depositCode;
		}
			public Long getOpAction() {
			return opAction;
		}
	
		public void setOpAction(Long opAction) {
			this.opAction =opAction;
		}
			public String getOpParam() {
			return opParam;
		}
	
		public void setOpParam(String opParam) {
			this.opParam =opParam;
		}
			public String getOpMessage() {
			return opMessage;
		}
	
		public void setOpMessage(String opMessage) {
			this.opMessage =opMessage;
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
			public Date getPostTime() {
			return postTime;
		}
	
		public void setPostTime(Date postTime) {
			this.postTime =postTime;
		}
			public String getRemarks() {
			return remarks;
		}
	
		public void setRemarks(String remarks) {
			this.remarks =remarks;
		}
	}
