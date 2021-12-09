package com.lme.martian.robot;

import com.lme.martian.grid.Grid;

@FunctionalInterface
public interface RobotInstruction {
	
	void execute(Grid grid);

}
