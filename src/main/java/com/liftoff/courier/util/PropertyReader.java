package com.liftoff.courier.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class PropertyReader.
 * 
 * @author manoj.n
 * @version 1.0
 */
public class PropertyReader {

	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(PropertyReader.class);
	
	/** The properties. */
	private static Properties properties;
	
	/**
	 * Gets the property value.
	 *
	 * @param propertyKey the property key
	 * @return the property value
	 */
	public static String getPropertyValue(String propertyKey) {
		String propertyValue = null;
		try {
			properties = getPropertiesInstance(properties);
			propertyValue = (String) properties.get(propertyKey);
		} catch (Exception exception) {
			String exceptionMessage = "Error Occurred in PropertyReader.getPropertyValue";
			logger.error(exceptionMessage + exception);
		}
		return propertyValue;
	}	
	
	/**
	 * Gets the properties instance.
	 *
	 * @param properties the properties
	 * @return the properties instance
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private static Properties getPropertiesInstance(Properties properties) throws IOException {
		if (null == properties) {
			properties = new Properties();
			InputStream inputStream = PropertyReader.class.getClassLoader().getResourceAsStream("/courierportal.properties");
			properties.load(inputStream);
			logger.info("Property File courierportal.properties Loaded Successfully....");
		}
		return properties;
	}
	
}
