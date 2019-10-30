package com.revature.prompts;

import java.util.Scanner;

import com.revature.daos.AccountDao;
import com.revature.daos.UserDao;
import com.revature.util.AuthUtil;

public class CreateAccountPrompt implements Prompt{
	
	private AccountDao accountDao = AccountDao.currentImplementation;
	private UserDao userDao = UserDao.currentImplementation;
	private Scanner scan = new Scanner(System.in);
	private AuthUtil authUtil = AuthUtil.instance;
	
	@Override
	public Prompt run() {
		System.out.println("You have chosen to create an account!");
		System.out.println("Please enter the type of account you would like to create");
		String type = scan.nextLine();
		System.out.println("Please enter the amount of money you would like to start with");
		float amount = scan.nextFloat();
		accountDao.create(authUtil.getCurrentUser().getId(), type, amount);
		System.out.println("Account created!");
		return new MainMenuPrompt();
	}

}
