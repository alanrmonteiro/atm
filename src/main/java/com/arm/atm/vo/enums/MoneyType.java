package com.arm.atm.vo.enums;

public enum MoneyType {
	ONE_HUNDRED(100L),
	FIFTY(50L),
	TWENTY(20L),
	TEN(10L);
	
	private Long value;
	
	MoneyType(Long value){
		this.value = value;
	}
	
	public Long getValue() {
		return value;
	}
}
