package com.yunsi.work0806_studentManger.service.impl;

import java.util.Scanner;

import com.yunsi.work0806_studentManger.exception.HRManagerException;
import com.yunsi.work0806_studentManger.service.IHRManager;
import com.yunsi.work0806_studentManger.stu.Man;

public class HRManagerimpl implements IHRManager{

	public static final int DEFAULT_LEN=10;//public �����������������
	private Man[] men;
	private int idx = 0;
	Scanner sc = new Scanner(System.in);

	public HRManagerimpl() {
		this(DEFAULT_LEN);
	}

	public HRManagerimpl(int len) {// ͨ��lenԤ��������
		men = new Man[len];
	}

	// �ж��Ƿ����Ҫ��
	private  void exist(Man m,boolean t,boolean flag) throws HRManagerException {
		//m Ϊ�������tΪ�������ظ�(Ϊfalse),flag���Ƿ������
		boolean g=false;
		boolean a=false; 
		if(idx>0) {
				for(int i=0;i<idx;i++) {
					if(m.getMid().equals(men[i].getMid())) {//�����ظ���
							t=!t;
						}
					if(m.getAge()>60) {//��������
						g = true;
					}
					if (flag) {
						if(m.getIde().equals(men[i].getIde())) {//������Ƿ���ͬ
							a=true;
						}
					}	
				}
		}
		if(a) {
			throw new HRManagerException("���["+m.getMid()+"]��ݲ�ͬ");
			}
		if(t) {
			throw new HRManagerException("���["+m.getMid()+"]���󣡣�");
			}
		if(g) {
			throw new HRManagerException("���["+m.getMid()+"]�����䲻��꣺age="+m.getAge());
			}
	}//end exist

	// �浽������
	/* (non-Javadoc)
	 * @see com.yunsi.service.impl.IHRManager#add(com.yunsi.stu.Man)
	 */
	@Override
	public void add(Man m) {
		//��������
		
		if(idx>=men.length) {
			Man[] mans=new Man[this.men.length*2];
			for(int i=0;i<this.men.length;i++) {
				mans[i]=men[i];
			}
			this.men=mans;
			System.out.println("����������,��ǰ���ȣ� "+men.length);
		}
		// �ж��Ƿ��ظ�
		try {
			this.exist(m,false,false);
			this.men[idx] = m;
			idx++;
			System.out.println("--���[" + m.getMid() + "]��ӳɹ�--");
		}catch(HRManagerException e) {
			e.printStackTrace();	
		}
		
	}//end add

	
	//����
	/* (non-Javadoc)
	 * @see com.yunsi.service.impl.IHRManager#find(java.lang.String)
	 */
	@Override
	public void find(String mid) {
		for (int i = 0; i < idx; i++) {
			if (men[i].getMid().equals(mid)) {// ��������ƥ��mid
				System.out.println("�ҵ�����ǣ�[" + mid + "]���û�");
				System.out.println("�Ƿ���ʾ����Ϣ��  Y/N");
				String y = sc.next();
				// System.out.println(y.equalsIgnoreCase("Y"));
				if (y.equalsIgnoreCase("Y")) {
					men[i].show();
				}
				break;
			}
		}//end for
	}//end find
	
	
	// ɾ��
	/* (non-Javadoc)
	 * @see com.yunsi.service.impl.IHRManager#delete(com.yunsi.stu.Man)
	 */
	@Override
	public void delete(Man m ) {
		try {
			this.exist(m,true,false);
			for (int i=0;i<idx;i++) {
				if (men[i].getMid().equals(m.getMid())) {
					System.out.println("�ҵ�����ǣ�[" + m.getMid() + "]�ĳ�Ա");
					men[i].show();
					System.out.println("�Ƿ�ɾ������Ϣ��  Y/N");
					String y = sc.next();
					if (y.equalsIgnoreCase("Y")) {
						for (int j = i; j <idx-1; j++) {
							men[i]=men[i+1];	
						}
						System.out.println("��ɾ��[ "+m.getMid()+" ]��Ա!!");
						idx--;
						break;
					} 
				}
			}
		} catch (HRManagerException e) {
			e.printStackTrace();
		}
		
	}//end delete
	
	//����
	
	/* (non-Javadoc)
	 * @see com.yunsi.service.impl.IHRManager#update(com.yunsi.stu.Man)
	 */
	@Override
	public void update(Man m) {
		
		try {
			this.exist(m, true,true);
			for (int i=0;i<idx;i++) {
				if (men[i].getMid().equals(m.getMid())) {// ��������ƥ��mid
					men[i]=m;
					System.out.println("--���["+m.getMid()+"]�������--");
					break;
				}
			}
		} catch (HRManagerException e) {
			e.printStackTrace();
		}
		
	}//end updata
	
	
	//��ʾȫ����Ϣ
	/* (non-Javadoc)
	 * @see com.yunsi.service.impl.IHRManager#show()
	 */
	@Override
	public void show() {
		if(idx>0) {
			System.out.println("\n--��ʾȫ����Ϣ:\n");
			for(int i=0;i<idx;i++) {
				men[i].show();
				System.out.println();
			}
		}
	}
}// end IHR
