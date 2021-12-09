package com.lme.martian.grid;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.lme.martian.robot.Orientation;

public class GridTest {
	
	private static Grid defaultGrid;
	
	@BeforeAll
	public static void setUp () throws GridException {
		defaultGrid = new Grid(Grid.MAX_COORDINATE, Grid.MAX_COORDINATE);
	}
	
	@Test
	public void gridShouldCreateSuccessfullyMinMinCoordinates () throws GridException {
		assembleAndAssertGridCoordinates(Grid.MIN_COORDINATE, Grid.MIN_COORDINATE);
	}
	
	@Test
	public void gridShouldCreateSuccessfullyMinMaxCoordinates () throws GridException {
		assembleAndAssertGridCoordinates(Grid.MIN_COORDINATE, Grid.MAX_COORDINATE);
	}
	
	@Test
	public void gridShouldCreateSuccessfullyMaxMinCoordinates () throws GridException {
		assembleAndAssertGridCoordinates(Grid.MAX_COORDINATE, Grid.MIN_COORDINATE);
	}
	
	@Test
	public void gridShouldCreateSuccessfullyMaxMaxCoordinates () throws GridException {
		assembleAndAssertGridCoordinates(Grid.MAX_COORDINATE, Grid.MAX_COORDINATE);
	}
	
	@Test
	public void gridShouldThrowExceptionXLessThanMin () {
		assertThrows(0, Grid.MIN_COORDINATE);
	}
	
	@Test
	public void gridShouldThrowExceptionYLessThanMin () {
		assertThrows(Grid.MIN_COORDINATE, 0);
	}
	
	@Test
	public void gridShouldThrowExceptionXGreaterThanMax () {
		assertThrows(51, Grid.MIN_COORDINATE);
	}
	
	@Test
	public void gridShouldThrowExceptionYGreaterThanMax () {
		assertThrows(Grid.MAX_COORDINATE, 51);
	}
	
	@Test
	public void shouldInitialiseNewRobot() throws GridException{
		defaultGrid.initialiseNewRobot(30, 30, Orientation.E);
		assertTrue(defaultGrid.isOccupied());
	}
	
	@Test
	public void shouldThrowExceptionOnGridSizeExceededXAxis() throws GridException{
		Assertions.assertThrows(GridException.class, () -> defaultGrid.initialiseNewRobot(51, 30, Orientation.E));
	}
	
	@Test
	public void shouldNotExceedGridBounds() throws GridException{
		assertFalse(defaultGrid.coordinatesExceedGridBounds(25, 30));
	}
	
	@Test
	public void shouldExceedGridBounds() throws GridException{
		assertTrue(defaultGrid.coordinatesExceedGridBounds(51, 30));
	}
	
	@Test
	public void shouldThrowExceptionOnGridSizeExceededYAxis() throws GridException{
		Assertions.assertThrows(GridException.class, () -> defaultGrid.initialiseNewRobot(40, 51, Orientation.E));
	}
	
	@Test
	public void shouldFinaliseGridProperly() throws GridException {
		defaultGrid.initialiseNewRobot(30, 30, Orientation.E);
		defaultGrid.finalizeRobotInstructions();
		assertFalse(defaultGrid.isOccupied());
	}
	
	private final void assembleAndAssertGridCoordinates(final int xAxis, final int yAxis) throws GridException {
		Grid grid = new Grid(xAxis, yAxis);
		assertEquals(xAxis, grid.getxAxis());
		assertEquals(yAxis, grid.getyAxis());
	}
	
	private final void assertThrows(final int xAxis, final int yAxis) {
		Assertions.assertThrows(GridException.class, () -> new Grid(xAxis, yAxis));
	}

}
