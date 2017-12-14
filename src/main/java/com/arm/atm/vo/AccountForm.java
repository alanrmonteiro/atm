package com.arm.atm.vo;

import lombok.Data;

@Data
public class AccountForm {

	private Long number;
	
	private String owner;
	
	private Double balance;
	
	private Long bankId;
	
	private String password;
}
