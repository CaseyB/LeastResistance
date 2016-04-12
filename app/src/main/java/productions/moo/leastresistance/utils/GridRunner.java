package productions.moo.leastresistance.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import productions.moo.leastresistance.models.Path;
import productions.moo.leastresistance.models.Space;

public class GridRunner
{
	public static Path findPath (final Space space)
	{
		List<Path> possiblePaths = new ArrayList<>();
		List<Integer> firstColumn = space.getColumn(0);
		for (int row = 0; row < firstColumn.size(); row++)
		{
			Path path = new Path();
			path.addStep(row, firstColumn.get(row));
			possiblePaths.addAll(getPathFrom(0, row, path, space));
		}

		return findBestPath(possiblePaths);
	}

	private static List<Path> getPathFrom (int columnIndex, int rowIndex, Path path, Space space)
	{
		int nextColumnIndex = columnIndex + 1;

		// Have we hit the end?
		if (nextColumnIndex == space.getWidth())
		{
			path.setCompleted(true);
			return Collections.singletonList(path);
		}

		// Here we'll use where we are to generate up to three forks in the path based on where we
		// can go from here
		List<Path> paths = new ArrayList<>();
		if (space.getHeight() <= 3)
		{
			List<Integer> nextColumn = space.getColumn(nextColumnIndex);
			// If we only have three rows then we'll try them all
			for (int nextRowIndex = 0; nextRowIndex < nextColumn.size(); nextRowIndex++)
			{
				paths.addAll(step(nextColumnIndex, nextRowIndex, path, space));
			}
		}
		else
		{
			// First try in the same row
			int nextRowIndex = rowIndex;
			paths.addAll(step(nextColumnIndex, nextRowIndex, path, space));

			// Now try row - 1 % height
			nextRowIndex = (rowIndex - 1) % space.getHeight();
			if (nextRowIndex < 0)
			{
				nextRowIndex += space.getHeight();
			}
			paths.addAll(step(nextColumnIndex, nextRowIndex, path, space));

			// Now try row + 1 % height
			nextRowIndex = (rowIndex + 1) % space.getHeight();
			paths.addAll(step(nextColumnIndex, nextRowIndex, path, space));
		}

		return paths;
	}

	private static List<Path> step (int nextColumnIndex, int nextRowIndex, Path path, Space space)
	{
		List<Integer> nextColumn = space.getColumn(nextColumnIndex);
		int nextStep = nextColumn.get(nextRowIndex);
		// Limit to 50 or less
		if (path.getScore() + nextStep < 50)
		{
			Path fork = path.clone();
			fork.addStep(nextRowIndex, nextStep);
			return getPathFrom(nextColumnIndex, nextRowIndex, fork, space);
		}
		else
		{
			return Collections.singletonList(path);
		}
	}

	private static Path findBestPath (List<Path> possiblePaths)
	{
		// The best path is the one that made it all the way through with the lowest score or, if none
		// made it all the way through then the one that went farthest.
		if (anyPassed(possiblePaths))
		{
			return findShortestPath(possiblePaths);
		}
		else
		{
			return findLongestPath(possiblePaths);
		}
	}

	private static boolean anyPassed (List<Path> possiblePaths)
	{
		for (Path path : possiblePaths)
		{
			if (path.completed())
			{
				return true;
			}
		}

		return false;
	}

	private static Path firstPassed (List<Path> possiblePaths)
	{
		for (Path path : possiblePaths)
		{
			if (path.completed())
			{
				return path;
			}
		}

		return null;
	}

	private static Path findShortestPath (List<Path> possiblePaths)
	{
		Path result = firstPassed(possiblePaths);

		for (Path path : possiblePaths)
		{
			if (path.completed() && path.getScore() < result.getScore())
			{
				result = path;
			}
		}

		return result;
	}

	private static Path findLongestPath (List<Path> possiblePaths)
	{
		Path result = possiblePaths.get(0);

		for (Path path : possiblePaths)
		{
			if (path.getSteps().size() > result.getSteps().size())
			{
				result = path;
			}
		}

		return result;
	}
}
