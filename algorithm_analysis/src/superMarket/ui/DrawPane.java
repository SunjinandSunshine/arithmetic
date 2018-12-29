package ui;
/**
 * @author Sun
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class DrawPane extends JPanel {

	private static final long serialVersionUID = 1L;
	public boolean add_flag = false;
	public boolean select_flag = false;
	public boolean concat_flag = false;
	public boolean delete_flag = false;
	public static ArrayList<RoomPane> roomList = new ArrayList<RoomPane>();
	public ArrayList<Point> start = new ArrayList<Point>();
	public ArrayList<Point> end = new ArrayList<Point>();

	public DrawPane() {

		this.setBorder(MainFrame.border);
		this.setLayout(null);
	}

	public void addNewRoom(int px, int py) {

		AddPane ap = new AddPane(this, px, py);
		RoomPane rp = new RoomPane(this, ap);
		roomList.add(rp);
		this.add(rp);
		rp.index = roomList.size() - 1;
		rp.setBounds(px, py, 50, 50);
		ap.setVisible(true);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Dimension dim = this.getSize();
		int wid = (int) dim.getWidth() / 20;
		int hei = (int) dim.getHeight() / 20;
		// 横线
		int posx = 0, posy = 0;
		for (; posy < dim.getHeight();) {
			g.drawLine(posx, posy, (int) dim.getWidth(), posy);
			posy = posy + hei;
		}
		// 竖线
		posx = 0;
		posy = 0;
		for (; posx < dim.getWidth();) {
			g.drawLine(posx, posy, posx, (int) dim.getHeight());
			posx = posx + wid;
		}
		// 通路
		for (int i = 0; i < start.size(); i++) {
			g.drawLine(start.get(i).x, start.get(i).y, end.get(i).x,
					end.get(i).y);
		}
		// 名称
		g.setColor(Color.RED);
		for (int i = 0; i < roomList.size(); i++) {
			if (roomList.get(i).apane.name != null) {
				g.drawString(roomList.get(i).apane.name + "("
						+ roomList.get(i).apane.freq + ")", roomList.get(i)
						.getLocation().x + 10,
						roomList.get(i).getLocation().y + 60);
			}
		}
	}

	public static void main(String a[]) {

		AddPane ap = new AddPane(new DrawPane(), 100, 100);
		ap.setVisible(true);
	}

}

class RoomPane extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;

	public DrawPane dpane;
	public AddPane apane;
	public int index;
	public static Point first;
	public static AddPane last;

	public RoomPane(DrawPane dp, AddPane ap) {

		this.dpane = dp;
		this.apane = ap;
		this.addMouseListener(this);
		this.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		ImageIcon icon = new ImageIcon("image/room.png");
		g.drawImage(icon.getImage(), 0, 0, this.getWidth(), this.getHeight(),
				this);
	}

	public void mouseClicked(MouseEvent e) {

		if (e.getButton() == MouseEvent.BUTTON1 && dpane.delete_flag) {
			dpane.remove(this);
			DrawPane.roomList.remove(this);
			dpane.delete_flag = false;
			dpane.setCursor(Cursor.getDefaultCursor());
			dpane.repaint();
		} else if (e.getButton() == MouseEvent.BUTTON1 && dpane.select_flag) {
			dpane.start.add(first);
			dpane.end.add(this.getLocation());
			dpane.select_flag = false;
			dpane.setCursor(Cursor.getDefaultCursor());
			dpane.repaint();
			last.model.addElement(this.apane.name);
			boolean circle = true;
			while (circle) {
				try {
					float len = Float.parseFloat(JOptionPane.showInputDialog(
							null, "输入距离"));
					last.lenmodel.addElement(len);
					circle = false;
				} catch (NumberFormatException ex) {
					circle = true;
					JOptionPane.showMessageDialog(null, "输入数字");
				}
			}
		} else if (e.getButton() == MouseEvent.BUTTON1) {
			this.apane.setVisible(true);
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
}

class AddPane extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	public JTextField namefield = new JTextField(10);
	public JTextField freqfield = new JTextField(10);
	public JButton ensure = new JButton("确定");
	public JButton cancel = new JButton("取消");
	public JButton concat = new JButton("添加通路");
	public DefaultListModel model = new DefaultListModel();
	public JList list = new JList(model);
	public DefaultListModel lenmodel = new DefaultListModel();
	public JList lenlist = new JList(lenmodel);
	public String name;
	public float freq;
	public DrawPane dpane;
	public int px, py;
	public AddPane(DrawPane dp, int posx, int posy) {
		dpane = dp;
		this.px = posx;
		this.py = posy;
		this.addComponent();
		this.model.addElement("邻居");
		this.list.setBackground(new Color(100, 150, 200));
		this.lenmodel.addElement("距离");
		this.lenlist.setBackground(new Color(150, 200, 100));
	}

	public void addComponent() {

		this.setTitle("当前建筑");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setSize(dpane.getWidth() / 2, dpane.getHeight() / 2);
		JLabel jl1 = new JLabel("名称:");
		JLabel jl2 = new JLabel("频度:");
		JPanel up = new JPanel();
		JPanel down = new JPanel(new BorderLayout());
		JPanel btnp = new JPanel();
		JPanel lpane = new JPanel(new GridLayout(1, 2));
		up.add(jl1);
		up.add(namefield);
		up.add(jl2);
		up.add(freqfield);
		btnp.add(concat);
		btnp.add(ensure);
		btnp.add(cancel);
		lpane.add(new JScrollPane(list));
		lpane.add(new JScrollPane(lenlist));
		down.add(lpane);
		down.add(btnp, BorderLayout.SOUTH);
		this.add(up, BorderLayout.NORTH);
		this.add(down);
		this.concat.addActionListener(this);
		this.cancel.addActionListener(this);
		this.ensure.addActionListener(this);
		this.concat.setBackground(Color.LIGHT_GRAY);
		this.cancel.setBackground(Color.LIGHT_GRAY);
		this.ensure.setBackground(Color.LIGHT_GRAY);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(ensure)) {
			this.name = namefield.getText();
			try {
				if (freqfield.getText().length() != 0)
					this.freq = Float.parseFloat(freqfield.getText());
			} catch (NumberFormatException ex) {
				freqfield.setText("");
				JOptionPane.showMessageDialog(null, "频度为数字！");
			}
			this.setVisible(false);
			this.dpane.repaint();
		} else if (e.getSource().equals(cancel)) {
			this.setVisible(false);
		} else if (e.getSource().equals(concat)) {
			RoomPane.first = new Point(px, py);
			RoomPane.last = this;
			dpane.select_flag = true;
			dpane.setCursor(new Cursor(Cursor.HAND_CURSOR));
			this.setVisible(false);
		}
	}
}