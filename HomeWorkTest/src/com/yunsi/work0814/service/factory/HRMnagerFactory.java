package com.yunsi.work0814.service.factory;


import com.yunsi.work0814.exception.HRMException;
import com.yunsi.work0814.service.HRManagerIF;
import com.yunsi.work0814.service.impl.DiskHRManagerimpl;
import com.yunsi.work0814.service.impl.MenHRManagerimpl;

public class HRMnagerFactory {
	
	
	public static HRManagerIF autofactory() throws HRMException  {
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
		
	}
	
}
