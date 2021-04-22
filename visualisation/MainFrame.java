package visualisation;

import java.awt.Color;
import javax.swing.JFrame;

import algorithm.Field;

public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	public Field f;
	
	public CanvasPanel canvas = new CanvasPanel();
	
	Toolbox toolbox = new Toolbox();
	
	public MainFrame() {
		super();
		
		this.setResizable(true);
		this.setTitle("A*");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		this.add(canvas);
		canvas.setBackground(Color.black);
		

		this.setSize(800, 800);
		this.setVisible(true);
	}
	
	public void setField(Field f) {
		
		this.f = f;
		canvas.setField(f);
		
		toolbox.setMainFrame(this);
		
		this.repaint();
	}

}
