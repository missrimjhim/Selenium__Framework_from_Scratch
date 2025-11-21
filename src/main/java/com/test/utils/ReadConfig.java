package com.test.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {
	private Properties properties;
	
	public ReadConfig() {
		try {
			FileInputStream fis= new FileInputStream("Configuration//config.properties");
			properties=new Properties();
			properties.load(fis);
			
		} catch(IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to load config.properties file");
			}
		
	}
	
	public String getProperty(String key) {
		return properties.getProperty(key);
	}
	
	public String getBrowser() {
		return properties.getProperty("browser");
	}
	public String getUrl()
	{
		return properties.getProperty("url");
	}
	public String getApplication()
	{
		return properties.getProperty("application");
	}
	

}
