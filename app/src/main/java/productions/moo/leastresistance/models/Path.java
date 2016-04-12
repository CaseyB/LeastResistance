package productions.moo.leastresistance.models;

import java.util.ArrayList;
import java.util.List;

public class Path
{
	private int _score;
	private List<Integer> _steps;
	private boolean _completed;

	public Path()
	{
		_score = 0;
		_steps = new ArrayList<>();
		_completed = false;
	}

	public int getScore ()
	{
		return _score;
	}

	public void setScore (int score)
	{
		_score = score;
	}

	public List<Integer> getSteps ()
	{
		return _steps;
	}

	public void addStep (int step)
	{
		_steps.add(step);
	}

	public boolean completed ()
	{
		return _completed;
	}

	public void setCompleted (boolean completed)
	{
		_completed = completed;
	}
}
