package it.gianlucacarlesso.checkers;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class CheckersActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_checkers);
	}
	
	public void startCheckerBoard(View view) {
		Intent intent = new Intent(this, CheckerboardActivity.class);
	    startActivity(intent);
	}

}
