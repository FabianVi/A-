package visualisation;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import algorithm.Field;

public class CanvasPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	Field f;
	
	public boolean rootDistance = false;
	public boolean targetDistance = false;
	
	public boolean checked = true;
	public boolean calculated = true;
	public boolean path = true;
	
	public CanvasPanel() {
		super();
	}
	
	@Override
	public void paint( Graphics g ) {
		
		g.setColor(this.getBackground());
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		if(f == null)
			return;
		
		float sizex = this.getWidth() * 1.0f / (f.width);
		float sizey = this.getHeight() * 1.0f  / (f.height);
		
		for(int i=0; i<f.width; i++)
			for(int j=0; j<f.height; j++) {
				switch(f.field[i][j].getSquareType()) {
				
				case DEFAULT:
					g.setColor(Color.gray);
					break;
					
				case ROOT:
					g.setColor(Color.orange);
					break;
					
				case TARGET:
					g.setColor(Color.green);
					break;
					
				case WALL:
					g.setColor(Color.darkGray);
					break;
					
				case CALCULATED:
					if(calculated)
						g.setColor(new Color(200,20,20));
					else
						g.setColor(Color.gray);
					break;
					
				case CHECKED:
					if(checked)
						g.setColor(Color.red);
					else
						g.setColor(Color.gray);
					break;
					
				default:
					g.setColor(Color.gray);
				}
				
				String distR = String.format("%.2f", f.field[i][j].distanceToRoot);
				String distT = String.format("%.2f", f.field[i][j].distanceToTarget);
				
		
				g.fillRect((int)(sizex*i)+1, (int)(sizey*j)+1, (int)sizex-2, (int)sizey-2);
				
				g.setColor(Color.white);
				
				if(f.field[i][j].isPath && path)
					g.drawRect((int)(sizex*i)+1, (int)(sizey*j)+1, (int)sizex-2, (int)sizey-2);
				
				if(rootDistance)
					g.drawChars(distR.toCharArray(), 0, Math.min(4, distR.length()), (int)(sizex*i)+1, (int)(sizey*j)+1 + (int)(sizex/2) - 10);
				if(targetDistance)
					g.drawChars(distT.toCharArray(), 0, Math.min(4, distT.length()), (int)(sizex*i)+1, (int)(sizey*j)+1 + (int)(sizex/2) + 20);
			}
		
	
	}
	
	public void setField(Field f) {
		
		this.f = f;
		this.repaint();
		
	}

}
