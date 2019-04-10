package org.huluobo;

import java.util.concurrent.ConcurrentHashMap;

public class Peach {

	static int total=1;
	
	public static void main(String[] args) {
		//果园里有一堆桃子，和一只猴子，这只猴子第一天把桃子吃了一半，扔掉了一个坏掉的；
		//第二天又吃了剩下的一半又扔掉了个一个坏掉的；以后每天如此，到第七天还没开始吃就剩一只桃子了。
		//请问最初果园里有多少只桃子？

		int num=1;
		for(int i=6;i>0;i--) {
			num=(num+1)*2;
		}
		System.out.println(num);
		//d1 190 95 -1 94
		//d2 94  47 -1 46
		//d3 46  23 -1 22
		//d4 22  11 -1 10
		//d5 10  5  -1 4
		//d6 4   2  -1 1
		
		System.out.println(getPeach(6));
		
		System.out.println(getP(5));
	}
	
	public static int getPeach(int days) {
		if(days>0) {
			total=(getPeach(days-1)+1)*2;
		}
		return total;
	}
	
	public static int getP(int p) {
		if(p>1) {
			p=p*getP(p-1);
		}
		return p;
	}
}
