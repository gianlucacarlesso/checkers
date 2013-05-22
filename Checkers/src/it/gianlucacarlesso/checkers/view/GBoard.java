package it.gianlucacarlesso.checkers.view;

import it.gianlucacarlesso.checkers.R;
import it.gianlucacarlesso.checkers.logic.Board;
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

public class GBoard extends View {
	private static Point SIZE_BOARD_ORIGIN = new Point(1319, 1406);
	private static Point CENTER_BOARD_ORIGIN = new Point(649, 627);
	private static Point SIZE_BOX_ORIGIN = new Point(140, 140);
	private Board board_logic = new Board();

	private Context context;
	private Bitmap board;
	private Bitmap piece_black;
	private Bitmap piece_white;
	private int shadow = 17;
	private Point screen_size;
	private Point[][] matrix = new Point[Board.NUM_BOX_ROW][Board.NUM_BOX_ROW];
	private int startGame = 0;

	int box_size_x;
	int box_size_y;
	int board_center_x;
	int board_center_y;

	public GBoard(Context _context) {
		super(_context);
		context = _context;

		graphicInitialization();
	}

	public GBoard(Context _context, AttributeSet attrs) {
		super(_context, attrs);
		context = _context;

		graphicInitialization();
	}

	public GBoard(Context _context, AttributeSet attrs, int defStyle) {
		super(_context, attrs, defStyle);
		context = _context;

		graphicInitialization();
	}

	@Override
	public void onDraw(Canvas canvas) {
		Log.i("onDraw", "Chiamato...");
		int pos_x = 0;
		int pos_y = 0;
		pos_x = (int) (1.0 * board.getWidth() / screen_size.x * shadow / 2);

		// Draw picture of board
		canvas.drawBitmap(board, pos_x, pos_y, null);

		int correct = (int) ((box_size_x + pos_x - piece_black.getWidth()) / 2.0);
		if (startGame < 2) {
			startGame++;

			// Draw pieces
			drawPiecesStart(canvas, correct, pos_x);
		} else {

		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();

		for (int i = 0; i < Board.NUM_BOX_ROW; i++) {
			for (int j = 0; j < Board.NUM_BOX_ROW; j++) {
				if (matrix[i][j].x <= (int) x
						&& matrix[i][j].x + box_size_x >= (int) x
						&& matrix[i][j].y <= (int) y
						&& matrix[i][j].y + box_size_y >= (int) y) {

					if (board_logic.board[i][j] != null) {
						Log.i("XXX", "C'è una pedina, giocatore:"
								+ board_logic.board[i][j].player);
						this.invalidate();
					}

					i = Board.NUM_BOX_ROW + 1;
					j = Board.NUM_BOX_ROW + 1;
				}
			}
		}
		return false;
	}

	@Override
	protected void onMeasure(int w, int h) {
		// I need to set the size of the view depending on the object that sign,
		// otherwise occupy all the space

		Log.i("onMeasure", "Chiamato...");
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

		board_center_x = (int) (1.0 * board.getWidth() / SIZE_BOARD_ORIGIN.x * CENTER_BOARD_ORIGIN.x);
		board_center_y = (int) (1.0 * board.getHeight() / SIZE_BOARD_ORIGIN.y * CENTER_BOARD_ORIGIN.y);

		box_size_x = (int) (1.0 * board.getWidth() / SIZE_BOARD_ORIGIN.x * SIZE_BOX_ORIGIN.x);
		box_size_y = (int) (1.0 * board.getHeight() / SIZE_BOARD_ORIGIN.y * SIZE_BOX_ORIGIN.y);

		// Load pieces images
		piece_black = BitmapFactory.decodeResource(res,
				R.drawable.piece_black_horizontal);
		piece_white = BitmapFactory.decodeResource(res,
				R.drawable.piece_white_horizontal);

		int new_x_piece = (int) (board.getWidth() / (Board.NUM_BOX_ROW + 1.3));
		if (new_x_piece >= box_size_x) {
			new_x_piece = box_size_x - 5;
		}
		int new_y_piece = (int) (1.0 * piece_black.getHeight()
				/ piece_black.getWidth() * new_x_piece);

		piece_black = Bitmap.createScaledBitmap(piece_black, new_x_piece,
				new_y_piece, false);
		piece_white = Bitmap.createScaledBitmap(piece_white, new_x_piece,
				new_y_piece, false);

		// Calculating all the boxes of Damiera
		int xbox = board_center_x - box_size_x * Board.NUM_BOX_ROW / 2;
		int ybox = board_center_y - box_size_y * Board.NUM_BOX_ROW / 2;
		for (int i = 0; i < Board.NUM_BOX_ROW; i++) {
			for (int j = 0; j < Board.NUM_BOX_ROW; j++) {
				matrix[i][j] = new Point(xbox, ybox);
				xbox += box_size_x;
			}
			xbox = board_center_x - box_size_x * Board.NUM_BOX_ROW / 2;
			ybox += box_size_y;
		}
	}

	private void drawPiecesStart(Canvas canvas, int correct, int pos_x) {
		// Black pieces
		for (int i = 0; i < Board.PLAYER_ROWS; i++) {
			int j = 1;
			if (i % 2 == 1) {
				j = 0;
			}
			for (; j < Board.NUM_BOX_ROW; j += 2) {
				canvas.drawBitmap(piece_black, matrix[i][j].x + correct
						+ ((int) (pos_x / 2.0) + 2), matrix[i][j].y + correct,
						null);
			}
		}

		// White pieces
		for (int i = Board.NUM_BOX_ROW - Board.PLAYER_ROWS; i < Board.NUM_BOX_ROW; i++) {
			int j = 1;
			if (i % 2 == 1) {
				j = 0;
			}
			for (; j < Board.NUM_BOX_ROW; j += 2) {
				canvas.drawBitmap(piece_white, matrix[i][j].x + correct
						+ ((int) (pos_x / 2.0) + 2), matrix[i][j].y + correct,
						null);
			}
		}
	}
}
