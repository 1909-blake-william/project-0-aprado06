package com.revature.prompts;

import java.util.Scanner;

import com.revature.daos.AccountDao;
import com.revature.daos.UserDao;
import com.revature.util.AuthUtil;

public class DepositMoneyPrompt implements Prompt{

	private AccountDao accountDao = AccountDao.currentImplementation;
	private UserDao userDao = UserDao.currentImplementation;
	private Scanner scan = new Scanner(System.in);
	private AuthUtil authUtil = AuthUtil.instance;
	
	@Override
	public Prompt run() {
		// TODO Auto-generated method stub
		System.out.println("You have chosen to deposit money");
		System.out.println("Which account would you like to deposit to?");
		int acc = scan.nextInt();
		if(accountDao.findStatus(acc).equals("Not Active")) {
			System.out.println("That Account is not active");
			return new DepositMoneyPrompt();
		}
		if(accountDao.findOwner(acc)!= authUtil.getCurrentUser().getId()) {
			System.out.println("This account does not belong to you");
			return new DepositMoneyPrompt();
		}
		System.out.println("How much money are you going to deposit?");
		float amount = scan.nextFloat();
		float balance = accountDao.findBalancebyAccountId(acc);
		balance = balance + amount;
		System.out.println("Your new balance is " + balance);
		accountDao.depositMoney(balance, acc);
		accountDao.transactionUpdate(amount, acc);
		
		System.out.println("Input 1 to go back to Main Menu or 0 to logout");
		int s = scan.nextInt();
		if(s==1) {
		return new MainMenuPrompt(); 
		} else {
			return new LoginPrompt();
		}
	}

}
