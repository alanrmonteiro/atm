package com.arm.atm.vo;

import com.arm.atm.vo.enums.MoneyType;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MoneyBill {
	
	private MoneyType type;
	private Long count = 0L;
	
	public String toString() {
		return type.name() + ":" + count;
	}
}
