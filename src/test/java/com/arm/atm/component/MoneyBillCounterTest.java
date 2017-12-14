package com.arm.atm.component;

import static com.arm.atm.component.MoneyBillCounter.count;
import static com.arm.atm.vo.enums.MoneyType.ONE_HUNDRED;
import static com.arm.atm.vo.enums.MoneyType.TEN;
import static com.arm.atm.vo.enums.MoneyType.TWENTY;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.arm.atm.vo.MoneyBill;

public class MoneyBillCounterTest {

	@Test
	public void must_count_money_bills() {
		List<MoneyBill> noneyBill = count(30L);
		
		assertEquals(2, noneyBill.size());
		
		assertEquals(TWENTY, noneyBill.get(0).getType());
		assertEquals(new Long(1), noneyBill.get(0).getCount());
		
		assertEquals(TEN, noneyBill.get(1).getType());
		assertEquals(new Long(1), noneyBill.get(1).getCount());
	}
	
	@Test
	public void must_count_money_bills_for_1000() {
		List<MoneyBill> noneyBill = count(1000L);
		
		assertEquals(1, noneyBill.size());
		
		assertEquals(ONE_HUNDRED, noneyBill.get(0).getType());
		assertEquals(new Long(10), noneyBill.get(0).getCount());
	}
	
	@Test
	public void must_count_money_bills_for_130() {
		List<MoneyBill> noneyBill = count(130L);
		
		assertEquals(3, noneyBill.size());
		
		assertEquals(ONE_HUNDRED, noneyBill.get(0).getType());
		assertEquals(new Long(1), noneyBill.get(0).getCount());
		
		assertEquals(TWENTY, noneyBill.get(1).getType());
		assertEquals(new Long(1), noneyBill.get(1).getCount());
		
		assertEquals(TEN, noneyBill.get(2).getType());
		assertEquals(new Long(1), noneyBill.get(2).getCount());
	}
}
