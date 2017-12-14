package com.arm.atm.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arm.atm.component.BankParser;
import com.arm.atm.entity.Bank;
import com.arm.atm.exception.AtmException;
import com.arm.atm.repository.BankRepository;
import com.arm.atm.util.Preconditions;
import com.arm.atm.vo.BankForm;
import com.google.common.collect.Lists;

@RestController
public class BankController {

	@Autowired
	private BankRepository bankRepository;
	
	@Autowired
	private BankParser bankParser;
	
	@RequestMapping(value = "/bank", method=RequestMethod.POST)
	public ResponseEntity<Bank> createBank(@RequestBody BankForm bankForm) {
		
		validateBank(bankForm);
		
		Bank bank 	= bankParser.parse(bankForm);
		Bank bankDb = bankRepository.save(bank);
		
		return new ResponseEntity<Bank>(bankDb, OK);
	}
	
	@RequestMapping(value = "/banks", method=RequestMethod.GET)
	public ResponseEntity<List<Bank>> listBanks() {
		List<Bank> bankDb = Lists.newArrayList(bankRepository.findAll());
		
		return new ResponseEntity<List<Bank>>(bankDb, OK);
	}
	
	private void validateBank(BankForm bankForm) {
		Bank bank = bankRepository.findByName(bankForm.getName());
		
		Optional.ofNullable(bank)
				.ifPresent(a -> Preconditions.throwException(new AtmException("Bank already exists.")));
	}
}
