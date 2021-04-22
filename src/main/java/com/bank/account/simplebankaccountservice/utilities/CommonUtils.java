package com.bank.account.simplebankaccountservice.utilities;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Thusitha Dissanayaka
 * 
 */
public class CommonUtils {
	final static Logger logger = LoggerFactory.getLogger(CommonUtils.class);

	private CommonUtils() {
	}

	public static Properties properties = null;
	private static CommonUtils instance = null;
	private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * @return CommonUtils
	 */
	public static CommonUtils getInstance() {
		if (instance == null) {
			instance = new CommonUtils();
		}
		return instance;
	}

	public String printTime(long startTime, String lable) {
		String time = "";
		try {
			long endTime = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			time = ((totalTime / 60000) % 60) + "m " + ((totalTime / 1000) % 60) + "s " + totalTime % 1000 + "ms";
			logger.warn("Total time for " + lable + ":" + ((totalTime / 60000) % 60) + "m " + ((totalTime / 1000) % 60) + "s " + totalTime % 1000 + "ms\n\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return time;
	}

	public Properties getProperties() {

		Properties properties = null;
		try {
			java.util.Locale myLocale = java.util.Locale.getDefault();
			PropertyResourceBundle resource = (PropertyResourceBundle) PropertyResourceBundle.getBundle("application", myLocale);
			properties = convertResourceBundleToProperties(resource);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return properties;

	}

	private Properties convertResourceBundleToProperties(ResourceBundle resource) {
		Properties properties = new Properties();

		Enumeration<String> keys = resource.getKeys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			properties.put(key, resource.getString(key));
		}

		return properties;
	}

	public String getFile(String fileName) {
		StringBuilder result = new StringBuilder("");

		// Get file from resources folder
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());

		try (Scanner scanner = new Scanner(file)) {

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				result.append(line).append("\n");
			}
			scanner.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return result.toString();
	}

	public int getNumber(String number) {
		int numeric = 0;
		try {
			numeric = Integer.parseInt(number);
		} catch (Exception e) {
			numeric = 0;
		}
		return numeric;
	}
	
	public String getTimeStamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return format.format(timestamp);
	}

}
