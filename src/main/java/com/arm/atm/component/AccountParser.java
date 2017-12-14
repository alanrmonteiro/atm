package com.arm.atm.component;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.arm.atm.entity.Account;
import com.arm.atm.entity.Bank;
import com.arm.atm.vo.AccountForm;

@Component
public class AccountParser {
	
	public Account parse(AccountForm accountForm, Bank bank) {
		return Account
				.builder()
				.bank(bank)
				.number(accountForm.getNumber())
				.balance(new BigDecimal(accountForm.getBalance()))
				.owner(accountForm.getOwner())
				.password(accountForm.getPassword())
				.build();
	}
	
}
