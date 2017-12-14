package com.arm.atm.component;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.arm.atm.entity.Account;
import com.arm.atm.exception.AtmException;
import com.arm.atm.repository.AccountRepository;
import com.arm.atm.util.Preconditions;
import com.arm.atm.vo.MoneyBill;

@Component
@Scope(SCOPE_PROTOTYPE)
public class AtmSession {

	private Account account;
	
	@Autowired
	private AccountRepository accountRepository;

	AtmSession(Account account) {
		this.account = account;
	}

	public List<MoneyBill> withdraw(Long value) {
		long balance = account.getBalance().longValue();

		Preconditions.ifLessThanThrow(balance, value, new AtmException("Value exceeded the limit."));

		account.withdraw(value);
		this.account = accountRepository.save(this.account);
		return MoneyBillCounter.count(value);
	}
	
	public AtmSession deposit(Long value) {
		account.deposit(value);
		this.account = accountRepository.save(this.account);
		return this;
	}
	
	public BigDecimal getBalance() {
		return account.getBalance();
	}
}
