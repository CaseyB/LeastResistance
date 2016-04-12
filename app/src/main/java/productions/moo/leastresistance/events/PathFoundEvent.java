package productions.moo.leastresistance.events;

import productions.moo.leastresistance.models.Path;

public class PathFoundEvent
{
	public Path path;

	public PathFoundEvent(final Path path)
	{
		this.path = path;
	}
}
