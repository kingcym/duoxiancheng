package com.example.demo.shujujiegou.ch哈希表;


import java.math.BigInteger;

/**
 * * @Description: 开放地址法
 * 当hashcode冲突发生，寻找数组的空位，将数值插入
 */
public class KaifangdizhiHashTable {
	private Info[] arr;

	/**
	 * 默认的构造方法
	 */
	public KaifangdizhiHashTable() {
		arr = new Info[100];
	}

	/**
	 * 指定数组初始化大小
	 */
	public KaifangdizhiHashTable(int maxSize) {
		arr = new Info[maxSize];
	}

	/**
	 * 插入数据
	 */
	public void insert(Info info) {
		//获得关键字
		String key = info.getKey();
		//关键字所自定的哈希数
		int hashVal = hashCode(key);
		//如果这个索引已经被占用，而且里面是一个未被删除的数据
		while(arr[hashVal] != null && arr[hashVal].getName() != null) {
			//进行递加
			++hashVal;
			//循环
			hashVal %= arr.length;
		}
		arr[hashVal] = info;
	}

	/**
	 * 查找数据
	 */
	public Info find(String key) {
		int hashVal = hashCode(key);
		while(arr[hashVal] != null) {
			if(arr[hashVal].getKey().equals(key)) {
				return arr[hashVal];
			}
			++hashVal;
			hashVal %= arr.length;
		}
		return null;
	}

	public int hashCode(String key) {
		BigInteger hashVal = new BigInteger("0");
		BigInteger pow27 = new BigInteger("1");
		for(int i = key.length() - 1; i >= 0; i--) {
			int letter = key.charAt(i) - 96;
			BigInteger letterB = new BigInteger(String.valueOf(letter));
			hashVal = hashVal.add(letterB.multiply(pow27));
			pow27 = pow27.multiply(new BigInteger(String.valueOf(27)));
		}
		return hashVal.mod(new BigInteger(String.valueOf(arr.length))).intValue();
	}
}
