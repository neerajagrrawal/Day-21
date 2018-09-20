package com.capgemini.bankapp.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.bankapp.dbutil.DbUtil;
import com.capgemini.bankapp.repository.BankAccountRepository;

@Repository
public class BankAccountRepositoryImpl implements BankAccountRepository {
	
	@Autowired
	private DbUtil dbUtil ;




	@Override
	public double getBalance(long accountId) {
		String query = "select balance from bankAccount where account_Id=? ";
		try(Connection connection = dbUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(query);)
		{
			
			statement.setInt(1,(int)accountId);
			ResultSet result=statement.executeQuery() ;
			if(result.next())
			{
				return result.getDouble(1) ;
			}
			
			} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		return -1 ;
	}

	@Override
	public boolean updateBalance(long accountId, double newBalance) {
		
		String query = "UPDATE bankAccount set balance=? where account_Id=? ";
		try(Connection connection = dbUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(query);)
		{
			
			statement.setInt(2,(int)accountId);
			statement.setDouble(1, newBalance);
			if(statement.executeUpdate()!=0)
			{
				return true ;
			}
			else
			{
				return false ;
			}
		}
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		return false ;
		
		
		
		
	}

}
