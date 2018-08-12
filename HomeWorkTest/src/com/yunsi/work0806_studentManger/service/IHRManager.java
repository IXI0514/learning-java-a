package com.yunsi.work0806_studentManger.service;

import com.yunsi.work0806_studentManger.stu.Man;

public interface IHRManager {

	// 存到数组中
	void add(Man m);//end add

	//查找
	void find(String mid);//end find

	// 删除
	void delete(Man m);//end delete

	//更新
	//void reIde(Man m) throws HRManagerException;//end reIde

	void update(Man m);//end updata

	//显示全部信息
	void show();

}