package com.arm.atm.vo;

import lombok.Data;

@Data
public class DepositForm {
	private Long accountNumber;
	private String password;
	private String bankName;
	private Long value;
}
