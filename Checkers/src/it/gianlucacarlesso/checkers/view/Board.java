package it.gianlucacarlesso.checkers.view;

import it.gianlucacarlesso.checkers.R;
import it.gianlucacarlesso.checkers.utilities.DisplayProperties;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class Board extends View {
	public static int NUM_BOX = 64;
	public static int BOX_INLINE = 8;

	private Context context;
	private Bitmap board;
	private int boarder_board = 30;
	private int display_x, display_y;

	public Board(Context _context) {
		super(_context);
		context = _context;
		setDimDisplay();

		graphicInitialization();
	}

	public Board(Context _context, AttributeSet attrs) {
		super(_context, attrs);
		context = _context;
		setDimDisplay();
		
		graphicInitialization();
	}

	public Board(Context _context, AttributeSet attrs, int defStyle) {
		super(_context, attrs, defStyle);
		context = _context;
		setDimDisplay();
		
		graphicInitialization();
	}

	@Override
	public void onDraw(Canvas canvas) {
		int pos_x = 25;
		int pos_y = 0;

		// Draw picture of board
		canvas.drawBitmap(board, pos_x, pos_y, null);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		return false;
	}

	private void setDimDisplay() {
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();

		display_x = display.getWidth();
		display_y = display.getHeight();
	}

	private void graphicInitialization() {
		// Load picture of board
		Resources res = getResources();
		board = BitmapFactory.decodeResource(res, R.drawable.board);

		Point screen_size = DisplayProperties.getMetrics(context);
		Log.i("SIZE", "w: " + screen_size.x + " h:" + screen_size.y);

		int new_x_board = screen_size.x - boarder_board;
		int new_y_board = (int) ((new_x_board / screen_size.x) * screen_size.y);
		
		Log.i("NEWSIZE", "w: " + new_x_board + " h: " + new_y_board);
		board = Bitmap.createScaledBitmap(board, new_x_board, new_y_board,
				false);
	}
}
