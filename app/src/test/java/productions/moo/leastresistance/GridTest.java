package productions.moo.leastresistance;

import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Test;

import static org.junit.Assert.*;

import productions.moo.leastresistance.models.Path;
import productions.moo.leastresistance.models.Space;
import productions.moo.leastresistance.utils.GridRunner;

@LargeTest
public class GridTest
{
	@Test
	public void createValidGrid ()
	{
		Space grid = new Space();
		grid.addColumn(1, 2, 3, 4, 5);
		grid.addColumn(1, 2, 3, 4, 5);
		grid.addColumn(1, 2, 3, 4, 5);
		grid.addColumn(1, 2, 3, 4, 5);
		grid.addColumn(1, 2, 3, 4, 5);
	}

	@Test(expected=IndexOutOfBoundsException.class)
	public void createLongColumn ()
	{
		Space grid = new Space();
		grid.addColumn(1, 2, 3, 4, 5);
		grid.addColumn(1, 2, 3, 4, 5, 6);
	}

	@Test(expected=IndexOutOfBoundsException.class)
	public void createShortColumn ()
	{
		Space grid = new Space();
		grid.addColumn(1, 2, 3, 4, 5);
		grid.addColumn(1, 2, 3, 4);
	}

	@Test
	public void exampleOne ()
	{
//		3 4 1 2 8 6
//		6 1 8 2 7 4
//		5 9 3 9 9 5
//		8 4 1 3 2 6
//		3 7 2 8 6 4

//		Yes
//		16
//		1 2 3 4 4 5

		Space space = new Space();
		space.addColumn(3, 6, 5, 8, 3);
		space.addColumn(4, 1, 9, 4, 7);
		space.addColumn(1, 8, 3, 1, 2);
		space.addColumn(2, 2, 9, 3, 8);
		space.addColumn(8, 7, 9, 2, 6);
		space.addColumn(8, 4, 5, 6, 4);

		Path path = GridRunner.findPath(space);
		assertTrue(path.completed());
		assertEquals(16, path.getScore());
	}

	@Test
	public void exampleTwo ()
	{
//		3 4 1 2 8 6
//		6 1 8 2 7 4
//		5 9 3 9 9 5
//		8 4 1 3 2 6
//		3 7 2 1 2 3

//		Yes
//		11
//		1 2 1 5 4 5

		Space space = new Space();
		space.addColumn(3, 6, 5, 8, 3);
		space.addColumn(4, 1, 9, 4, 7);
		space.addColumn(1, 8, 3, 1, 2);
		space.addColumn(2, 2, 9, 3, 1);
		space.addColumn(8, 7, 9, 2, 2);
		space.addColumn(6, 4, 5, 6, 3);

		Path path = GridRunner.findPath(space);
		assertTrue(path.completed());
		assertEquals(11, path.getScore());
	}
}