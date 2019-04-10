package org.huluobo.acm;

import java.util.Scanner;

/**
 * 
 * @author G8670
 *
 */
public class VijosP1011 {
	static Scanner sc=new Scanner(System.in);
	static int r=sc.nextInt();//行数
	static int c=sc.nextInt();//列数
	static int[][]arr=new int[r][c];//高度
	static int[][]dis=new int[r][c];//当前位置的最优滑行距离
	
	public static void main(String[] args) {
		for(int i=0;i<r;i++){
			for(int j=0;j<c;j++){
				arr[i][j]=sc.nextInt();
				dis[i][j]=-1;
			}
		}
		int temp=0;
		for(int i=0;i<r;i++){
			for(int j=0;j<c;j++){
				getDis(i,j);
				if(dis[i][j]>temp)
					temp=dis[i][j];
			}
		}
		System.out.println(temp);
	}
	static int getDis(int x,int y){
		if(dis[x][y]!=-1){
			return dis[x][y];
		}else{
			int aa=0;//记录该点上面的点的dis值
			if(x>0&&arr[x][y]>arr[x-1][y]){//当前行不是第一行且上面元素的高度比当前元素的高度低
				dis[x-1][y]=getDis(x-1,y);
				aa=dis[x-1][y]+1;
			}
			int bb=0;//记录该点下面的点的dis值
			if(x<r-1&&arr[x][y]>arr[x+1][y]){//当前行不是最后一行且下面元素的高度比当前元素的高度低
				dis[x+1][y]=getDis(x+1,y);
				bb=dis[x+1][y]+1;
			}
			int cc=0;//left
			if(y>0&&arr[x][y]>arr[x][y-1]){//
				dis[x][y-1]=getDis(x,y-1);
				cc=dis[x][y-1]+1;
			}
			int dd=0;//right
			if(y<c-1&&arr[x][y]>arr[x][y+1]){
				dis[x][y+1]=getDis(x,y+1);
				dd=dis[x][y+1]+1;
			}
			if(aa==0&&bb==0&&cc==0&&dd==0){
				dis[x][y]=1;
				return 1;
			}else{
				dis[x][y]=max(max(aa,bb),max(cc,dd));
				return dis[x][y];	
			}
		}
	}
	static int max(int a,int b){
		if(a>b){
			return a;
		}else{
			return b;
		}
	}
}
