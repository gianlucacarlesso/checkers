package it.gianlucacarlesso.checkers.view;

import it.gianlucacarlesso.checkers.R;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
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
	private int display_x, display_y;

	public Board(Context _context) {
		super(_context);
		context = _context;
		setDimDisplay();
	}

	public Board(Context _context, AttributeSet attrs) {
		super(_context, attrs);
		context = _context;
		setDimDisplay();
	}

	public Board(Context _context, AttributeSet attrs, int defStyle) {
		super(_context, attrs, defStyle);
		context = _context;
		setDimDisplay();
	}

	@Override
	public void onDraw(Canvas canvas) {
		int pos_x = 25;
		int pos_y = 0;
		int size_x = (int) ((display_x - 50) / BOX_INLINE);

		Resources res = getResources();
		Bitmap box_1 = BitmapFactory.decodeResource(res, R.drawable.box_1);
		box_1 = Bitmap.createScaledBitmap(box_1, size_x, size_x, false);
		Bitmap box_2 = BitmapFactory.decodeResource(res, R.drawable.box_2);
		box_2 = Bitmap.createScaledBitmap(box_2, size_x, size_x, false);

		
		Log.e("XXX", display_x + "--" + size_x);
		
		for (int i = 0; i < NUM_BOX; i++) {
			if (i % 2 == 0) {
				canvas.drawBitmap(box_1, pos_x, pos_y, null);
			} else {
				canvas.drawBitmap(box_2, pos_x, pos_y, null);
			}
			pos_x += size_x;

			if (i % BOX_INLINE == 0) {
				pos_x = 25;
				pos_y += box_1.getHeight();
			}
		}
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
}
