package com.lme.martian.robot;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.lme.martian.grid.Grid;
import com.lme.martian.grid.GridException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RobotTest {
	
	private static Grid defaultGrid;
	
	@BeforeAll
	public static void setUp () throws GridException {
		defaultGrid = new Grid(5, 3);
	}
	
	@Test
	@Order(1)
	public void shouldSuccessfullyMoveRobot() throws GridException {
		Robot robot = defaultGrid.initialiseNewRobot(1, 1, Orientation.E);
		assembleAndAssert("RFRFRFRF", "1 1 E", null, robot);
	}
	
	@Test
	@Order(2)
	public void shouldLoseRobotOffGrid() throws GridException {
		Robot robot = defaultGrid.initialiseNewRobot(3, 2, Orientation.N);
		Position lostPosition = new Position(3, 4, Orientation.N);
		assembleAndAssert("FRRFLLFFRRFLL", "3 3 N LOST", lostPosition, robot);
	}
	
	@Test
	@Order(3)
	public void shouldAvoidMovingToLostPosition() throws GridException {
		Robot robot = defaultGrid.initialiseNewRobot(0, 3, Orientation.W);
		assembleAndAssert("LLFFFLFLFL", "2 3 S", null, robot);
	}
	
	@Test
	@Order(4)
	public void shouldNotLoseRobotOffGridEncounteringMultipleLostPositions() throws GridException {
		Robot robotToRegisterAnotherLostPosition = defaultGrid.initialiseNewRobot(0, 3, Orientation.W);
		Position lostPosition = new Position(-1, 3, Orientation.W);
		assembleAndAssert("F", "0 3 W LOST", lostPosition, robotToRegisterAnotherLostPosition);

		Robot robotToAvoidMultipleLostPositions = defaultGrid.initialiseNewRobot(0, 3, Orientation.W);
		assembleAndAssert("FLLFFFLFLFL", "2 3 S", null, robotToAvoidMultipleLostPositions);
	}
	
	@Test
	@Order(5)
	public void shouldNotMoveWhenMissingInstructions() throws GridException {
		Robot robot = defaultGrid.initialiseNewRobot(0, 3, Orientation.W);
		assembleAndAssert("", "0 3 W", null, robot);
	}
	
	private static void assembleAndAssert(String instructionString, String excectedOutput, Position expectedLostPosition, Robot robot) {
		final List<Instruction> instructions = InstructionsUtil.parseInstructionString(instructionString);
		final String actualOutput = robot.executeInstructions(defaultGrid, instructions);
		assertEquals(excectedOutput, actualOutput.trim());
		assertEquals(expectedLostPosition, robot.getLostPosition());
	}
	
}
