package org.huluobo.acm;

import java.util.Arrays;
import java.util.Scanner;

public class VijosP1059 {

	static int[] a = new int[110];// 每块积木的长度
	static int[] ans = new int[10010];// 能达到i高度的城堡有多少座
	static boolean[] hight = new boolean[10010];// 当前城堡能否达到高度i

	static Scanner sc = new Scanner(System.in);
	static int n = sc.nextInt();// 初始城堡个数

	public static void main(String[] args) {
		int i, t;

		for (t = 1; t <= n; ++t) {
			int j, m = 0;
			int maxhight = 0;// 这座城堡初始状态下最高多高；

			//每次循环都要将数组初始化为0 写在前面
			//初始化方法，类似于memset(hight, 0,hight.length);
			Arrays.fill(hight, false);
			
			hight[0] = true;
			int h = sc.nextInt();// 积木的棱长

			while (h != -1) {
				a[++m] = h;
				// hight[h]=1;//每一块高度都可能达到，错误，已经有的数的会加两次,如1,3,5.不可能使得hight[2]=1但hight[1]=1,执行hight[1+a[1]]=1
				maxhight += h;
				h = sc.nextInt();
			}

			//循环总的积木块数
			for (i = 1; i <= m; ++i) {
				for (j = maxhight - a[i]; j >= 0; --j)
					if (hight[j])
						hight[j + a[i]] = true;
			}
			for (i = 0; i <= maxhight; ++i)
				if (hight[i])
					ans[i]++;
		}
		// 所有塔都求完了之后
		for (i = 10005; i >= 0; --i)
			if (ans[i] == n) {
				System.out.println(i);
				break;
			}

	}
}
