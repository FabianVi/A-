package algorithm;

public class Square {
	
	SquareType type = SquareType.DEFAULT;
	
	public int x,y;
	
	public boolean isPath=false;
	
	public float distanceToRoot = Float.MAX_VALUE;
	public float distanceToTarget = Float.MAX_VALUE;
	
	public Square(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Square(int x, int y, SquareType t) {
		
		this.x = x;
		this.y = y;
		
		this.type = t;
	}
	
	public void setSquareType(SquareType t) {
		this.type = t;
	}

	public SquareType getSquareType() {
		return type;
	}

	public float getDistance() {
		return distanceToRoot + distanceToTarget;
	}
	
	public float calcDistace(Square target) {
		
		float dx = target.x - this.x;
		float dy = target.y - this.y;
		
		return (float) Math.sqrt(dx*dx + dy*dy);
	}
	
	public void calculate(Square prev,Square target) {
		
		if (distanceToRoot > (prev.distanceToRoot + prev.calcDistace(this)))
			distanceToRoot = prev.distanceToRoot + prev.calcDistace(this);
		if (distanceToTarget > this.calcDistace(target))
			distanceToTarget = this.calcDistace(target);
		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(distanceToRoot);
		result = prime * result + Float.floatToIntBits(distanceToTarget);
		result = prime * result + (isPath ? 1231 : 1237);
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Square other = (Square) obj;
		if (Float.floatToIntBits(distanceToRoot) != Float.floatToIntBits(other.distanceToRoot))
			return false;
		if (Float.floatToIntBits(distanceToTarget) != Float.floatToIntBits(other.distanceToTarget))
			return false;
		if (isPath != other.isPath)
			return false;
		if (type != other.type)
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Square [type=" + type + ", x=" + x + ", y=" + y + ", distanceToRoot=" + distanceToRoot
				+ ", distanceToTarget=" + distanceToTarget + "]";
	}
	
	
	
}
