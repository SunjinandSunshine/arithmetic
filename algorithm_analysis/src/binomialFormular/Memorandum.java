package binomialFormular;
import java.util.Scanner;
/**
 * 
 * @author Sun备忘录算法
 *
 */
public class Memorandum {
	private static int binomial[][];
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		System.out.println("请输入n");
		int n = input.nextInt();
		System.out.println("请输入k");
		int k = input.nextInt();
		binomial = new int[n+1][k+1];
		//initialize binomial
		for(int i=0;i<n+1;i++){
			for(int j=0;j<k+1;j++){
				binomial[i][j] = -1;
			}
		} 
		System.out.println(getCalculate(n,k));
		input.close();
	}
	private static int getCalculate(int n, int k) {
			int value;
			if(k==0||k==n){
				value = 1;
			}
			else if(binomial[n][k]!=-1){//判断是否已经运算过。
				value = binomial[n][k];
			}else{//如果没有计算过则重新计算
				value = getCalculate(n-1,k)+getCalculate(n-1,k-1);
			}
			binomial[n][k] = value; //记录C(n,k)
			return binomial[n][k];
	}
 }
