package com.tsh.vas.vo.bill;

import java.util.List;

public class OperatorsVo {
	private String operatorsType; //运营商名称
	
	private List<String> den; //面额集合
	
	public String getOperatorsType() {
		return operatorsType;
	}

	public void setOperatorsType(String operatorsType) {
		this.operatorsType = operatorsType;
	}

	public List<String> getDen() {
		return den;
	}

	public void setDen(List<String> den) {
		this.den = den;
	}
	
}

