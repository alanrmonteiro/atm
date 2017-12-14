package com.arm.atm.util;

public class Preconditions {

	public static void ifLessThanThrow(Long value, Long compare, RuntimeException e){
		if(value < compare)
			throw e;
	}
	
	public static Object throwException(RuntimeException e) {
		throw e;
	}
}
