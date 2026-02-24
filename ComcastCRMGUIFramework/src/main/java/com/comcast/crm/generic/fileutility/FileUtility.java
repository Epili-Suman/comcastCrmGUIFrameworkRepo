package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtility 
{
	public String getDataFromPropertiesFile(String key) throws IOException
	{
		FileInputStream fis = new FileInputStream("configAppData/CommonData.properties");
		Properties property=new Properties();
		property.load(fis);
		return property.getProperty(key);		
	}
}