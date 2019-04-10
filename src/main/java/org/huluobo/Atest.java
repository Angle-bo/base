package org.huluobo;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;


public class Atest {
	public static  void main(String[] args) {
		Pers p=new Pers(); 
		p.setAge(1);
		p.setName("123");
		setname(p);
		System.out.println(p.getAge());
		System.out.println(p.getName());
		
		String s="10.1.1.1.1.1.1";
		String[] string=s.split("\\.");
		int  intNum =Integer.parseInt(string[string.length-1]);
		//chars[chars.length-1]=(char) ('0' + (intNum+1));
		string[string.length-1]=Integer.toString((intNum+1));
		
		StringBuffer sb=new StringBuffer();
		for(int i=0; i<string.length; i++){
			sb.append(string[i]);
			if(i!=(string.length-1)) {
				sb.append(".");
			}
		}
		
		System.out.println(sb.toString());
		System.out.println(StringUtils.join(string,"."));
	}
	
	public static void setname(Pers p) {
		p.setAge(2);
		p.setName("456");
	}
}

class Pers{
	private String name;
	
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
}