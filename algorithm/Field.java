package algorithm;

import java.util.ArrayList;
import java.util.List;

public class Field {
	
	public int width,height;
	
	public Square field[][];
	public Square current;
	
	public Square target;
	public Square root;
	
	List<Square> checked = new ArrayList<Square>();
	List<Square> calcDistance = new ArrayList<Square>();
	
	public Field(int width,int height) {
		
		this.width = width;
		this.height = height;
		
		field  = new Square[width][height];
		
		for(int i=0; i<width; i++)
			for(int j=0; j<height; j++)
				field[i][j] = new Square(i,j);
		
		root = field[0][0];
		root.setSquareType(SquareType.ROOT);
		
		current = root;
		root.distanceToRoot = 0;
		
		target = field[width-1][height-1];
		target.setSquareType(SquareType.TARGET);
		
	}
	
	public void setWall(int x, int y) {
		field[x][y].setSquareType(SquareType.WALL);
	}
	
	public void evaluate() {
		do
		{
			this.calcNeighbours(current);
			current = this.bestNext();
			
		}
		while(current.getSquareType() != SquareType.TARGET);
		
		this.backProp(target);
	}
	
	public void step() {
		
		if(current.getSquareType() == SquareType.TARGET) {
			this.backProp(target);
			return;
		}
		
		this.calcNeighbours(current);
		current = this.bestNext();
	}
	
	
	public void setTarget(int x , int y) {
		
		if(!checkBounce(x, y))
			return;
		
		target.setSquareType(SquareType.DEFAULT);
		
		target = field[x][y];
		target.setSquareType(SquareType.TARGET);
	}
	
	public void setRoot(int x , int y) {
		
		if(!checkBounce(x, y))
			return;
		
		root.setSquareType(SquareType.DEFAULT);
		
		root = field[x][y];
		root.setSquareType(SquareType.ROOT);
	}
	
	public boolean checkBounce(int x,int y) {
		return x>=0 && y>=0 && x<width && y<height;
	}
	
	public Square bestNeighbour(Square sq) {
		
		Square min = sq;
		
		for(int i=sq.x-1; i<=sq.x+1;i++)
			for(int j=sq.y-1; j<=sq.y+1;j++)
				if(checkBounce(i, j))
					if(field[i][j].distanceToRoot < min.distanceToRoot && field[i][j].getSquareType()!=SquareType.WALL)
						min = field[i][j];
		
		return min;
		
	}
	
	public Square bestNext() {
		
		Square min = calcDistance.get(0);
		
		for(Square q : calcDistance)
			if(min.getDistance() > q.getDistance())
				min = q;
			
		return min;
	}
	
	public void calcNeighbours(Square sq) {
		
		if((sq.getSquareType() == SquareType.CALCULATED)) {
			sq.setSquareType(SquareType.CHECKED);
			checked.add(sq);
			calcDistance.remove(sq);
		}

		
		for(int i=sq.x-1; i<=sq.x+1;i++)
			for(int j=sq.y-1; j<=sq.y+1;j++)
				if(checkBounce(i, j) && sq != field[i][j] && field[i][j].getSquareType() != SquareType.WALL) //  && field[i][j].getSquareType() != SquareType.CALCULATED && field[i][j].getSquareType() != SquareType.CHECKED  && field[i][j].getSquareType() != SquareType.ROOT
				{
					field[i][j].calculate(sq,target);
					
					if(field[i][j].getSquareType() == SquareType.DEFAULT) {
						
						field[i][j].setSquareType(SquareType.CALCULATED);
						calcDistance.add(field[i][j]);
						
					}
					
					if(field[i][j].getSquareType() == SquareType.TARGET)
						calcDistance.add(field[i][j]);
						
				}
					
		
	}
	
	public void backProp(Square start) {
		
		Square tmp = start;
		
		while(tmp.getSquareType() != SquareType.ROOT)
		{
			tmp.isPath = true;
			tmp = this.bestNeighbour(tmp);
		}
		
		
	}
}
