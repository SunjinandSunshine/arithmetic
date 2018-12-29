package kettle;

import java.util.LinkedList;
import java.util.Queue;
/*
 * 遍历
 * 题目：倒水
 */
public class Test {
	static class Node {
		int water[] = new int[3]; // 水壶的水
		int step; // 步骤
	}
	static class Main {
		boolean visted[][][]; // 标记水壶的状态
		int[] start = new int[3]; // 输入状态
		int[] max = new int[3]; // 水壶装水上限(容量)

		void init() { // 初始化
			start[0] = 0;
			start[1] = 0;
			start[2] = 8;
			max[0] = 3;
			max[1] = 5;
			max[2] = 8;
			visted = new boolean[10][10][10];
			int flag = BFS();
			if (flag == -1) {
				System.out.println("不能得到想要状态！");
			}
		}

		int BFS() { // 广度优先
			Queue<Node> queue = new LinkedList<Node>(); // 生成队列
			Node node1 = new Node();
			node1.water[0] = start[0];
			node1.water[1] = start[1];
			node1.water[2] = start[2];
			node1.step = 0;
			queue.offer(node1);// 在队列中加入node1
			visted[start[0]][start[1]][start[2]] = true;

			while (!queue.isEmpty()) {
				Node node = queue.element(); // 获得第一个元素
				System.out.println("         " + node.water[0] + "       " + node.water[1] + "       " + node.water[2]);
				queue.poll(); // 删除第一个元素

				if (achieve(node) == true) { // 判断是否符合条件（有一个为4）
					return node.step;
				}

				for (int i = 0; i < 3; i++) { // 将水从第i个水壶倒到第j个水壶
					for (int j = 0; j < 3; j++) {
						if (i == j)
							continue;
						if (node.water[i] != 0 && node.water[j] < max[j]) { // 倒水的水壶必须有水，并且接水的水壶不是满的
							Node node2 = new Node();// 新建
							node2.step = node.step;
							node2.water[0] = node.water[0];
							node2.water[1] = node.water[1];
							node2.water[2] = node.water[2];
							int pour = max[j] - node2.water[j]; // 还可以倒的水量
							if (node2.water[i] > pour) { // 不会倒空
								node2.water[j] += pour;
								node2.water[i] -= pour;
							} else { // 倒空
								node2.water[j] += node2.water[i];
								node2.water[i] = 0;
							}
							node2.step = node.step + 1;
							if (!visted[node2.water[0]][node2.water[1]][node2.water[2]]) {// 如果这种情况没有出现
								visted[node2.water[0]][node2.water[1]][node2.water[2]] = true;
								queue.add(node2);// 入队
							}
						}
					}
				}
			}
			return -1;
		}

		// 判断是否有一个水壶是4
		boolean achieve(Node node) {
			for (int i = 0; i < 3; i++) {
				if (node.water[i] == 4)
					return true;
			}
			return false;
		}

	}

	public static void main(String[] args) {
		System.out.println("              " + "水壶1" + "   " + "水壶2" + "   " + "水壶3");
		System.out.println("最大容量" + "   3   " + "    5    " + "   8 \n" + "倒水路径");
		Main m = new Main();
		m.init();
	}
}
