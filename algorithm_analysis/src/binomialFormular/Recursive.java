package binomialFormular;
import java.util.Scanner;
/**
 * 
 * @author Sun直接递归算法
 *
 */
public class Recursive {
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		System.out.println("请输入n");
		int n = input.nextInt();
		System.out.println("请输入k");
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
