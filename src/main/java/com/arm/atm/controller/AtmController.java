package com.arm.atm.controller;

import static org.springframework.http.HttpStatus.OK;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.arm.atm.component.Atm;
import com.arm.atm.component.AtmSession;
import com.arm.atm.vo.DepositForm;
import com.arm.atm.vo.MoneyBill;

@Controller
public class AtmController {

	@Autowired
	private Atm atm;
	
	@RequestMapping(value = "/deposit", method=RequestMethod.POST)
	public ResponseEntity<String> deposit(@RequestBody DepositForm depositForm) {
		
		AtmSession atmSession = atm.authenticate(depositForm.getBankName(), 
												 depositForm.getAccountNumber(), 
												 depositForm.getPassword());
		
		atmSession.deposit(depositForm.getValue());
		
		return new ResponseEntity<String>("Deposit successfull", OK);
	}
	
	@RequestMapping(value = "/withdraw", method=RequestMethod.POST)
	public ResponseEntity<String> createBank(@RequestBody DepositForm depositForm) {
		
		AtmSession atmSession = atm.authenticate(depositForm.getBankName(), 
												 depositForm.getAccountNumber(), 
												 depositForm.getPassword());
		
		List<MoneyBill> withdraw = atmSession.withdraw(depositForm.getValue());
		
		return new ResponseEntity<String>("Withdraw successfull:" + withdraw, OK);
	}
	
	@RequestMapping(value = "/balance", method=RequestMethod.POST)
	public ResponseEntity<String> balance(@RequestBody DepositForm depositForm) {
		
		AtmSession atmSession = atm.authenticate(depositForm.getBankName(), 
												 depositForm.getAccountNumber(), 
												 depositForm.getPassword());
		
		BigDecimal balance = atmSession.getBalance();
		
		return new ResponseEntity<String>("Balance: " + balance, OK);
	}
}
