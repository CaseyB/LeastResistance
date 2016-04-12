package productions.moo.leastresistance.events;

import productions.moo.leastresistance.models.Space;

public class GridUpdateEvent
{
	public Space space;

	public GridUpdateEvent(final Space space)
	{
		this.space = space;
	}
}
