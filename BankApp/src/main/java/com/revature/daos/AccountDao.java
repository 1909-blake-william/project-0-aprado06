package com.revature.daos;

import java.util.List;

import com.revature.models.Account;

public interface AccountDao {
	
	AccountDao currentImplementation = new AccountDaoSQL();
	
	int create(int Uid, String AT, float AB);
	
	int deleteAccountbyId(int id);
	
	float findBalancebyAccountId(int id);
	
	float withdrawMoney(float amount, int id);
	
	float depositMoney(float amount, int id);
	
	float transactionUpdate(float amount, int id);
	
	String findStatus(int id);
	
	public int findOwner(int id);
	
	List<Account> viewAll(int id);
	
	List<Account> viewAll();
	
	List<String> viewTransactionHistory(int id);
	
	
}
