package productions.moo.leastresistance;

import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Test;

import static org.junit.Assert.*;

import productions.moo.leastresistance.models.Space;

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
}