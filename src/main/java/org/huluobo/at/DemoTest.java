package org.huluobo.at;

import java.lang.reflect.Method;

public class DemoTest {

	public static void main(String[] args) throws Exception {
	
		Method method = Class.forName("org.huluobo.Abean").getDeclaredMethod("oneMethod");
		Huluobo huluobo = method.getAnnotation(Huluobo.class);

		System.out.println(huluobo.parameter1());
		System.out.println(huluobo.parameter2());
	}

}
