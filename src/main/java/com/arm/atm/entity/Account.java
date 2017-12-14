package com.arm.atm.entity;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(uniqueConstraints=@UniqueConstraint(columnNames = {"NUMBER", "BANK_ID"}))
public class Account {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="NUMBER")
	private Long number;
	
	@Column(name="OWNER")
	private String owner;
	
	@Column(name="BALANCE")
	private BigDecimal balance;
	
	@Column(name="PASSWORD")
	private String password;
	
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "BANK_ID", nullable = false)
	private Bank bank;

	public void deposit(Long value) {
		this.balance = this.balance.add(new BigDecimal(value));
	}

	public void withdraw(Long value) {
		this.balance = this.balance.subtract(new BigDecimal(value));
	}
 	
}
