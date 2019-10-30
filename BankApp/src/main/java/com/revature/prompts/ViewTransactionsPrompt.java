package com.revature.prompts;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.daos.AccountDao;
import com.revature.daos.UserDao;
import com.revature.models.Account;
import com.revature.util.AuthUtil;

public class ViewTransactionsPrompt implements Prompt{

	private AccountDao accountDao = AccountDao.currentImplementation;
	private UserDao userDao = UserDao.currentImplementation;
	private Scanner scan = new Scanner(System.in);
	private AuthUtil authUtil = AuthUtil.instance;
	@Override
	public Prompt run() {
		System.out.println("For which account do you want to see the history?");
		int id = scan.nextInt();
		System.out.println("Your transactions for account " + id + " are: ");
		List<String> output = new ArrayList<String>();
		output = accountDao.viewTransactionHistory(id);
		for(String s: output) {
			System.out.println(s);
		}
		
		System.out.println("Input 1 to go back to Main Menu or 0 to logout");
		int s = scan.nextInt();
		if(s==1) {
		return new MainMenuPrompt(); 
		} else {
			return new LoginPrompt();
		}
	}

}
