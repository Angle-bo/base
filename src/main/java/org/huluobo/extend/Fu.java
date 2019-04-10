package org.huluobo.extend;

public class Fu {
	Fu(int i) {
		System.out.println("父类的构造函数"+1);
	}

	public static void main(String[] args) {
		Zi zi = new Zi(1);

	}
}

class Zi extends Fu {
	Zi(int i) {
		super(i);
		System.out.println("子类的构造函数"+i);
	}
}
