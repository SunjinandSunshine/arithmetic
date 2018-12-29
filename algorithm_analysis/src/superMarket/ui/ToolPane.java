package ui;
/**
 * @author Sun
 */
import javax.swing.*;
import java.awt.*;
public class ToolPane extends JPanel {

	private static final long serialVersionUID = 1L;

	JButton add = new JButton(new ImageIcon("image/room.png"));
	JButton concat = new JButton(new ImageIcon("image/concat.jpg"));
	JButton delete = new JButton(new ImageIcon("image/delete.png"));
	JButton run = new JButton(new ImageIcon("image/run.png"));

	public ToolPane() {
		this.setBorder(MainFrame.border);
		this.setLayout(new BorderLayout());
		this.addTool();
	}
	private void addTool() {
		JToolBar tool = new JToolBar("MyTool");
		tool.setLayout(new GridLayout(1, 4, 80, 10));
		tool.setFloatable(true);
		add.setBackground(Color.LIGHT_GRAY);
		concat.setBackground(Color.LIGHT_GRAY);
		delete.setBackground(Color.LIGHT_GRAY);
		run.setBackground(Color.LIGHT_GRAY);
		tool.add(add);
		tool.add(concat);
		tool.add(delete);
		tool.add(run);
		this.add(tool);
	}
}
