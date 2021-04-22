package main;

import algorithm.Field;
import visualisation.MainFrame;

public class Main {

	public static void main(String[] args) {
		
		MainFrame frame = new MainFrame();
		Field field = new Field(40,40);
		
		frame.setField(field);
		
		field.setWall(1, 1);
		field.setWall(1, 0);
		field.setWall(1, 2);
		
		field.setTarget(39,0);
		
		field.evaluate();
		
		
		
	}

}
