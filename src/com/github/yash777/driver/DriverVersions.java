package com.github.yash777.driver;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import com.github.yash777.files.FilesActions;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class DriverVersions {
	static HashMap<Integer, String> chrome = new HashMap<Integer, String>();
	static HashMap<Integer, String> firefox = new HashMap<Integer, String>();
	static HashMap<Integer, String> opera = new HashMap<Integer, String>();
	
	static JsonParser parser = new JsonParser();
	static String versionMappingFileURL = 
			"https://raw.githubusercontent.com/Yash-777/SeleniumDriverAutomation/master/Drivers/versions-mapping";
	
	public static void loadDefaultVersion() {
		// https://chromedriver.storage.googleapis.com/2.35/notes.txt
		chrome.put(64, "2.35");
		chrome.put(63, "2.35");
		chrome.put(62, "2.35");
		chrome.put(61, "2.34");
		chrome.put(60, "2.33");
		chrome.put(59, "2.32");
		chrome.put(58, "2.31");
		chrome.put(57, "2.29");
		chrome.put(56, "2.29");
		chrome.put(55, "2.28");
		chrome.put(54, "2.27");
		chrome.put(53, "2.26");
		chrome.put(52, "2.24");
		chrome.put(51, "2.23");
		chrome.put(50, "2.22");
		chrome.put(49, "2.22");
		chrome.put(48, "2.21");
		chrome.put(47, "2.21");
		chrome.put(46, "2.21");
		chrome.put(45, "2.20");
		chrome.put(44, "2.20");
		chrome.put(43, "2.20");
		chrome.put(42, "2.16");
		chrome.put(41, "2.15");
		chrome.put(40, "2.15");
		
		// https://github.com/mozilla/geckodriver/releases/
		firefox.put(57, "v0.19.1");
		firefox.put(56, "v0.19.1");
		firefox.put(55, "v0.19.1");
		firefox.put(54, "v0.19.1"); // Selenium 3.5 (and greater)
		firefox.put(53, "v0.18.0");
		firefox.put(52, "v0.17.0");
		firefox.put(51, "v0.17.0");
		firefox.put(50, "v0.16.0"); // Selenium 3.4 and greater.
		
		// https://github.com/operasoftware/operachromiumdriver/releases
		opera.put(28, "v0.2.2");
		opera.put(29, "v0.2.2");
		
		opera.put(45, "v.2.29");
		opera.put(46, "v.2.29");
		opera.put(47, "v.2.29");
		opera.put(48, "v.2.30");
		opera.put(49, "v.2.32");
		opera.put(50, "v.2.33");
	}
	
	public static void main(String[] args) throws IOException {
		// http://jsonviewer.stack.hu/
		
		String urlStr = "https://raw.githubusercontent.com/Yash-777/SeleniumDriverAutomation/master/Drivers/versions-mapping";
		String jsonStr = FilesActions.readCloudFileAsString( urlStr );
		System.out.println( jsonStr );
		
		new DriverVersions().json_UnKnown_Format( jsonStr );
	}
	
	public void json_UnKnown_Format( String jsonStr ) {
		
		if( jsonStr == null ) {
			loadDefaultVersion();
		} else {
			JsonObject object = (JsonObject) parser.parse( new StringReader( jsonStr ) );
			System.out.println( object );
			
			Set <java.util.Map.Entry<String, com.google.gson.JsonElement>> keys = object.entrySet();
			if ( keys.isEmpty() ) {
				System.out.println( "Empty JSON Object" );
				loadDefaultVersion();
			} else {
				for (Entry<String, JsonElement> entry : keys) {
					String keyEntry = entry.getKey();
					System.out.println(keyEntry + " : ");
					JsonElement valuesEntry =  entry.getValue();
					if (valuesEntry.isJsonNull()) {
						System.out.println("Version value : "+ valuesEntry);
					} else if (valuesEntry.isJsonObject()) {
						JsonObject obj = (JsonObject) parser.parse(valuesEntry.toString());
						Set <java.util.Map.Entry<String, JsonElement>> obj_key = obj.entrySet();
						if( keyEntry.toString().equalsIgnoreCase("CHROME") ) {
							browserVersions(obj_key, chrome );
						} else if(  keyEntry.toString().equalsIgnoreCase("FIREFOX") ) {
							browserVersions(obj_key, firefox );
						} else if( keyEntry.toString().equalsIgnoreCase("OPERA") ) {
							browserVersions(obj_key, opera );
						}
					}
				}
			}
		}
	}
	
	public void browserVersions( Set <Entry<String, JsonElement>> keys, HashMap<Integer, String> browser ) {
		for (Entry<String, JsonElement> entry : keys) {
			String keyEntry = entry.getKey();
			JsonElement valuesEntry =  entry.getValue();
			if (valuesEntry.isJsonNull()) {
				System.out.println(valuesEntry);
				browser.put(Integer.valueOf( keyEntry ) , valuesEntry.toString().replaceAll("\"|\'", ""));
			} else if (valuesEntry.isJsonPrimitive()) {
				browser.put(Integer.valueOf( keyEntry ) , valuesEntry.toString().replaceAll("\"|\'", ""));
			}
		}
	}
}