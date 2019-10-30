package com.revature.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class UserDaoSQL implements UserDao {

	User extractUser(ResultSet rs) throws SQLException {
		int id = rs.getInt("user_id");
		String rsUsername = rs.getString("username");
		String rsPassword = rs.getString("password");
		String role = rs.getString("role");
		return new User(id, rsUsername, rsPassword, role);
	}
	
	@Override
	public int save(String user, String Pass) {
		// TODO Auto-generated method stub
		try (Connection c = ConnectionUtil.getConnection()) {

			String query = "INSERT INTO bank_users (user_id, username, password, role)"
					+ " VALUES (bank_users_id_seq.nextVal, ?, ?, 'Client')";

			PreparedStatement ps = c.prepareStatement(query);
			ps.setString(1, user);
			ps.setString(2, Pass);

			return ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		//log.debug("attempting to find user by credentials from DB");
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM bank_users";

			PreparedStatement ps = c.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<User> users = new ArrayList<User>();
			while (rs.next()) {
				users.add(new User(rs.getInt("user_id"), rs.getString("username"), "*****", rs.getString("role")));
			}

			return users;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM bank_users " + "WHERE user_id = ?";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return extractUser(rs);
			} else {
				return null;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		//log.debug("attempting to find user by credentials from DB");
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM bank_users " + "WHERE username = ?" + " AND password = ?";
			
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return extractUser(rs);
			} else {
				return null;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		try (Connection c = ConnectionUtil.getConnection()) {

			String sql = "SELECT * FROM bank_users " + "WHERE username = ?";

			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, username);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return extractUser(rs);
			} else {
				return null;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}


}
