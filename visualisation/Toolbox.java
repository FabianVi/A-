package visualisation;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import algorithm.Field;


public class Toolbox extends JFrame implements ActionListener {

	MainFrame mainFrame;
	
	JLabel title = new JLabel("ToolBox", SwingConstants.CENTER);
	
	JPanel field = new JPanel();
	JLabel fieldTitle = new JLabel("Field", SwingConstants.CENTER);
	JPanel fieldPanel = new JPanel();
	JLabel width = new JLabel("width");
	JLabel height = new JLabel("height");
	JTextField widthIn = new  JTextField();
	JTextField heightIn = new  JTextField();
	
	
	JLabel rootTargetTitle = new JLabel("ROOT/Target", SwingConstants.CENTER);
	JPanel rootTarget = new JPanel();
	JPanel rootTargetPanel = new JPanel();
	JLabel root = new JLabel("R (x,y)");
	JLabel target = new JLabel("T (x,y)");
	
	JTextField rootx = new  JTextField();
	JTextField rooty = new  JTextField();
	JTextField targetx = new  JTextField();
	JTextField targety = new  JTextField();
	
	JButton apply = new JButton("apply");
	
	JPanel evaluate = new JPanel();
	JPanel evaluatePane = new JPanel();
	JLabel evaluationLable = new JLabel("Evaluation", SwingConstants.CENTER);
	JLabel autoEvalLabel = new JLabel("auto eval");
	JCheckBox autoEval = new JCheckBox();
	JButton evaluateBtn = new JButton("Eval All");
	JButton next = new JButton(">>");
	
	
	JPanel view = new JPanel();
	JLabel viewTitle = new JLabel("View Settings", SwingConstants.CENTER);
	JPanel viewPanel = new JPanel();
	JLabel calculatedLabel = new JLabel("Calculated");
	JLabel checkedLabel = new JLabel("Checked");
	JLabel pathLabel = new JLabel("Path");
	JLabel distanceRLable = new JLabel("distanceR");
	JLabel distanceTLable = new JLabel("distanceT");
	
	JCheckBox calculated = new JCheckBox();
	JCheckBox distanceR = new JCheckBox();
	JCheckBox distanceT = new JCheckBox();
	JCheckBox checked = new JCheckBox();
	JCheckBox path = new JCheckBox();
	
	private static final long serialVersionUID = 1L;

	public Toolbox() {
		
		super();
		this.setTitle("Toolbox");
		this.setLayout(new GridLayout(5,1));
		this.setResizable(false);
		this.setSize(150, 800);
		
		
		title.setFont(new Font("Serif", Font.PLAIN, 30));
		this.add(title);
		
		rootTarget.setLayout(new GridLayout(2,1));
		rootTargetTitle.setFont(new Font("Serif", Font.PLAIN, 20));
		rootTarget.add(rootTargetTitle);
		
		
		rootTargetPanel.setLayout(new GridLayout(2,3));
		
		rootTargetPanel.add(root);
		rootTargetPanel.add(rootx);
		rootTargetPanel.add(rooty);
		
		rootTargetPanel.add(target);
		rootTargetPanel.add(targetx);
		rootTargetPanel.add(targety);
		
		rootTarget.add(rootTargetPanel);

		this.add(rootTarget);
		
		
		field.setLayout(new GridLayout(2,1));
		fieldTitle.setFont(new Font("Serif", Font.PLAIN, 20));
		
		field.add(fieldTitle);
		
		fieldPanel.setLayout(new GridLayout(3,2));
		fieldPanel.add(width);
		fieldPanel.add(widthIn);
		fieldPanel.add(height);
		fieldPanel.add(heightIn);
		fieldPanel.add(apply);
		
		field.add(fieldPanel);
		
		this.add(field);
		
		
		evaluate.setLayout(new GridLayout(2,1));
		evaluationLable.setFont(new Font("Serif", Font.PLAIN, 20));
		evaluatePane.setLayout(new GridLayout(2,2));
		evaluatePane.add(autoEvalLabel);
		evaluatePane.add(autoEval);
		evaluatePane.add(evaluateBtn);
		evaluatePane.add(next);

		evaluate.add(evaluationLable);
		evaluate.add(evaluatePane);
		
		this.add(evaluate);
		
		
		view.setLayout(new GridLayout(2,1));
		viewTitle.setFont(new Font("Serif", Font.PLAIN, 20));
		
		viewPanel.setLayout(new GridLayout(5,2));
		
		viewPanel.add(calculatedLabel);
		viewPanel.add(calculated);		
		viewPanel.add(checkedLabel);
		viewPanel.add(checked);
		viewPanel.add(pathLabel);
		viewPanel.add(path);		
		viewPanel.add(distanceRLable);
		viewPanel.add(distanceR);
		viewPanel.add(distanceTLable);
		viewPanel.add(distanceT);	
		
		view.add(viewTitle);
		view.add(viewPanel);
		
		this.add(view);
		
		
		apply.addActionListener(this);
		evaluateBtn.addActionListener(this);
		next.addActionListener(this);
		autoEval.addActionListener(this);
		calculated.addActionListener(this);
		checked.addActionListener(this);
		distanceR.addActionListener(this);
		distanceT.addActionListener(this);
		path.addActionListener(this);
		
		
		this.setVisible(true);
		
	}
	
	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		
		widthIn.setText(Integer.toString(mainFrame.f.width));
		heightIn.setText(Integer.toString(mainFrame.f.width));
		
		calculated.setSelected(mainFrame.canvas.calculated);
		distanceR.setSelected(mainFrame.canvas.rootDistance);
		distanceT.setSelected(mainFrame.canvas.targetDistance);
		checked.setSelected(mainFrame.canvas.checked);
		path.setSelected(mainFrame.canvas.path);
		
		targetx.setText( Integer.toString(mainFrame.f.target.x));
		targety.setText( Integer.toString(mainFrame.f.target.y));
		
		rootx.setText( Integer.toString(mainFrame.f.root.x));
		rooty.setText( Integer.toString(mainFrame.f.root.x));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == apply) {
			Field f = new Field(Integer.parseInt(widthIn.getText()),Integer.parseInt(heightIn.getText()));
			
			f.setRoot(Integer.parseInt(rootx.getText()), Integer.parseInt(rooty.getText()));
			f.setTarget(Integer.parseInt(targetx.getText()), Integer.parseInt(targety.getText()));
			
			mainFrame.setField(f);
			mainFrame.canvas.applyWall();
			
			if(autoEval.isSelected())
				mainFrame.f.evaluate();
		}
		
		if(e.getSource() == evaluateBtn) {
			
			mainFrame.f.clear();
			
			mainFrame.canvas.applyWall();
			
			mainFrame.f.evaluate();
			
			mainFrame.repaint();
		}
		
		if(e.getSource() == next) {
			mainFrame.f.step();
			mainFrame.repaint();
		}
		
		if(e.getSource() == calculated) {
			mainFrame.canvas.calculated = calculated.isSelected();
			mainFrame.repaint();
		}
		
		if(e.getSource() == distanceR) {
			mainFrame.canvas.rootDistance = distanceR.isSelected();
			mainFrame.repaint();
		}
		
		if(e.getSource() == distanceT) {
			mainFrame.canvas.targetDistance = distanceT.isSelected();
			mainFrame.repaint();
		}
		
		if(e.getSource() == checked) {
			mainFrame.canvas.checked = checked.isSelected();
			mainFrame.repaint();
		}
		
		if(e.getSource() == path) {
			mainFrame.canvas.path = path.isSelected();
			mainFrame.repaint();
		}
		
		if(e.getSource() == autoEval) {
			mainFrame.canvas.autoEvaluate = autoEval.isSelected();
		}
		
		
	}
}
