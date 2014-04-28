package com.liftoff.courier.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liftoff.courier.dao.BaseDAO;
import com.liftoff.courier.jaxb.Address;
import com.liftoff.courier.util.PropertyReader;
import com.liftoff.courier.util.ResponseUtils;
import com.mysql.jdbc.Statement;

/**
 * The Class AddressDAOImpl.
 * 
 * @author manoj.n
 * @version 1.0
 */
public class AddressDAOImpl extends BaseDAO {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(AddressDAOImpl.class);
	
	/**
	 * Insert address.
	 *
	 * @param connection the connection
	 * @param address the address
	 * @return the long
	 * @throws SQLException the sQL exception
	 */
	public long insertAddress(Connection connection, Address address) throws SQLException {
		logger.debug("----- AddressDAOImpl.insertAddress() method begins -----");
		PreparedStatement addressStatement = null;
		ResultSet generatedKeys = null;
		long addressId = 0L;
		String exceptionMessage = "Error Occurred in AddressDAOImpl.insertAddress()";
		try {
			//Getting the connection from Database
			if(connection != null){
				logger.debug("Address details insertion into database begins");
				//Query Retrieval From Property Files
				String addressQuery = PropertyReader.getPropertyValue("address.query.insert");
				
				//Statement Preparation For Address Insertion
				addressStatement = connection.prepareStatement(addressQuery, Statement.RETURN_GENERATED_KEYS);
				addressStatement.setString(1, address.getName());
				addressStatement.setString(2, address.getStreet1());
				addressStatement.setString(3, address.getStreet2());
				addressStatement.setString(4, address.getCity());
				addressStatement.setString(5, address.getState());
				addressStatement.setString(6, address.getCountry());
				addressStatement.setString(7, address.getZipcode());
				
				addressStatement.execute();
				generatedKeys = addressStatement.getGeneratedKeys();
		        if (generatedKeys.next()) {
		        	addressId = generatedKeys.getLong(1);
		        }
		        logger.debug("Address details insertion into database completed");
			}
			else{
				logger.debug(ResponseUtils.DATABASE_CONNECTION_NULL_RESPONSE);
				return addressId;
			}
		} catch (SQLException exception2) {
			logger.error(ResponseUtils.getExceptionResponse(exceptionMessage,exception2));
			System.out.println(exception2);
			return addressId;
		}
		finally{
			logger.debug("----- AddressDAOImpl.insertAddress() method ends -----");
			super.closeStatement(addressStatement);
		}
		return addressId;
	}
	
	/**
	 * Gets the address.
	 *
	 * @param connection the connection
	 * @param addressId the address id
	 * @return the address
	 * @throws SQLException the sQL exception
	 */
	public Address getAddress(Connection connection, long addressId) throws SQLException{
		logger.debug("----- ContactDAOImpl.getAddress() method begins -----");
		PreparedStatement addressStatement = null;
		ResultSet resultSet = null;
		Address address = null;
		String exceptionMessage = "Error Occurred in AddressDAOImpl.getAddress()";
		try {
			//Getting the connection from Database
			if(connection != null){
				logger.debug("Contact details fetch from database begins");
				//Query Retrieval From Property Files
				String addressQuery = PropertyReader.getPropertyValue("address.query.select");
				
				//Statement Preparation For Contact Insertion
				addressStatement = connection.prepareStatement(addressQuery);
				addressStatement.setLong(1, addressId);
				
				resultSet = addressStatement.executeQuery();
				if (resultSet.next()) {
					resultSet.beforeFirst();
					while(resultSet.next()){
						address = new Address();
						address.setName(resultSet.getString("name"));
						address.setName(resultSet.getString("street1"));
						address.setName(resultSet.getString("street2"));
						address.setName(resultSet.getString("city"));
						address.setName(resultSet.getString("state"));
						address.setName(resultSet.getString("country"));
						address.setName(resultSet.getString("zipcode"));
					}
				} else {
					logger.debug(ResponseUtils.NO_RECORD_IN_DATABASE);
					return null;
				}
				logger.debug("Contact details fetch from database completed");
			}
			else{
				logger.debug(ResponseUtils.DATABASE_CONNECTION_NULL_RESPONSE);
				return address;
			}
		} catch (SQLException exception2) {
			logger.error(ResponseUtils.getExceptionResponse(exceptionMessage,exception2));
			return address;
		}
		finally{
			logger.debug("----- ContactDAOImpl.getAddress() method ends -----");
			super.closeStatement(addressStatement);
			super.closeResultset(resultSet);
		}
		return address;
	}
}
