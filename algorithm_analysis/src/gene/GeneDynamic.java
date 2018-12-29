package gene;
import java.util.Scanner;
/**
 * 
 * @author Sun
 * d[i][j]=max{ d[i-1][j-1]+cost(a[i],b[j]),d[i][j-1]+cost(‘-’,b[j]),d[i-1][j]+cost(a[i],’-’) }
 */
public class GeneDynamic {
	private static int[][] w={
								{5,-1,-2,-1,-3},
							    {-1,5,-3,-2,-4},
							    {-2,-3,5,-2,-2},
							    {-1,-2,-2,5,-1},
						        {-3,-4,-2,-1,-1<<30}};//w是匹配度數組
	private static String a;
	private static String b;
	private static int[][] d;//d是动态规划数组
	private static int[]   h = new int[333];
	public static void main(String[] args){
    h['A']=0; h['C']=1; h['G']=2; h['T']=3; h['-']=4;
	Scanner input = new Scanner(System.in);
	System.out.println("请输入第一个基因序列");
	a = input.nextLine();
	String aNew = getNewString(a);
	System.out.println("请输入第二个基因序列");
	b = input.nextLine();
	String bNew = getNewString(b);
	d = new int[a.length()+1][b.length()+1];
	input.close();
	for(int i=1;i<=a.length();i++) {  d[i][0]=d[i-1][0]+cost(aNew.charAt(i),'-'); }
	for(int j=1;j<=b.length();j++) {  d[0][j]=d[0][j-1]+cost('-',bNew.charAt(j)); }
	for(int i=1;i<=a.length();i++){
		for(int j=1;j<=b.length();j++){
			 d[i][j]=max(d[i-1][j-1]+cost(aNew.charAt(i),bNew.charAt(j)),(d[i-1][j]+cost(aNew.charAt(i),'-'))
					 ,(d[i][j-1]+cost(bNew.charAt(j),'-')));
		}
	 }
	System.out.println(d[a.length()][b.length()]);
	}
	private static int max(int i, int j, int k) {
		int max = i;
		if(i<=j)
			max = j;
		if(max<=k)
			max = k;
		return max;
	}
	private static int cost(char x,char y){
		return w[h[x]][h[y]];
	}
	public static String getNewString(String s){
		String s1 = "0";
		return s1+s;
	}
}
