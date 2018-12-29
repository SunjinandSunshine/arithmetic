package intervalScheduling;
/**
 * 
 * @author Sun
 * 动态规划,其递推式为：
 * M[i]=max(wi+M[pi],M[i-1])
 */
public class IntervalScheduling {

	private static int[][] schedul ={{0},
			                         {4,2,1},
			                         {2,1},
			                         {2,2,1},
			                         {0},
			                         {2,4,4},
			                         {2,4,4}};;
	private static int[] cost = {0,2,4,4,7,2,1};
//	private static int[][] schedul ={{0},{1},{0},{1}};;
//private static int[] cost = {0,1,3,1};
	private static int[] F = new int[schedul.length];
	public static void main(String[] args){
		F[0] = 0;
		for(int i=1;i<F.length;i++){
			F[i] = max(F[i-1],cost[i]+max(schedul[i]));
		}
		System.out.println(F[schedul.length-1]);
	}
	private static int max(int i, int j) {
		return i>j?i:j;
	}
	private static int max(int[] is) {
		int max=0;
		for(int i=0;i<is.length;i++){
			if(max<=is[i]){
				max = is[i];
			}
		}
		return max;
	}
}
