package com.arm.atm.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arm.atm.component.AccountParser;
import com.arm.atm.entity.Account;
import com.arm.atm.entity.Bank;
import com.arm.atm.exception.AtmException;
import com.arm.atm.repository.AccountRepository;
import com.arm.atm.repository.BankRepository;
import com.arm.atm.util.Preconditions;
import com.arm.atm.vo.AccountForm;

@RestController
public class AccountController {

	@Autowired
	public AccountRepository accountRepository;
	
	@Autowired
	private BankRepository bankRepository;
	
	@Autowired
	private AccountParser accountParser;
	
	@RequestMapping(value = "/account", method=RequestMethod.POST)
	public ResponseEntity<Account> createAccount(@RequestBody AccountForm accountForm) {
		
		validateAccount(accountForm);
		
		Bank bank = bankRepository.findOne(accountForm.getBankId());
		validateBank(bank);
		Account newAccount = accountParser.parse(accountForm, bank);
		Account accountDb = accountRepository.save(newAccount);
		return new ResponseEntity<Account>(accountDb, OK);
	}

	private void validateBank(Bank bank) {
		Optional.ofNullable(bank).orElseThrow(()-> new AtmException("Bank does not exist."));
	}

	private void validateAccount(AccountForm accountForm) {
		Account existingAccount = accountRepository.find(accountForm.getBankId(), accountForm.getNumber());
		
		Optional
			.ofNullable(existingAccount)
			.ifPresent(a -> Preconditions.throwException(new AtmException("Account already account.")));
	}
}
