package org.huluobo.acm;

import java.util.Scanner;

public class VijosP1097 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();// 初始堆数
		int sum = 0;
		int[] num = new int[n];
		for (int i = 0; i < n; i++) {
			num[i] = sc.nextInt();
		}

		int t = n - 1;// 需要搬n-1次
		int min1 = 0;// 最小值下标
		int min2 = 1;// 次小值下标
		while (t-- > 0) {
			for (int i = 0; i < n; i++) {
				if (num[i] < num[min1]) {
					min2 = min1;
					min1 = i;
				} else if (num[i] >= num[min1] && num[i] < num[min2] && i != min1)
					min2 = i;
			}
			sum += num[min1] + num[min2];
			num[min2] += num[min1]; // 把次小值更新为和
			num[min1] = 99999999; // 把最小值标记为足够大的数
		}

		System.out.println(sum);
	}

}
