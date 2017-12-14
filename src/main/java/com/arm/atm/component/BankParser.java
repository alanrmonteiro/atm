package com.arm.atm.component;

import org.springframework.stereotype.Component;

import com.arm.atm.entity.Bank;
import com.arm.atm.vo.BankForm;
@Component
public class BankParser {

	public Bank parse(BankForm bankForm) {
		return Bank.builder().name(bankForm.getName()).build();
	}
	
}
