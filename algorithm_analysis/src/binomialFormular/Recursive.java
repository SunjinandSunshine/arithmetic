package binomialFormular;
import java.util.Scanner;
/**
 * 
 * @author Sunֱ�ӵݹ��㷨
 *
 */
public class Recursive {
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		System.out.println("������n");
		int n = input.nextInt();
		System.out.println("������k");
		int k = input.nextInt();
		System.out.println(calculate(n,k));
		input.close();
	}
	private static int calculate(int n, int k) {
		if(n==k)//if n equals k,only one choice
			return 1;
		else if(k==1)//if k equals 1,n choices.
			return n;
		else
			return calculate(n-1,k)+calculate(n-1,k-1);
	}
}
