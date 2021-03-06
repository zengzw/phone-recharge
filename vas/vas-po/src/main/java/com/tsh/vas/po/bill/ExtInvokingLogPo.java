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
@Table(name = "ext_invoking_log")
public class ExtInvokingLogPo implements Serializable{
	
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
		
			@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id =id;
	}
				@Column(name = "deposit_id")
	public Long getDepositId() {
		return depositId;
	}

	public void setDepositId(Long depositId) {
		this.depositId =depositId;
	}
				@Column(name = "deposit_code")
	public String getDepositCode() {
		return depositCode;
	}

	public void setDepositCode(String depositCode) {
		this.depositCode =depositCode;
	}
				@Column(name = "op_action")
	public Long getOpAction() {
		return opAction;
	}

	public void setOpAction(Long opAction) {
		this.opAction =opAction;
	}
				@Column(name = "op_param")
	public String getOpParam() {
		return opParam;
	}

	public void setOpParam(String opParam) {
		this.opParam =opParam;
	}
				@Column(name = "op_message")
	public String getOpMessage() {
		return opMessage;
	}

	public void setOpMessage(String opMessage) {
		this.opMessage =opMessage;
	}
				@Column(name = "state")
	public Long getState() {
		return state;
	}

	public void setState(Long state) {
		this.state =state;
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
				@Column(name = "type")
	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type =type;
	}
				@Column(name = "post_time")
	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime =postTime;
	}
				@Column(name = "remarks")
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks =remarks;
	}
		}
