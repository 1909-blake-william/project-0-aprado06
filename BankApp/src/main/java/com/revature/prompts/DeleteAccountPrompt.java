package com.revature.prompts;

import java.util.Scanner;

import com.revature.daos.AccountDao;
import com.revature.daos.UserDao;
import com.revature.util.AuthUtil;

public class DeleteAccountPrompt implements Prompt{

	private AccountDao accountDao = AccountDao.currentImplementation;
	private UserDao userDao = UserDao.currentImplementation;
	private Scanner scan = new Scanner(System.in);
	private AuthUtil authUtil = AuthUtil.instance;
	@Override
	public Prompt run() {
		System.out.println("You have decided to delete an account we're sorry to see you go");
		System.out.println("Please enter the account id you would like to delete");
		int id = scan.nextInt();
		accountDao.deleteAccountbyId(id);
		System.out.println("Account deleted");
		
		System.out.println("Input 1 to go back to Main Menu or 0 to logout");
		int s = scan.nextInt();
		if(s==1) {
		return new MainMenuPrompt(); 
		} else {
			return new LoginPrompt();
		}
		
	}

}
