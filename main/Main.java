package main;

import algorithm.Field;
import visualisation.MainFrame;

public class Main {

	public static void main(String[] args) {
		
		MainFrame frame = new MainFrame();
		Field field = new Field(40,40);
		
		field.setTarget(39,0);
		
		frame.setField(field);
		
	}

}
