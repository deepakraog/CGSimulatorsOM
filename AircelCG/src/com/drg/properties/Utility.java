package com.drg.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utility {
	Properties props = null;

	public void load() throws IOException {

		File configDir = new File(System.getProperty("catalina.base"), "conf");
		File configFile = new File(configDir, "urlConfig.properties");
		InputStream stream = new FileInputStream(configFile);
		props = new Properties();
		props.load(stream);
	}

	public Object get(String key) throws IOException {
		if (props == null) {
			load();
		}
		return props.get(key);
	}
}