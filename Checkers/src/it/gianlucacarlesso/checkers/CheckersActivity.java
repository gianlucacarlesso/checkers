package it.gianlucacarlesso.checkers;

import it.gianlucacarlesso.checkers.utilities.DisplayProperties;
import it.gianlucacarlesso.checkers.utilities.ImageUtilities;
import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

public class CheckersActivity extends Activity {

	@SuppressWarnings("deprecation")
	@Override
	@TargetApi(16)
	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_checkers);

		// Set activity background
		View layout = getWindow().getDecorView().findViewById(android.R.id.content);
		Drawable image = getResources().getDrawable(
				R.drawable.background_checkers_activity);
		
		Point screen_size = DisplayProperties.getMetrics(getApplicationContext());
		image = ImageUtilities.resize(image, screen_size.x, screen_size.y);

		if (Build.VERSION.SDK_INT >= 16) {
			layout.setBackground(image);
		} else {
			layout.setBackgroundDrawable(image);
		}
		
		// Set font
		Typeface typface = Typeface.createFromAsset(getAssets(), "fonts/english.ttf");
		TextView title = (TextView) findViewById(R.id.title_app);
		title.setTypeface(typface, Typeface.BOLD);
	}

	public void startCheckerBoard(View view) {
		Intent intent = new Intent(this, CheckerboardActivity.class);
		startActivity(intent);
	}

}
