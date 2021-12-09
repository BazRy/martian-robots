package com.lme.martian.robot;

public class Position {

	private int xAxis;
	private int yAxis;
	private Orientation orientation;
	
	protected Position(int xAxis, int yAxis, Orientation orientation) {
		this.xAxis = xAxis;
		this.yAxis = yAxis;
		this.orientation = orientation;
	}
	
	protected int getxAxis() {
		return xAxis;
	}

	protected int getyAxis() {
		return yAxis;
	}

	protected Orientation getOrientation() {
		return orientation;
	}

	protected void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orientation == null) ? 0 : orientation.hashCode());
		result = prime * result + xAxis;
		result = prime * result + yAxis;
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
		Position other = (Position) obj;
		if (orientation != other.orientation)
			return false;
		if (xAxis != other.xAxis)
			return false;
		if (yAxis != other.yAxis)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder().append(xAxis).append(" ").append(yAxis).append(" ").append(orientation.toString()).append(" ");
		return sb.toString();
	}
}
