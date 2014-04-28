package com.liftoff.courier.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The Interface CourierDAO.
 * 
 * @author manoj.n
 * @version 1.0
 */
public interface CourierDAO {

	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 */
	public Connection getConnection() throws ClassNotFoundException,SQLException;
	
	/**
	 * Release connection.
	 *
	 * @param connection the connection
	 * @throws SQLException the sQL exception
	 */
	public void releaseConnection(Connection connection) throws SQLException;
	
	/**
	 * Close statement.
	 *
	 * @param statement the statement
	 * @throws SQLException the sQL exception
	 */
	public void closeStatement(Statement statement) throws SQLException;
	
	/**
	 * Close resultset.
	 *
	 * @param resultSet the result set
	 * @throws SQLException the sQL exception
	 */
	public void closeResultset(ResultSet resultSet) throws SQLException;
	
}

