package arithmetic;
/**
 * @author Sun
 */
import java.util.*;
public class Arithmetic {
	public Map map = new Map();
	public List<Vertex> cost = new ArrayList<Vertex>();
	public List<Vertex> parent = new ArrayList<Vertex>();
	public int[] pdex = new int[30];
	public float length = 0;
	public Arithmetic() {
		this.buildTestMap();
	}
	public Arithmetic(Map map) {
		this.map = map;
	}
	public void buildTestMap() {
		String name[] = { "a", "b", "c", "d" };
		float freq[] = { 1.1f, 1.4f, 0.9f, 1.8f };
		float length[][] = { { 0, 1.0f, 3.0f, -1 }, { 7.0f, 0, -1, 2.0f },
				{ 4.0f, -1, 0, 5.0f }, { -1, 3.0f, -1, 0 } };
		map = new Map(name, freq, length);
	}
	// 两点的最佳路径
	public int[] getOptimalPath(int x, int y) {
		List<Edge> path = new ArrayList<Edge>();
		// 初始化cost、parent集
		cost.add(map.vertex.get(x));
		for (int i = 0; i < map.vertex.size(); i++) {
			parent.add(map.vertex.get(i));
		}
		parent.remove(map.vertex.get(x));
		// 初始化标记
		ArrayList<Edge> len = new ArrayList<Edge>();
		for (int i = 0; i < map.edge.get(x).size(); i++) {
			len.add(map.edge.get(x).get(i));
		}
		// 循环更新标记
		Edge min = new Edge(-1, -1, -1);
		while (min.arrive != y) {
			min = getMinLength(len);
			path.add(min);
			cost.add(map.vertex.get(min.arrive));
			parent.remove(map.vertex.get(min.arrive));
			len.remove(min);
			for (int j = 0; j < map.edge.get(min.arrive).size(); j++) {
				if (parent.contains(map.vertex.get(map.edge.get(min.arrive)
						.get(j).arrive))) {
					len.add(map.edge.get(min.arrive).get(j));
				}
			}
		}
		// 取得最优路径
		Edge cur = null;
		int j = 0;
		for (int i = path.size() - 1; i >= 0; i--) {
			if (i != 0) {
				if (cur == null) {
					if (path.get(i).getOwn() == path.get(i - 1).getArrive()) {
						pdex[j++] = path.get(i).getArrive();
						length = length + path.get(i).getLength();
						cur = null;
					} else {
						cur = path.get(i);
					}
				} else {
					if (cur.getOwn() == path.get(i - 1).getArrive()) {
						pdex[j++] = cur.arrive;
						length = length + cur.getLength();
						cur = null;
					}
				}
			} else {
				if (cur == null) {
					pdex[j++] = path.get(i).getArrive();
					pdex[j++] = path.get(i).getOwn();
					length = length + path.get(i).getLength();
				} else {
					pdex[j++] = cur.getArrive();
					pdex[j++] = cur.getOwn();
					length = length + cur.getLength();
				}
			}
		}
		int[] result = new int[j];
		for (int i = 0, k = j - 1; i < j; i++, k--) {
			result[k] = pdex[i];
		}
		return result;
	}
	// 离 Cost 最近的相邻节点
	private Edge getMinLength(List<Edge> len) {
		Edge edge = len.get(0);
		float minlen = len.get(0).getLength();
		for (int i = 1; i < len.size(); i++) {
			if (minlen > len.get(i).getLength()) {
				minlen = len.get(i).getLength();
				edge = len.get(i);
			}
		}
		return edge;
	}
	public float getLength() {
		return length;
	}
	public void setLength(float length) {
		this.length = length;
	}
	public static void main(String args[]) {
		Arithmetic ari = new Arithmetic();
		int[] pa0 = ari.getOptimalPath(1, 0);
		for (int i = 0; i < pa0.length; i++) {
			System.out.print(pa0[i] + " ");
		}
		System.out.println("\n" + ari.getLength());
		ari.length = 0;
		for (int i = 0; i < ari.map.vertex.size(); i++) {
			for (int j = 0; j < ari.map.vertex.size(); j++) {
				if (i != j) {
					int[] pa = ari.getOptimalPath(j, i);
					float len = ari.getLength();
					ari.setLength(0);
					System.out.println(j + "到" + i + "：");
					for (int d = 0; d < pa.length; d++) {
						System.out.print(pa[d] + " ");
					}
					System.out.println("\n" + len);
				}
			}
		}
	}
}
