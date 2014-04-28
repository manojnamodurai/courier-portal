package com.liftoff.courier.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liftoff.courier.util.PropertyReader;

/**
 * The Class BaseDAO.
 * 
 * @author manoj.n
 * @version 1.0
 */
public class BaseDAO implements CourierDAO {

	/** The logger. */
	public Logger logger = LoggerFactory.getLogger(BaseDAO.class);
	
	@Override
	public Connection getConnection() throws ClassNotFoundException,SQLException {
		logger.info("BaseDAO.getConnection method ends");
		Connection connection = null;
		try {
			String driver = PropertyReader.getPropertyValue("driver");
			String url = PropertyReader.getPropertyValue("url");
			String user = PropertyReader.getPropertyValue("user");
			String password = PropertyReader.getPropertyValue("password");
			Class.forName(driver).newInstance();;
			connection = DriverManager.getConnection(url, user, password);
			logger.info("Database Connection is Obtained");
		}catch(Exception exception){
			String exceptionMessage = "Error Occurred in BaseDAO.getConnection";
			logger.error(exceptionMessage + exception);
			throw new SQLException(exception.getMessage());
		}
		logger.info("BaseDAO.getConnection method begins");
		return connection;
	}
	
	@Override
	public void releaseConnection(Connection connection) throws SQLException {
		try {
			if(connection != null){
				connection.close();
				logger.info("Database Connection is Closed...");
			}
		} catch (SQLException exception) {
			String exceptionMessage = "Error occured in BaseDAO.closeResultset";
			logger.error(exceptionMessage + exception);
		}
	}
	
	@Override
	public void closeStatement(Statement statement) throws SQLException {
		try {
			if(statement != null){
				statement.close();
			}
		} catch (SQLException exception) {
			String exceptionMessage = "Error occured in BaseDAO.closeResultset";
			logger.error(exceptionMessage + exception);
		}
	}
	
	@Override
	public void closeResultset(ResultSet resultSet) throws SQLException {
		try {
			if(resultSet != null){
				resultSet.close();
			}
		} catch (SQLException exception) {
			String exceptionMessage = "Error occured in BaseDAO.closeResultset";
			logger.error(exceptionMessage + exception);
		}
	}

}
