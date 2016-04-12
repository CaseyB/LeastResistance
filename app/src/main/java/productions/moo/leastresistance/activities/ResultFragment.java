package productions.moo.leastresistance.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import productions.moo.leastresistance.R;
import productions.moo.leastresistance.events.GridUpdateEvent;
import productions.moo.leastresistance.events.PathFoundEvent;
import productions.moo.leastresistance.models.Path;
import productions.moo.leastresistance.models.Space;
import productions.moo.leastresistance.utils.GridRunner;

public class ResultFragment extends Fragment
{
	@Bind(R.id.results)
	View _results;

	@Bind(R.id.spinner)
	View _spinner;

	@Bind(R.id.success)
	TextView _success;

	@Bind(R.id.score)
	TextView _score;

	@Bind(R.id.steps)
	LinearLayout _stepsLayout;

	private EventBus _eventBus = EventBus.getDefault();

	public ResultFragment ()
	{
		// Required empty public constructor
	}

	@Override
	public void onAttach (Context context)
	{
		super.onAttach(context);
		_eventBus.register(this);
	}

	@Override
	public void onDetach ()
	{
		super.onDetach();
		_eventBus.unregister(this);
	}

	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_result, container, false);
		ButterKnife.bind(this, view);
		return view;
	}

	public void setSpace (final Space space)
	{
		_results.setVisibility(View.GONE);
		_spinner.setVisibility(View.VISIBLE);

		_eventBus.post(new GridUpdateEvent(space));
	}

	public void onEventAsync (GridUpdateEvent event)
	{
		Path path = GridRunner.findPath(event.space);
		_eventBus.post(new PathFoundEvent(path));
	}

	public void onEventMainThread (PathFoundEvent event)
	{
		_success.setText(event.path.completed() ? getString(R.string.yes) : getString(R.string.no));
		_score.setText(String.format("%d", event.path.getScore()));
		_stepsLayout.removeAllViews();

		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		params.leftMargin = (int) getResources().getDimension(R.dimen.step_padding);
		params.rightMargin = (int) getResources().getDimension(R.dimen.step_padding);
		for (Integer step : event.path.getSteps())
		{
			TextView view = new TextView(getContext());
			view.setTextSize(20);
			view.setText(String.format("%d", step + 1));
			_stepsLayout.addView(view, params);
		}

		_spinner.setVisibility(View.GONE);
		_results.setVisibility(View.VISIBLE);
	}
}
