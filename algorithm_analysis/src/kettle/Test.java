package kettle;

import java.util.LinkedList;
import java.util.Queue;
/*
 * ����
 * ��Ŀ����ˮ
 */
public class Test {
	static class Node {
		int water[] = new int[3]; // ˮ����ˮ
		int step; // ����
	}
	static class Main {
		boolean visted[][][]; // ���ˮ����״̬
		int[] start = new int[3]; // ����״̬
		int[] max = new int[3]; // ˮ��װˮ����(����)

		void init() { // ��ʼ��
			start[0] = 0;
			start[1] = 0;
			start[2] = 8;
			max[0] = 3;
			max[1] = 5;
			max[2] = 8;
			visted = new boolean[10][10][10];
			int flag = BFS();
			if (flag == -1) {
				System.out.println("���ܵõ���Ҫ״̬��");
			}
		}

		int BFS() { // �������
			Queue<Node> queue = new LinkedList<Node>(); // ���ɶ���
			Node node1 = new Node();
			node1.water[0] = start[0];
			node1.water[1] = start[1];
			node1.water[2] = start[2];
			node1.step = 0;
			queue.offer(node1);// �ڶ����м���node1
			visted[start[0]][start[1]][start[2]] = true;

			while (!queue.isEmpty()) {
				Node node = queue.element(); // ��õ�һ��Ԫ��
				System.out.println("         " + node.water[0] + "       " + node.water[1] + "       " + node.water[2]);
				queue.poll(); // ɾ����һ��Ԫ��

				if (achieve(node) == true) { // �ж��Ƿ������������һ��Ϊ4��
					return node.step;
				}

				for (int i = 0; i < 3; i++) { // ��ˮ�ӵ�i��ˮ��������j��ˮ��
					for (int j = 0; j < 3; j++) {
						if (i == j)
							continue;
						if (node.water[i] != 0 && node.water[j] < max[j]) { // ��ˮ��ˮ��������ˮ�����ҽ�ˮ��ˮ����������
							Node node2 = new Node();// �½�
							node2.step = node.step;
							node2.water[0] = node.water[0];
							node2.water[1] = node.water[1];
							node2.water[2] = node.water[2];
							int pour = max[j] - node2.water[j]; // �����Ե���ˮ��
							if (node2.water[i] > pour) { // ���ᵹ��
								node2.water[j] += pour;
								node2.water[i] -= pour;
							} else { // ����
								node2.water[j] += node2.water[i];
								node2.water[i] = 0;
							}
							node2.step = node.step + 1;
							if (!visted[node2.water[0]][node2.water[1]][node2.water[2]]) {// ����������û�г���
								visted[node2.water[0]][node2.water[1]][node2.water[2]] = true;
								queue.add(node2);// ���
							}
						}
					}
				}
			}
			return -1;
		}

		// �ж��Ƿ���һ��ˮ����4
		boolean achieve(Node node) {
			for (int i = 0; i < 3; i++) {
				if (node.water[i] == 4)
					return true;
			}
			return false;
		}

	}

	public static void main(String[] args) {
		System.out.println("              " + "ˮ��1" + "   " + "ˮ��2" + "   " + "ˮ��3");
		System.out.println("�������" + "   3   " + "    5    " + "   8 \n" + "��ˮ·��");
		Main m = new Main();
		m.init();
	}
}
