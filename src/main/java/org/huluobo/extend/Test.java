package org.huluobo.extend;

public class Test {

	private int a=10;
	int b=20;
	static int c=1;
	public static void main(String[] args) {
		Test t=new Test();
		System.out.println(t.a);
		System.out.println(t.b);
		System.out.println(t.c);
		System.out.println(Test.c);
		
	}
}
