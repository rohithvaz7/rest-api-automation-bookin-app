package utility;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Properties;


public class FrameworkUtility {
	
	protected static Properties properties;

	public static String readConfigurationFile(String key) {
		try{
			properties = new Properties();
			properties.load(new FileInputStream(FrameworkConstants.CONFIG_FILE_PATH));
			
		} catch (Exception e){
			System.out.println("Cannot find key: "+key+" in Config file due to exception : "+e);
		}
		return properties.getProperty(key).trim();	
	}
	
	public static JSONObject returDefaultPayLoadObject(String filePath) {
		// To get the JSON request from external file			
		JSONParser parser = new JSONParser();
		Object obj = null;
		try {
			obj = parser.parse(new FileReader(filePath));
		} catch (Exception e) {
			AllureLogger.logToAllure("Error in JSON object parsing with exception : "+e);
			
		}
		JSONObject jsonObject = (JSONObject) obj;
		return jsonObject;
	}


   //printing the json response
	public void logResponseAsString(Response response) {
		AllureLogger.logToAllure(response.asString());
		System.out.println(response.asString());
		
	}
}
