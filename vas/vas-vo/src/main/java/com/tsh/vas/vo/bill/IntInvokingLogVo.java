package com.tsh.vas.vo.bill;

import java.io.Serializable;
import java.util.Date;

public class IntInvokingLogVo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** 内部日志 */
	private Long id;
	/** 订单ID */
	private Long orderId;

	/** 订单CODE */
	private String orderCode;

	/** 操作行为(查询、充值、回调) */
	private Long opAction;

	/** 入参 */
	private String opParam;

	/** 操作结果信息 */
	private String opMessage;

	/** 调用状态(1-成功 2-失败) */
	private Long state;

	/** 来源（淘实惠、智慧商店） */
	private Long source;

	/** 来源编码 */
	private String sourceCode;

	/** 预留 */
	private Long type;

	/** 提交时间 */
	private Date postTime;

	/** 备注 */
	private String remarks;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Long getOpAction() {
		return opAction;
	}

	public void setOpAction(Long opAction) {
		this.opAction = opAction;
	}

	public String getOpParam() {
		return opParam;
	}

	public void setOpParam(String opParam) {
		this.opParam = opParam;
	}

	public String getOpMessage() {
		return opMessage;
	}

	public void setOpMessage(String opMessage) {
		this.opMessage = opMessage;
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

	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public IntInvokingLogVo(){
	}
	/**
	 * 内部系统调用日志记录构造方法
	 * @param opAction  操作行为(查询、充值、回调)
	 * @param opParam  入参
	 * @param opMessage 出参
	 * @param state 调用状态(1-成功 2-失败)
	 * @param postTime 调用时间
	 * @param remarks 备注
	 */
	public IntInvokingLogVo(Long opAction, String opParam, String opMessage, Long state,Date postTime,String remarks) {
		this.opAction = opAction;
		this.opParam = opParam;
		this.opMessage = opMessage;
		this.state = state;
		this.postTime = postTime;
		this.remarks = remarks;
	}
}
