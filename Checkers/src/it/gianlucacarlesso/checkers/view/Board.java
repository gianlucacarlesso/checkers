package it.gianlucacarlesso.checkers.view;

import it.gianlucacarlesso.checkers.R;
import it.gianlucacarlesso.checkers.utilities.DisplayProperties;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class Board extends View {
	private Context context;
	private Bitmap board;
	private int shadow = 17;
	private Point screen_size;

	public Board(Context _context) {
		super(_context);
		context = _context;

		graphicInitialization();
	}

	public Board(Context _context, AttributeSet attrs) {
		super(_context, attrs);
		context = _context;

		graphicInitialization();
	}

	public Board(Context _context, AttributeSet attrs, int defStyle) {
		super(_context, attrs, defStyle);
		context = _context;

		graphicInitialization();
	}

	@Override
	public void onDraw(Canvas canvas) {
		int pos_x = 0;
		int pos_y = 0;

		pos_x = (int) (1.0 * board.getWidth() / screen_size.x * shadow / 2);

		// Draw picture of board

		canvas.drawBitmap(board, pos_x, pos_y, null);

		Log.i("XXX",
				"Board w:" + board.getWidth() + " h:" + board.getHeight()
						+ " - Canvas w:" + canvas.getWidth() + " h:"
						+ canvas.getHeight() + " this:" + this.getHeight());
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		return false;
	}

	@Override
	protected void onMeasure(int w, int h) {
		// I need to set the size of the view depending on the object that sign,
		// otherwise occupy all the space
		if (board != null) {
			setMeasuredDimension(board.getWidth(), board.getHeight());
		}
	}

	private void graphicInitialization() {
		screen_size = DisplayProperties.getMetrics(context);

		// Load picture of board
		Resources res = getResources();
		board = BitmapFactory.decodeResource(res, R.drawable.board);

		int new_y_board = (int) (1.0 * screen_size.x / board.getWidth() * board
				.getHeight());

		board = Bitmap.createScaledBitmap(board, screen_size.x, new_y_board,
				false);

	}
}
