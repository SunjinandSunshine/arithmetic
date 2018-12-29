package ui;
/**
 * @author Sun
 */
import javax.swing.*;
import java.awt.*;

public class TextPane extends JPanel {
	private static final long serialVersionUID = 1L;
	public JTextArea runResult = new JTextArea();
	public TextPane() {

		this.setLayout(new BorderLayout());
		this.runResult.setEditable(false);
		this.add(new JScrollPane(runResult));
	}

}
