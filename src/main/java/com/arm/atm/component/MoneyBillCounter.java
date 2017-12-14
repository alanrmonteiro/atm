package com.arm.atm.component;

import java.util.ArrayList;
import java.util.List;

import com.arm.atm.vo.MoneyBill;
import com.arm.atm.vo.enums.MoneyType;

public class MoneyBillCounter {

	
	
	public static List<MoneyBill> count(Long value) {
		
		List<MoneyBill> list = new ArrayList<>();
		
		for (MoneyType type : MoneyType.values()) {
			Long valueTypeCount = value / type.getValue();
			if(valueTypeCount >= 1) {
				value = value - (valueTypeCount * type.getValue());
				list.add(new MoneyBill(type,valueTypeCount));
			}
		}
		return list;
	}
}
