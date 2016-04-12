package productions.moo.leastresistance.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import productions.moo.leastresistance.models.Path;
import productions.moo.leastresistance.models.Space;

public class GridRunner
{
	public static Path findPath(final Space space)
	{
		List<Path> possiblePaths = new ArrayList<>();
		List<Integer> firstColumn = space.getColumn(0);
		for (int row = 0; row < firstColumn.size(); row++)
		{
			Path path = new Path();
			path.addStep(row, firstColumn.get(row));
			possiblePaths.addAll(getPathFrom(0, row, path, space));
		}

		return findShortestPath(possiblePaths);
	}

	private static List<Path> getPathFrom(int columnIndex, int rowIndex, Path path, Space space)
	{
		int nextColumnIndex = columnIndex + 1;

		// Have we hit the end?
		if(nextColumnIndex == space.getWidth())
		{
			path.setCompleted(true);
			return Collections.singletonList(path);
		}

		// Here we'll use where we are to generate up to three forks in the path based on where we
		// can go from here
		List<Path> paths = new ArrayList<>();
		List<Integer> nextColumn = space.getColumn(nextColumnIndex);
		if(space.getHeight() <= 3)
		{
			// If we only have three rows then we'll try them all
			for (int nextRowIndex = 0; nextRowIndex < nextColumn.size(); nextRowIndex++)
			{
				Path fork = path.clone();

				// TODO: Limit score to 50
				int nextStep = nextColumn.get(nextRowIndex);
				fork.addStep(nextRowIndex, nextStep);
				paths.addAll(getPathFrom(nextColumnIndex, nextRowIndex, fork, space));
			}
		}
		else
		{
			// First try in the same row
			int nextRowIndex = rowIndex;
			Path fork = path.clone();
			int nextStep = nextColumn.get(nextRowIndex);
			// TODO: Limit to 50
			fork.addStep(nextRowIndex, nextStep);
			paths.addAll(getPathFrom(nextColumnIndex, nextRowIndex, fork, space));

			// Now try row - 1 % height
			nextRowIndex = (rowIndex - 1) % space.getHeight();
			if (nextRowIndex < 0) nextRowIndex += space.getHeight();
			fork = path.clone();
			nextStep = nextColumn.get(nextRowIndex);
			// TODO: Limit to 50
			fork.addStep(nextRowIndex, nextStep);
			paths.addAll(getPathFrom(nextColumnIndex, nextRowIndex, fork, space));

			// Now try row + 1 % height
			nextRowIndex = (rowIndex + 1) % space.getHeight();
			fork = path.clone();
			nextStep = nextColumn.get(nextRowIndex);
			// TODO: Limit to 50
			fork.addStep(nextRowIndex, nextStep);
			paths.addAll(getPathFrom(nextColumnIndex, nextRowIndex, fork, space));
		}

		return paths;
	}

	private static Path findShortestPath(List<Path> possiblePaths)
	{
		Path result = possiblePaths.get(0);

		for (Path path : possiblePaths)
		{
			if(path.getScore() < result.getScore())
			{
				result = path;
			}
		}

		return result;
	}
}
