package com.lme.martian.grid;

import static com.lme.martian.robot.InstructionsUtil.parseInstructionString;

import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.math.NumberUtils;

import com.lme.martian.robot.Instruction;
import com.lme.martian.robot.Orientation;
import com.lme.martian.robot.Robot;

/**
 * Parse user input and construct a new {@link Grid} structure based on inputs provided.
 * Report errors and handle lifecycle of {@link Grid} & {@link Robot} construction along with 
 * execution of available {@link Robot} operations
 * 
 * @author barryryan
 *
 */
public class UserInputHandler {

	private static final int USER_ROBOT_MAX_INSTRUCTIONS_LENGTH = 100;
	private static final String USER_GRID_TEXT = "Please Enter Upper Right Grid X & Y Coordinates (separated by a space).  Enter 'end' to halt programme";
	private static final String USER_ROBOT_INITIALISE_TEXT = "Please Enter Robot Start Position - X & Y Coordinates & NSEW Orientation (separated by a space)";
	private static final String USER_ROBOT_INSTRUCT_TEXT = String.format("Please Enter Robot Instructions (no spaces),  acceptable values are: %s", Instruction.getValidInstructionString()) ;
	private static final String USER_ROBOT_INSTRUCTION_MAX_LENGTH_EXCEEDED = String.format("Max Instruction Length (%s) Exceeded", USER_ROBOT_MAX_INSTRUCTIONS_LENGTH);
	private static final String USER_ROBOT_INVALID_INSTRUCTIONS = "Invalid Robot Instructions Entered";
		
	private Grid grid;
	
	public UserInputHandler() {
		initialise();
	}
	
	private void initialise() {
		System.out.println(USER_GRID_TEXT);
		Scanner scanner = new Scanner(System.in);
		
		String input = scanner.nextLine();
		
		while (!input.equalsIgnoreCase("end")) {
			if(grid == null) {
				initialiseGrid(input);
			} else {
				if (grid.isOccupied()) {
					instructCurrentRobotOnGrid(input);
				} else {
					initialiseNewRobotOnGrid(input);
				}
			}
			input = scanner.nextLine();
		}
		scanner.close();
	}
	
	private void initialiseGrid(String input) {
		String[] args = input.split(" ");
		if(args.length != 2) {
			System.out.println("Error Creating Grid, invalid argumants\n" + USER_GRID_TEXT);
			return;
		}
		int x = NumberUtils.toInt(args[0], 0);
		int y = NumberUtils.toInt(args[1], 0);
		try {
			grid = new Grid(x, y);
			System.out.println(USER_ROBOT_INITIALISE_TEXT);
		} catch (GridException ex) {
			System.out.println(ex.getMessage() +"\n" + USER_GRID_TEXT);
		}
	}
	
	private void initialiseNewRobotOnGrid(String input) {
		
		String[] args = input.split(" ");
		if(args.length != 3) {
			System.out.println("Error Creating Robot, invalid arguments\n" + USER_ROBOT_INITIALISE_TEXT);
			return;
		}
		int x = NumberUtils.toInt(args[0], -1);
		int y = NumberUtils.toInt(args[1], -1);

		try {
			Orientation orientation = Orientation.valueOf(args[2]);
			grid.initialiseNewRobot(x, y, orientation);
			System.out.println(USER_ROBOT_INSTRUCT_TEXT);
		} catch (GridException | IllegalArgumentException ex) {
			System.out.println(ex.getMessage() +"\n" + USER_ROBOT_INITIALISE_TEXT);
		}
	}
	
	private void instructCurrentRobotOnGrid(String input) {
		if (input.length() > USER_ROBOT_MAX_INSTRUCTIONS_LENGTH) {
			System.out.println(USER_ROBOT_INSTRUCTION_MAX_LENGTH_EXCEEDED + "\n" + USER_ROBOT_INSTRUCT_TEXT);
		}
		List<Instruction> instructions = parseInstructionString(input);
        if (!instructions.isEmpty()) {
        	System.out.println(grid.getCurrentRobot().executeInstructions(grid, instructions));
			System.out.println(USER_ROBOT_INITIALISE_TEXT);
        } else {
			System.out.println(USER_ROBOT_INVALID_INSTRUCTIONS + "\n" + USER_ROBOT_INSTRUCT_TEXT);
        }
        
	}
}
