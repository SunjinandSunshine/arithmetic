package ui;
/**
 * @author Sun
 */
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MainFrame extends JFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	// 主面板
	public DrawPane dpane = new DrawPane();
	public ToolPane tpane = new ToolPane();
	public TextPane textpane = new TextPane();
	// 边界
	public static Border border = BorderFactory.createBevelBorder(
			BevelBorder.LOWERED, Color.BLACK, Color.DARK_GRAY);
	// 数据模型
	public MapModel mapModel = new MapModel();

	public MainFrame() {
		this.setTitle("超市选址问题");
		this.setSize(900, 600);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(3);
		this.makePane();
		this.addAction();
	}
	private void makePane() {
		JPanel left = new JPanel(new BorderLayout(5, 10));
		left.add(dpane);
		left.add(tpane, BorderLayout.SOUTH);
		JSplitPane jspane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left,
				textpane);
		jspane.setDividerLocation(this.getWidth() / 4 * 3);
		this.add(jspane);
	}

	public void addAction() {
		this.tpane.add.addActionListener(this);
		this.tpane.concat.addActionListener(this);
		this.tpane.delete.addActionListener(this);
		this.tpane.run.addActionListener(this);
		this.dpane.addMouseListener(this);
	}
	// 打印最短路径信息
	public void printText(ArrayList<int[]> list) {
		for (int i = 0; i < list.size(); i++) {
			textpane.runResult.append("最佳路径：");
			for (int j = 0; j < list.get(i).length; j++) {
				if (j == list.get(i).length - 1) {
					textpane.runResult
							.append(DrawPane.roomList.get(list.get(i)[j]).apane.name
									+ "");
				} else {
					textpane.runResult
							.append(DrawPane.roomList.get(list.get(i)[j]).apane.name
									+ "->");
				}
			}
			textpane.runResult.append("\n最短距离："
					+ mapModel.getMinLength().get(i) + "\n");
		}
	}

	// 最优组合值
	public void getOptimal() {
		float[] min = new float[mapModel.getPath().size()
				/ (mapModel.map.vertex.size() - 1)];
		int k = 0;
		for (int i = 0; i < mapModel.getMinLength().size();) {
			for (int j = 0; j < mapModel.map.vertex.size() - 1; j++) {
				min[k] = min[k] + mapModel.getMinLength().get(i);
				i++;
			}
			k++;
		}
		for (int i = 0; i < min.length; i++) {
			int cen = mapModel.map.vertex.size() - 1;
			textpane.runResult
					.append("到"
							+ DrawPane.roomList
									.get((mapModel.getPath().get(cen * i)[mapModel
											.getPath().get(cen * i).length - 1])).apane.name
							+ "的最短路径为：" + min[i] + "\n");
		}

		float theMin = min[0];
		int minDex = 0;
		for (int i = 1; i < min.length; i++) {
			if (theMin > min[i]) {
				theMin = min[i];
				minDex = i;
			}
		}
		DrawPane.roomList.get(minDex).setBorder(new LineBorder(Color.RED, 2));
		textpane.runResult.append("最佳地址："
				+ DrawPane.roomList.get(minDex).apane.name);
	}

	// 按钮监听
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(tpane.add)) {
			Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(
					new ImageIcon("image/room.png").getImage(),
					new Point(10, 20), "stick");
			this.dpane.setCursor(cursor);
			this.dpane.add_flag = true;
		} else if (e.getSource().equals(tpane.concat)) {
			this.dpane.start.clear();
			this.dpane.end.clear();
			for (int i = 0; i < DrawPane.roomList.size(); i++) {
				this.dpane.remove(DrawPane.roomList.get(i));
			}
			this.dpane.repaint();
			DrawPane.roomList.clear();
			this.textpane.runResult.setText("");
		} else if (e.getSource().equals(tpane.delete)) {
			this.dpane.setCursor(new Cursor(Cursor.HAND_CURSOR));
			this.dpane.delete_flag = true;
		} else if (e.getSource().equals(tpane.run)) {
			mapModel.getData(DrawPane.roomList);
			mapModel.count();
			this.printText(mapModel.getPath());
			this.getOptimal();
		}
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3) {
			this.dpane.setCursor(Cursor.getDefaultCursor());
			this.dpane.concat_flag = false;
			this.dpane.add_flag = false;
			this.dpane.delete_flag = false;
			this.dpane.select_flag = false;
		} else if (e.getButton() == MouseEvent.BUTTON1) {
			if (this.dpane.add_flag) {
				this.dpane.setCursor(Cursor.getDefaultCursor());
				this.dpane.addNewRoom(e.getX(), e.getY());
				this.dpane.add_flag = false;
			}
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	public static void main(String args[]) {

		MainFrame mf = new MainFrame();
		mf.validate();
	}
}
