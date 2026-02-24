package com.comcast.crm.generic.fileutility;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonUtility
{
	public String getDataFromJsonFile(String key) throws FileNotFoundException, IOException, ParseException
	{
		JSONParser json=new JSONParser();
		Object obj=json.parse(new FileReader("configAppData/appCommonData.json"));
		JSONObject jsonObj=(JSONObject) obj;
		return jsonObj.get(key).toString();
	}
}
