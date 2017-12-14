package com.arm.atm.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.arm.atm.entity.Bank;

public interface BankRepository extends PagingAndSortingRepository<Bank, Long>{

	Bank findByName(String name);

}
