package com.revature.prompts;

import java.util.Scanner;

import com.revature.util.AuthUtil;

public class MainMenuPrompt implements Prompt{

		private Scanner scan = new Scanner(System.in);
		private AuthUtil authUtil = AuthUtil.instance;

		@Override
		public Prompt run() {
			
			if(authUtil.getCurrentUser().getRole().equals("Admin")) {
				System.out.println("Enter 1 to view all Users");
				System.out.println("Enter 2 to view all Accounts");
				System.out.println("Enter 0 to logout");
			} else {
			System.out.println("Welcome " + authUtil.getCurrentUser().getUsername() + ", please choose an option");
			System.out.println("Enter 1 to create Account");
			System.out.println("Enter 2 to delete Account");
			System.out.println("Enter 3 to view Accounts");
			System.out.println("Enter 4 to withdraw money");
			System.out.println("Enter 5 to deposit money");
			System.out.println("Enter 6 to view Account Transactions");
			System.out.println("Enter 0 to logout");
			}

			// get user input
			String selection = scan.nextLine();

			switch (selection) {
			case "0":
				return new LoginPrompt();
			case "1":
				if(authUtil.getCurrentUser().getRole().equals("Admin")) {
					return new AdminViewUsersPrompt();
				} else {
				return new CreateAccountPrompt();
				}
			case "2":
				if(authUtil.getCurrentUser().getRole().equals("Admin")) {
					return new AdminViewAccPrompt();
				} else {
				return new DeleteAccountPrompt();
				}
			case "3":
				return new ViewAccountsPrompt();	
			case "4":
				return new WithdrawPrompt();
			case "5":
				return new DepositMoneyPrompt();
			case "6":
				return new ViewTransactionsPrompt();
			default:
				System.out.println("invalid selection, try again.");
				break;
			}
			return this;
		}

}
