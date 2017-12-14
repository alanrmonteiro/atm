package com.arm.atm.atmback;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.arm.atm.component.Atm;
import com.arm.atm.entity.Account;
import com.arm.atm.entity.Bank;
import com.arm.atm.exception.AtmException;
import com.arm.atm.repository.AccountRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AtmBackApplicationTests {

	@Autowired
	private Atm atm;

	@Autowired
	private AccountRepository accountRepository;

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Before
	public void before() {

	}

	@Test
	public void must_create_account() {
		Bank itau = Bank.builder().name("Itau").build();

		Account account = Account.builder().bank(itau).owner("José").password("1234").number(111111L)
				.balance(new BigDecimal(0)).build();

		accountRepository.save(account);
	}

	@Test
	@Transactional
	public void must_deposit() {
		Bank itau = Bank.builder().name("Banco do Brasil").build();

		Account account = Account.builder().bank(itau).owner("José").password("1234").number(111111L)
				.balance(new BigDecimal(0)).build();

		accountRepository.save(account);

		atm.authenticate("Banco do Brasil", 111111L, "1234").deposit(100L);

		Account accountDb = accountRepository.find("Banco do Brasil", 111111L, "1234");

		Assert.assertEquals(new BigDecimal("100.00").doubleValue(), accountDb.getBalance().doubleValue(), 2);
	}

	@Test
	@Transactional
	public void must_withdraw() {
		Bank itau = Bank.builder().name("Banco do Brasil").build();

		Account account = Account.builder().bank(itau).owner("José").password("1234").number(111111L)
				.balance(new BigDecimal(1000)).build();

		accountRepository.save(account);

		atm.authenticate("Banco do Brasil", 111111L, "1234").withdraw(100L);

		Account accountDb = accountRepository.find("Banco do Brasil", 111111L, "1234");

		Assert.assertEquals(new BigDecimal("900.00").doubleValue(), accountDb.getBalance().doubleValue(), 2);
	}
	
	@Test
	@Transactional
	public void must_throw_exception_withdraw_bigger_than_balance() {
		
		thrown.expect(AtmException.class);
		
		Bank itau = Bank.builder().name("Banco do Brasil").build();

		Account account = Account.builder().bank(itau).owner("José").password("1234").number(111111L)
				.balance(new BigDecimal(1000)).build();

		accountRepository.save(account);

		atm.authenticate("Banco do Brasil", 111111L, "1234").withdraw(1001L);
	}
}
