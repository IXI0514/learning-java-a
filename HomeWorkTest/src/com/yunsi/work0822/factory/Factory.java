package com.yunsi.work0822.factory;
/**
 * 
 * 	获取配置文件
 * @author ShenBL
 *
 */

import java.io.FileReader;
import java.util.Properties;

public class Factory {

	public String[] getProp() {
		String[] str = new String[4];
		Properties properties = new Properties();	
		try {
			FileReader fReader = new FileReader("../HomeWorkTest/src/com/yunsi/work0822/factory/properties.properties");
			properties.load(fReader);
			str[0] = properties.getProperty("path1");
			str[1] = properties.getProperty("path2");
			str[2] = properties.getProperty("fthreadnum");
			str[3] = properties.getProperty("zthreadnum");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	} 
}
