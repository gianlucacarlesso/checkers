package it.gianlucacarlesso.checkers;

import android.os.Bundle;
import android.app.Activity;

public class CheckerboardActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_checkerboard);
		//setContentView(new Board(this));
		
		
	}

}
