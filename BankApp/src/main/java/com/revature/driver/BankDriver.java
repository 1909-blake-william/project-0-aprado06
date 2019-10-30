package com.revature.driver;

import java.util.List;

import com.revature.daos.UserDao;
import com.revature.models.User;
import com.revature.prompts.LoginPrompt;
import com.revature.prompts.Prompt;

public class BankDriver {
	
	public static void main(String[] args) {

		 Prompt p = new LoginPrompt();
		  
		  while (true) { 
			  p = p.run();}
		
	}
}
