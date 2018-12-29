package binomialFormular;
import java.util.Scanner;
/**
 * 
 * @author Sun����¼�㷨
 *
 */
public class Memorandum {
	private static int binomial[][];
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		System.out.println("������n");
		int n = input.nextInt();
		System.out.println("������k");
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
			else if(binomial[n][k]!=-1){//�ж��Ƿ��Ѿ��������
				value = binomial[n][k];
			}else{//���û�м���������¼���
				value = getCalculate(n-1,k)+getCalculate(n-1,k-1);
			}
			binomial[n][k] = value; //��¼C(n,k)
			return binomial[n][k];
	}
 }
