package com.revature.daos;

import java.sql.ResultSet;
import java.util.List;

import com.revature.models.User;

public interface UserDao {
	
	UserDao currentImplementation = new UserDaoSQL();

	int save(String user, String Pass);

	List<User> findAll();

	User findById(int id);
	
	User findByUsernameAndPassword(String username, String password);

	User findByUsername(String username);
	
	
}

