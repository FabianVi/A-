package visualisation;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Toolbox extends JFrame {

	JPanel panel = new JPanel();
	MainFrame mainFrame;
	
	private static final long serialVersionUID = 1L;

	public Toolbox() {
		
		super();
		
		this.setTitle("Toolbox");
		this.add(panel);
		this.setResizable(false);
		this.setSize(100, 800);
		
		panel.add(new JButton("OK"));
		
		
		this.setVisible(true);
		
	}
	
	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	
	
}
