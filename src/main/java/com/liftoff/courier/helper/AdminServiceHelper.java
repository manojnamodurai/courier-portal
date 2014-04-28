package com.liftoff.courier.helper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liftoff.courier.dao.impl.DeliveryDAOImpl;
import com.liftoff.courier.jaxb.OrderDetails;
import com.liftoff.courier.util.PropertyReader;

/**
 * The Class AdminServiceHelper.
 * 
 * @author manoj.n
 * @version 1.0
 */
public class AdminServiceHelper {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(AdminServiceHelper.class);
	
	/** The Constant DAY. */
	private static final String DAY = PropertyReader.getPropertyValue("history.type.day");
	
	/** The Constant WEEK. */
	private static final String WEEK = PropertyReader.getPropertyValue("history.type.week");
	
	/** The Constant MONTH. */
	private static final String MONTH = PropertyReader.getPropertyValue("history.type.month");
	
	/** The Constant MONTH_DAYS_COUNT. */
	private static final String MONTH_DAYS_COUNT = PropertyReader.getPropertyValue("days.count.month");
	
	/** The Constant WEEK_DAYS_COUNT. */
	private static final String WEEK_DAYS_COUNT = PropertyReader.getPropertyValue("days.count.week");
	
	/**
	 * Gets the delivery history by period.
	 *
	 * @param type the type
	 * @return the delivery history by period
	 */
	public ArrayList<OrderDetails> getDeliveryHistoryByPeriod(String type){
		logger.debug("----- AdminServiceHelper.getDeliveryHistoryByPeriod() method begins -----");
		ArrayList<OrderDetails> orderDetailsList = null;
		DeliveryDAOImpl deliveryDAOImpl = null;
		Calendar calendar = null;
		String startDate = null;
		String endDate = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try{
			if(DAY.equalsIgnoreCase(type)){
				calendar = new GregorianCalendar();
				startDate = dateFormat.format(calendar.getTime());
				endDate = startDate;
			} else if(WEEK.equalsIgnoreCase(type)){
				calendar = new GregorianCalendar();
				endDate = dateFormat.format(calendar.getTime());
				calendar.add(Calendar.DAY_OF_MONTH, -(Integer.parseInt(WEEK_DAYS_COUNT)-1));
				startDate = dateFormat.format(calendar.getTime());
			} else if(MONTH.equalsIgnoreCase(type)){
				calendar = new GregorianCalendar();
				endDate = dateFormat.format(calendar.getTime());
				calendar.add(Calendar.DAY_OF_MONTH, -(Integer.parseInt(MONTH_DAYS_COUNT)-1));
				startDate = dateFormat.format(calendar.getTime());
			} 
			deliveryDAOImpl = new DeliveryDAOImpl();
			orderDetailsList = deliveryDAOImpl.getDeliveryHistoryByPeriod(startDate, endDate);
		} catch(Exception exception){
			logger.error("Error occured in AdminServiceHelper.getDeliveryHistoryByPeriod() " + exception);
		}
		logger.debug("----- AdminServiceHelper.getDeliveryHistoryByPeriod() method ends -----");
		return orderDetailsList;
	}
}
