package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class AccountDaoSQL implements AccountDao {

	Account extractAccount(ResultSet rs) throws SQLException {
		int id = rs.getInt("account_id");
		int rsUser = rs.getInt("user_id");
		String rsType = rs.getString("account_type");
		float rsBalance = rs.getFloat("accoun_balance");
		String rsStatus = rs.getString("account_status");
		return new Account(id, rsUser, rsType, rsBalance, rsStatus);
	}

	@Override
	public int create(int Uid, String AT, float AB) {
		// TODO Auto-generated method stub
		try (Connection c = ConnectionUtil.getConnection()) {

			String query = "INSERT INTO Account (account_id, user_id, account_type, account_balance, account_status)"
					+ " VALUES (account_id_seq.nextval ,?,?,?,'Active')";

			PreparedStatement ps = c.prepareStatement(query);
			ps.setInt(1, Uid);
			ps.setString(2, AT);
			ps.setFloat(3, AB);

			return ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int deleteAccountbyId(int id) {
		// TODO Auto-generated method stub
		try (Connection c = ConnectionUtil.getConnection()) {
			String query = "UPDATE account " + "SET account_status = ? " + "WHERE account_id = ?";

			PreparedStatement ps = c.prepareStatement(query);
			ps.setString(1, "Not Active");
			ps.setInt(2, id);

			return ps.executeUpdate();

		} catch (SQLException e) {
			return 0;
		}
	}

	@Override
	public float findBalancebyAccountId(int id) {
		try (Connection c = ConnectionUtil.getConnection()) {
			String query = "Select * from account " + "WHERE account_id = ?";

			PreparedStatement ps = c.prepareStatement(query);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			float output = 0f;
			while (rs.next()) {
				output = rs.getFloat("account_balance");
			}
			return output;
		} catch (SQLException e) {
			return 0;
		}
	}

	@Override
	public float withdrawMoney(float amount, int id) {
		try (Connection c = ConnectionUtil.getConnection()) {
			String query = "UPDATE account " + "SET account_balance = ? " + "WHERE account_id = ?";

			PreparedStatement ps = c.prepareStatement(query);
			ps.setFloat(1, amount);
			ps.setInt(2, id);

			return ps.executeUpdate();

		} catch (SQLException e) {
			return 0;
		}
	}

	@Override
	public float depositMoney(float amount, int id) {
		try (Connection c = ConnectionUtil.getConnection()) {
			String query = "UPDATE account " + "SET account_balance = ? " + "WHERE account_id = ?";

			PreparedStatement ps = c.prepareStatement(query);
			ps.setFloat(1, amount);
			ps.setInt(2, id);

			return ps.executeUpdate();

		} catch (SQLException e) {
			return 0;
		}
	}

	@Override
	public List<Account> viewAll(int id) {
		try (Connection c = ConnectionUtil.getConnection()) {
			String query = "Select * from account " + "WHERE user_id = ? AND account_status != 'Not Active'";

			PreparedStatement ps = c.prepareStatement(query);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			List<Account> accounts = new ArrayList<Account>();
			while (rs.next()) {
				Account acc = new Account(rs.getInt("account_id"), rs.getInt("user_id"), rs.getString("account_type"),
						rs.getFloat("account_balance"), rs.getString("account_status"));
				accounts.add(acc);
			}
			return accounts;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public List<Account> viewAll() {
		try (Connection c = ConnectionUtil.getConnection()) {
			String query = "Select * from account";

			PreparedStatement ps = c.prepareStatement(query);

			ResultSet rs = ps.executeQuery();
			List<Account> accounts = new ArrayList<Account>();
			while(rs.next()) {
				Account acc = new Account(rs.getInt("account_id"), rs.getInt("user_id"), rs.getString("account_type"), rs.getFloat("account_balance"),rs.getString("account_status"));
				accounts.add(acc);
			}
			return accounts;
		} catch (SQLException e) {
			return null;
		}
	}

	
	  @Override public List<String> viewTransactionHistory(int id) { 
		  try (Connection c = ConnectionUtil.getConnection()) {
				String query = "Select * from transactions " + "WHERE account_id = ?";

				PreparedStatement ps = c.prepareStatement(query);
				ps.setInt(1, id);

				ResultSet rs = ps.executeQuery();
				
				List<String> transactions = new ArrayList<String>();
				while(rs.next()) {
					String acc = "Transaction_id: " + rs.getInt("transaction_id") + " Transaction done: " + rs.getFloat("transaction_done") + " Date: " + rs.getDate("transaction_date");
					transactions.add(acc);
				}
				return transactions;
			} catch (SQLException e) {
				return null;
			}
	  }
	 

	@Override
	public float transactionUpdate(float amount, int id) {
		try (Connection c = ConnectionUtil.getConnection()) {

			String query = "INSERT INTO transactions (transaction_id, account_id, transaction_done, transaction_date)"
					+ " VALUES (transaction_id_seq.nextval , ?, ?, SYSDATE)";

			PreparedStatement ps = c.prepareStatement(query);
			ps.setInt(1, id);
			ps.setFloat(2, amount);

			return ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public String findStatus(int id) {
		try (Connection c = ConnectionUtil.getConnection()) {
			String query = "Select * from account " + "WHERE account_id = ?";

			PreparedStatement ps = c.prepareStatement(query);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			String status = "";
			while(rs.next()) {
				status = rs.getString("account_status");
			}
			return status;
		} catch (SQLException e) {
			return null;
		}
	}

	public int findOwner(int id) {
		try (Connection c = ConnectionUtil.getConnection()) {
			String query = "Select * from account " + "WHERE account_id = ?";

			PreparedStatement ps = c.prepareStatement(query);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			int owner = 0;
			while(rs.next()) {
				owner = rs.getInt("user_id");
			}
			return owner;
		} catch (SQLException e) {
			return 0;
		}
	}
	
	
}
