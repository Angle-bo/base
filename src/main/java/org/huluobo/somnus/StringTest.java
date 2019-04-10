package org.huluobo.somnus;

public class StringTest {

	public static void main(String[] args) {
		String a="hello2";
		final String b="hello";
		
		String c="hello"+2;
		System.out.println(a==c);
		
		String d=b+2;
		System.out.println(a==d);
		System.out.println(a.equals(d));
	}

}
