package com.yunsi.work0806_studentManger.service;

import com.yunsi.work0806_studentManger.stu.Man;

public interface IHRManager {

	// �浽������
	void add(Man m);//end add

	//����
	void find(String mid);//end find

	// ɾ��
	void delete(Man m);//end delete

	//����
	//void reIde(Man m) throws HRManagerException;//end reIde

	void update(Man m);//end updata

	//��ʾȫ����Ϣ
	void show();

}