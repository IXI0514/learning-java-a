package com.yunsi.work0814.service.factory;



import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.util.Properties;

import com.yunsi.work0814.service.HRManagerIF;


public class HRMnagerFactory {
	//2018年8月22日09:00:23 改读取文件
	public static HRManagerIF autofactory() {
		
		try {
			Properties properties = new Properties();
			FileReader fReader = new FileReader("../HomeWorkTest/src/com/yunsi/work0814/service/factory/properties.properties");
			properties.load(fReader);
			String str1 = properties.getProperty("impl");
			Class class1 = Class.forName(str1);
			Constructor<HRManagerIF> constructor = class1.getConstructor();
			HRManagerIF object = constructor.newInstance();
			System.out.println(object);
			return object;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Class class1 = Class.forName(className)
		return null;
	}
	
	
	
	
/*	简单的工厂
 * public static HRManagerIF autofactory() throws HRMException  {
		return autofactory("memory");
	}

	public static HRManagerIF autofactory(String type) throws HRMException{
		
		if (type==null) {
			throw new HRMException("尚未初始化存储方法！！");
		}
		if(type.equals("memory")) {
			System.out.println("当前存储方式："+type);
			return new MenHRManagerimpl();
		}else if(type.equals("disk")){
			System.out.println("当前存储方式："+type);
			return new DiskHRManagerimpl();
		}else {
			throw new HRMException("初始化存储方式错误！！");
		}
		
	}*/
	/*//改用反射
	public static HRManagerIF autofactory() {
		return auto("memoryset");
		
	}
	private static HRManagerIF auto(String type){
		if (type!=null) {
			if(type.equals("disk")) {
				return (HRManagerIF)getObject("com.yunsi.work0814.service.impl.DiskHRManagerimpl");
			}
			if(type.equals("memorylist")) {
				return (HRManagerIF)getObject("com.yunsi.work0814.service.impl.MenListHRManagerimpl");
			}
			if(type.equals("memoryset")) {
				return (HRManagerIF)getObject("com.yunsi.work0814.service.impl.MenSetHRManagerimpl");
			}
		}
		return null;
	}
	private static Object getObject(String type) {
			
			try {
				Class class1 = Class.forName(type);
				Constructor constructor = class1.getDeclaredConstructor(new Class[] {});
				Object instance = constructor.newInstance(new Object[] {});
				return instance;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null; 
			
	}*/
}
