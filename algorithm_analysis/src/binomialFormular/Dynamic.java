package binomialFormular;
import java.util.Scanner;
/**
 * 
 * @author Sun动态规划
 *
 */
public class Dynamic {
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		System.out.println("请输入n");
		int n = input.nextInt();
		System.out.println("请输入k");
		int k = input.nextInt();
		int[][] binomial = new int[n+1][k+1];
		System.out.println(getCalculate(n,k,binomial));
		input.close();
	}
	private static int getCalculate(int n, int k,int[][] binomial) {
		for(int i=0;i<n+1;i++){
			for(int j=0;j<k+1;j++){
				if(i>=j){
				if(i==0){
					binomial[0][j]=0;
				}
				else if((i==j  && i!=0 && j!=0) || (j==0&&i!=0)){
					binomial[i][j] = 1;
				 }
			 }
				else{
					binomial[i][j]=0;
				}
			}
		}
		for(int i=1;i<n+1;i++){
			for(int j=1;j<k+1;j++){
				if(i == j){
					binomial[i][j] = 1;
				}
				if(i>j){
				binomial[i][j] = binomial[i-1][j] + binomial[i-1][j-1];
			 }
			}
		}
		return binomial[n][k];
	}
}
