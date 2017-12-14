package com.arm.atm.exception;

public class AtmException extends RuntimeException{

	private static final long serialVersionUID = 7180353641164682990L;

	public AtmException(String message) {
		super(message);
	}
	
	public static void throwAtmException(String message) {
		new AtmException(message);
	}
}
