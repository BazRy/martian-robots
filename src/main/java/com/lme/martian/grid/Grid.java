package com.lme.martian.grid;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.lme.martian.robot.Orientation;
import com.lme.martian.robot.Position;
import com.lme.martian.robot.Robot;

/**
 * Rectangular structure with a max X and Y axis determined by MAX_COORDINATE
 * At any one time the grid can only be occupied by a single {@link Robot}
 * Maintains a running list of positions that exceed the grid boundaries,  where by a {@link Robot}
 * has attempted to move to that position.
 * 
 * @author barryryan
 *
 */
public class Grid {
	
	private final int xAxis;
	private final int yAxis;
	protected static final int MIN_COORDINATE = 1;
	protected static final int MAX_COORDINATE = 50;
	private Robot currentRobot;
	private List<Position> lostPositions = new ArrayList<>();
	
	public Grid(final int xAxis, final int yAxis) throws GridException {
		
		final List<Integer> validGridCordinates = IntStream.rangeClosed(MIN_COORDINATE, MAX_COORDINATE).boxed().collect(Collectors.toList());

		if (!(validGridCordinates.contains(xAxis) && validGridCordinates.contains(yAxis))) {
			throw new GridException(String.format("Grid Coordinates Must Be >= %s & <= %s", MIN_COORDINATE, MAX_COORDINATE));
		}

		this.xAxis = xAxis;
		this.yAxis = yAxis;
	}

	protected int getxAxis() {
		return xAxis;
	}

	protected int getyAxis() {
		return yAxis;
	}
	
	public Robot initialiseNewRobot (final int xAxis, final int yAxis, final Orientation orientation) throws GridException{
		if (isOccupied()) {
			throw new GridException("Grid Is Occupied With Another Robot");
		}
		if (coordinatesExceedGridBounds(xAxis, yAxis)) {
			throw new GridException("Grid XY Axis Exceeded, Re-enter Valid Start Position For Robot");
		}
		currentRobot = new Robot(xAxis, yAxis, orientation);
		return currentRobot;
	}
	
	protected Robot getCurrentRobot() {
		return currentRobot;
	}
	
	protected boolean isOccupied() {
		return currentRobot != null;
	}
	
	public boolean isLostPosition(final Position position) {
		return lostPositions.contains(position);
	}
	
	public boolean coordinatesExceedGridBounds(final int xAxis, final int yAxis) {
		return xAxis < 0 || xAxis > this.xAxis || yAxis < 0 || yAxis > this.yAxis;
	}

	public void finalizeRobotInstructions() {
		final Position lostPosition = currentRobot.getLostPosition();
		if (lostPosition != null) {
			lostPositions.add(lostPosition);
		}
		currentRobot = null;
	}
	
}
