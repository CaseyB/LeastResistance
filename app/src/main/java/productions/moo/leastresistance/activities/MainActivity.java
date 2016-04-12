package productions.moo.leastresistance.activities;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.util.ErrorDialogManager;
import productions.moo.leastresistance.R;
import productions.moo.leastresistance.models.Space;

public class MainActivity extends AppCompatActivity
{
	private static final String RESULT_FRAGMENT = "RESULT_FRAGMENT";

	private FragmentManager _fragmentManager;
	private ResultFragment _results;

	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ButterKnife.bind(this);

		_fragmentManager = getSupportFragmentManager();
		_results = (ResultFragment)_fragmentManager.findFragmentById(R.id.results);
		_fragmentManager.beginTransaction().hide(_results).commit();
	}

	@OnClick(R.id.button_one)
	public void runExampleOne()
	{
		Space space = new Space();
		space.addColumn(3, 6, 5, 8, 3);
		space.addColumn(4, 1, 9, 4, 7);
		space.addColumn(1, 8, 3, 1, 2);
		space.addColumn(2, 2, 9, 3, 8);
		space.addColumn(8, 7, 9, 2, 6);
		space.addColumn(8, 4, 5, 6, 4);

		calculateSpace(space);
	}

	@OnClick(R.id.button_two)
	public void runExampleTwo()
	{
		Space space = new Space();
		space.addColumn(3, 6, 5, 8, 3);
		space.addColumn(4, 1, 9, 4, 7);
		space.addColumn(1, 8, 3, 1, 2);
		space.addColumn(2, 2, 9, 3, 1);
		space.addColumn(8, 7, 9, 2, 2);
		space.addColumn(6, 4, 5, 6, 3);

		calculateSpace(space);
	}

	@OnClick(R.id.button_three)
	public void runExampleThree()
	{
		Space space = new Space();
		space.addColumn(19, 21, 20);
		space.addColumn(10, 23, 12);
		space.addColumn(19, 20, 20);
		space.addColumn(10, 19, 11);
		space.addColumn(19, 12, 10);

		calculateSpace(space);
	}

	private void calculateSpace(final Space space)
	{
		_results.setSpace(space);
		_fragmentManager.beginTransaction()
			.show(_results)
			.addToBackStack(RESULT_FRAGMENT)
			.commit();
	}

	@Override
	public void onBackPressed ()
	{
		if(_fragmentManager.getBackStackEntryCount() > 0)
		{
			_fragmentManager.popBackStack();
		}
		else
		{
			super.onBackPressed();
		}
	}
}
