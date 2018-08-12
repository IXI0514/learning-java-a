package com.yunsi.work0806_studentManger.service.impl;

import java.util.Scanner;

import com.yunsi.work0806_studentManger.exception.HRManagerException;
import com.yunsi.work0806_studentManger.service.IHRManager;
import com.yunsi.work0806_studentManger.stu.Man;

public class HRManagerimpl implements IHRManager{

	public static final int DEFAULT_LEN=10;//public 可用以其他类的引用
	private Man[] men;
	private int idx = 0;
	Scanner sc = new Scanner(System.in);

	public HRManagerimpl() {
		this(DEFAULT_LEN);
	}

	public HRManagerimpl(int len) {// 通过len预设总人数
		men = new Man[len];
	}

	// 判断是否符合要求
	private  void exist(Man m,boolean t,boolean flag) throws HRManagerException {
		//m 为处理对象，t为处理编号重复(为false),flag传是否处理身份
		boolean g=false;
		boolean a=false; 
		if(idx>0) {
				for(int i=0;i<idx;i++) {
					if(m.getMid().equals(men[i].getMid())) {//查编号重复的
							t=!t;
						}
					if(m.getAge()>60) {//限制年龄
						g = true;
					}
					if (flag) {
						if(m.getIde().equals(men[i].getIde())) {//查身份是否相同
							a=true;
						}
					}	
				}
		}
		if(a) {
			throw new HRManagerException("编号["+m.getMid()+"]身份不同");
			}
		if(t) {
			throw new HRManagerException("编号["+m.getMid()+"]错误！！");
			}
		if(g) {
			throw new HRManagerException("编号["+m.getMid()+"]的年龄不达标：age="+m.getAge());
			}
	}//end exist

	// 存到数组中
	/* (non-Javadoc)
	 * @see com.yunsi.service.impl.IHRManager#add(com.yunsi.stu.Man)
	 */
	@Override
	public void add(Man m) {
		//数组扩容
		
		if(idx>=men.length) {
			Man[] mans=new Man[this.men.length*2];
			for(int i=0;i<this.men.length;i++) {
				mans[i]=men[i];
			}
			this.men=mans;
			System.out.println("数组已扩容,当前长度： "+men.length);
		}
		// 判断是否重复
		try {
			this.exist(m,false,false);
			this.men[idx] = m;
			idx++;
			System.out.println("--编号[" + m.getMid() + "]添加成功--");
		}catch(HRManagerException e) {
			e.printStackTrace();	
		}
		
	}//end add

	
	//查找
	/* (non-Javadoc)
	 * @see com.yunsi.service.impl.IHRManager#find(java.lang.String)
	 */
	@Override
	public void find(String mid) {
		for (int i = 0; i < idx; i++) {
			if (men[i].getMid().equals(mid)) {// 遍历数组匹配mid
				System.out.println("找到编号是：[" + mid + "]的用户");
				System.out.println("是否显示其信息？  Y/N");
				String y = sc.next();
				// System.out.println(y.equalsIgnoreCase("Y"));
				if (y.equalsIgnoreCase("Y")) {
					men[i].show();
				}
				break;
			}
		}//end for
	}//end find
	
	
	// 删除
	/* (non-Javadoc)
	 * @see com.yunsi.service.impl.IHRManager#delete(com.yunsi.stu.Man)
	 */
	@Override
	public void delete(Man m ) {
		try {
			this.exist(m,true,false);
			for (int i=0;i<idx;i++) {
				if (men[i].getMid().equals(m.getMid())) {
					System.out.println("找到编号是：[" + m.getMid() + "]的成员");
					men[i].show();
					System.out.println("是否删除其信息？  Y/N");
					String y = sc.next();
					if (y.equalsIgnoreCase("Y")) {
						for (int j = i; j <idx-1; j++) {
							men[i]=men[i+1];	
						}
						System.out.println("已删除[ "+m.getMid()+" ]成员!!");
						idx--;
						break;
					} 
				}
			}
		} catch (HRManagerException e) {
			e.printStackTrace();
		}
		
	}//end delete
	
	//更新
	
	/* (non-Javadoc)
	 * @see com.yunsi.service.impl.IHRManager#update(com.yunsi.stu.Man)
	 */
	@Override
	public void update(Man m) {
		
		try {
			this.exist(m, true,true);
			for (int i=0;i<idx;i++) {
				if (men[i].getMid().equals(m.getMid())) {// 遍历数组匹配mid
					men[i]=m;
					System.out.println("--编号["+m.getMid()+"]更新完毕--");
					break;
				}
			}
		} catch (HRManagerException e) {
			e.printStackTrace();
		}
		
	}//end updata
	
	
	//显示全部信息
	/* (non-Javadoc)
	 * @see com.yunsi.service.impl.IHRManager#show()
	 */
	@Override
	public void show() {
		if(idx>0) {
			System.out.println("\n--显示全部信息:\n");
			for(int i=0;i<idx;i++) {
				men[i].show();
				System.out.println();
			}
		}
	}
}// end IHR
