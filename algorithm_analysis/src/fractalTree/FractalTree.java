package fractalTree;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * 
 * @author Sun
 *
 */
public class FractalTree extends JPanel {
	private static final long serialVersionUID = -4012707443757181023L;
	public static final double OFFSET_ANGLE = Math.PI / 6;
	public static final double PID2 = Math.PI / 2;
	public static final double limit = 5.0;
	private Point2D.Double beginPoint;
	private Point2D.Double endPoint;
	public FractalTree(Point2D.Double beginPoint, Point2D.Double endPoint) {
		super();
		this.beginPoint = beginPoint;
		this.endPoint   = endPoint;
		this.beginPoint.distance(endPoint);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(191, 140, 99));
		// 初始准线
		g.drawLine((int) beginPoint.getX(), (int) beginPoint.getY(), (int) endPoint.getX(), (int) endPoint.getY());
		paintChild(beginPoint, endPoint, g);
	}

	public static void paintChild(Point2D.Double beginp, Point2D.Double endp, Graphics g) {
		double length = beginp.distance(endp) / 3;
		if (length > limit) {// 限定条件
			if (length < 30)// 作为叶子
				g.setColor(Color.green);
			paint1_3point(beginp, endp, g);
			g.setColor(new Color(191, 140, 99));
			if (length < 30)// 作为叶子
				g.setColor(Color.green);
			paint2_3point(beginp, endp, g);
		}
	}
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(new Dimension(700, 600));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int width = frame.getWidth();
		int height = frame.getHeight();
		frame.add(new FractalTree(new Point2D.Double(width / 2, height), new Point2D.Double(width / 2, height / 2)));
		frame.setVisible(true);
	}
	public static void paint1_3point(Point2D.Double beginp, Point2D.Double endp, Graphics g) {
		double length = 2 * beginp.distance(endp) / 3;
		double pAngle = Math.atan2(beginp.getY() - endp.getY(), endp.getX() - beginp.getX());//第一次是PI/2
		System.out.println(pAngle);
		double cAngle = pAngle - OFFSET_ANGLE;// 与x轴的偏移角度
		double newBX = (2 * beginp.getX() + endp.getX()) / 3;
		double newBY = (2 * beginp.getY() + endp.getY()) / 3;
		double newEX = newBX + length * Math.cos(cAngle);// 新分支的结束X坐标
		double newEY = newBY - length * Math.sin(cAngle);// 新分支的结束Y坐标
		Point2D.Double newBeginp = new Point2D.Double(newBX, newBY);
		Point2D.Double newEndp = new Point2D.Double(newEX, newEY);
		g.drawLine((int) newBeginp.getX(), (int) newBeginp.getY(), (int) newEndp.getX(), (int) newEndp.getY());
		paintChild(newBeginp, newEndp, g);
	}
	public static void paint2_3point(Point2D.Double beginp, Point2D.Double endp, Graphics g) {
		double length = 2 * beginp.distance(endp) / 3;
		double pAngle = Math.atan2(beginp.getX() - endp.getX(), beginp.getY() - endp.getY());
		double cAngle = pAngle + OFFSET_ANGLE;// 与y轴的偏于角度
		double newBX = (beginp.getX() + 2 * endp.getX()) / 3;
		double newBY = (beginp.getY() + 2 * endp.getY()) / 3;
		double newEX = newBX - length * Math.sin(cAngle);// 新分支的结束X坐标
		double newEY = newBY - length * Math.cos(cAngle);// 新分支的结束Y坐标
		Point2D.Double newBeginp = new Point2D.Double(newBX, newBY);
		Point2D.Double newEndp = new Point2D.Double(newEX, newEY);
		g.drawLine((int) newBeginp.getX(), (int) newBeginp.getY(), (int) newEndp.getX(), (int) newEndp.getY());
		paintChild(newBeginp, newEndp, g);
	}
}
