package com.lme.martian.robot;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.lme.martian.grid.Grid;

/**
 * Is initialised (constructed) and has access to a {@link Grid} structure.  
 * Currently implements minimal (move left, right & forward) operations.
 * 
 * @author barryryan
 *
 */
public class Robot {

	private Position currentPosition;
	private Position lostPosition;
	protected static final String LOST = "LOST";
	
	private Map<Instruction, RobotInstruction> instructionsMap = ImmutableMap.of(
			Instruction.LEFT, (grid -> turnLeft(grid)),
			Instruction.RIGHT, (grid -> turnRight(grid)),
			Instruction.FORWARD, (grid -> moveForward(grid))
		);
	
	public Robot(final int xAxis, final int yAxis, final Orientation orientation) {
		this.currentPosition = new Position(xAxis, yAxis, orientation);
	}
	
	public String executeInstructions (final Grid grid, final List<Instruction> instructions) {
		
		try {
			for (Instruction instruction : instructions) {
				if (lostPosition == null) {
					instructionsMap.get(instruction).execute(grid);
				} else {
					break;
				}
			}
		} finally {
			grid.finalizeRobotInstructions();
		}
		return (new StringBuilder().append(currentPosition.toString()).append(lostPosition != null ? LOST : "")).toString();
	}
	
	private void turnLeft(final Grid grid) {
		this.currentPosition.setOrientation(Orientation.getAdjacentOrientations(this.currentPosition.getOrientation()).getLeft());
	}
	
	private void turnRight(final Grid grid) {
		this.currentPosition.setOrientation(Orientation.getAdjacentOrientations(this.currentPosition.getOrientation()).getRight());
	}

	private void moveForward(final Grid grid) {
		int xAxis = this.currentPosition.getxAxis();
		int yAxis = this.currentPosition.getyAxis();
		
		switch (this.currentPosition.getOrientation()) {
			case N:
				yAxis++;
				break;
			case S:
				yAxis--;
				break;
			case E:
				xAxis++;
				break;
			case W:
				xAxis--;
				break;
		}
		
		Position proposedPosition = new Position(xAxis, yAxis, this.currentPosition.getOrientation());
		if (!grid.isLostPosition(proposedPosition)) {
			if (grid.coordinatesExceedGridBounds(xAxis, yAxis)) {
				this.lostPosition = proposedPosition;
			} else {
				this.currentPosition = proposedPosition;
			}
		}
	}
	
	public Position getLostPosition() {
		return lostPosition;
	}
	
}
