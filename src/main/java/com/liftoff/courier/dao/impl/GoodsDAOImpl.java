package com.liftoff.courier.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liftoff.courier.dao.BaseDAO;
import com.liftoff.courier.jaxb.GoodsDetails;
import com.liftoff.courier.util.PropertyReader;
import com.liftoff.courier.util.ResponseUtils;
import com.mysql.jdbc.Statement;

/**
 * The Class GoodsDAOImpl.
 * 
 * @author manoj.n
 * @version 1.0
 */
public class GoodsDAOImpl extends BaseDAO {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(GoodsDAOImpl.class);
	
	/**
	 * Insert goods details.
	 *
	 * @param connection the connection
	 * @param goodsDetails the goods details
	 * @return the long
	 * @throws SQLException the sQL exception
	 */
	public long insertGoodsDetails(Connection connection, GoodsDetails goodsDetails) throws SQLException{
		logger.debug("----- GoodsDAOImpl.insertGoodsDetails() method begins -----");
		PreparedStatement goodsStatement = null;
		ResultSet generatedKeys = null;
		long goodsId = 0L;
		String exceptionMessage = "Error Occurred in GoodsDAOImpl.insertGoodsDetails()";
		try {
			//Getting the connection from Database
			if(connection != null){
				logger.debug("Goods details insertion into database begins");
				//Query Retrieval From Property Files
				String goodsQuery = PropertyReader.getPropertyValue("goods.query.insert");
				
				//Statement Preparation For Contact Insertion
				goodsStatement = connection.prepareStatement(goodsQuery, Statement.RETURN_GENERATED_KEYS);
				goodsStatement.setString(1, goodsDetails.getWeight());
		        goodsStatement.setString(2, goodsDetails.getDescripton());
				
				goodsStatement.execute();
				generatedKeys = goodsStatement.getGeneratedKeys();
		        if (generatedKeys.next()) {
		        	goodsId = generatedKeys.getLong(1);
		        }
		        logger.debug("Goods details insertion into database completed");
			}
			else{
				logger.debug(ResponseUtils.DATABASE_CONNECTION_NULL_RESPONSE);
				return goodsId;
			}
		} catch (SQLException exception) {
			logger.error(ResponseUtils.getExceptionResponse(exceptionMessage,exception));
			return goodsId;
		}
		finally{
			logger.debug("----- GoodsDAOImpl.insertGoodsDetails() method ends -----");
			super.closeStatement(goodsStatement);
		}
		return goodsId;
	}
	
	
}
