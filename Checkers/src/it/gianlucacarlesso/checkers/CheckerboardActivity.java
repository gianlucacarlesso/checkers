package it.gianlucacarlesso.checkers;

import it.gianlucacarlesso.checkers.utilities.DisplayProperties;
import it.gianlucacarlesso.checkers.utilities.ImageUtilities;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Point;
import android.graphics.drawable.Drawable;

public class CheckerboardActivity extends Activity {

	@SuppressWarnings("deprecation")
	@Override
	@TargetApi(16)
	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_checkerboard);

		View layout = getWindow().getDecorView().findViewById(
				android.R.id.content);
		Drawable image = getResources().getDrawable(
				R.drawable.background_activity);

		Point screen_size = DisplayProperties
				.getMetrics(getApplicationContext());
		image = ImageUtilities.resize(image, screen_size.x, screen_size.y);

		if (Build.VERSION.SDK_INT >= 16) {
			layout.setBackground(image);
		} else {
			layout.setBackgroundDrawable(image);
		}
	}

}
