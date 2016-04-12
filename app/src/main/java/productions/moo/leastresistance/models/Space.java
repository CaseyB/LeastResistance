package productions.moo.leastresistance.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Space
{
	private List<List<Integer>> _grid;

	public Space()
	{
		_grid = new ArrayList<>();
	}

	public void addColumn(Integer... values)
	{
		// validate length
		if(_grid.size() > 0 && values.length != _grid.get(0).size())
		{
			throw new IndexOutOfBoundsException("Column should be " + _grid.get(0).size() + " values but " + values.length + " were passed in");
		}

		List<Integer> column = new ArrayList<>(Arrays.asList(values));
		_grid.add(column);
	}

	public List<Integer> getColumn(int column)
	{
		if(column < _grid.size())
		{
			return _grid.get(column);
		}
		else
		{
			return null;
		}
	}
}
