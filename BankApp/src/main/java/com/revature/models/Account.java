package com.revature.models;

import java.util.List;

public class Account {
	
	private int accountId;
	private int userId;
	private String accountType;
	private float accountBalance;
	private String accountStatus;
	
	public Account(int accountId, int userId, String accountType, float accountBalance, String accountStatus) {
		super();
		this.accountId = accountId;
		this.userId = userId;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
		this.accountStatus = accountStatus;
	}

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public float getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(float accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", userId=" + userId + ", accountType=" + accountType
				+ ", accountBalance=" + accountBalance + ", accountStatus=" + accountStatus + "]";
	}
	

	
}
