import java.util.Random;

public class MinesweeperModel {
	private int rows;
	private int columns;
	private int mines;
	private boolean won;
	private boolean gameOver;
	private int[][] gameBoard;
	private boolean[][] spotRevealed;
	private boolean[][] flagged;
	
	public MinesweeperModel(int rows, int columns, int mines) {
		this.rows = rows;
		this.columns = columns;
		this.mines = mines;
		this.won = false;
		this.gameOver = false;
		this.gameBoard = new int[rows][columns];
		this.spotRevealed = new boolean[rows][columns];
		this.flagged = new boolean[rows][columns];
		
		fillBoard();
		placeMines();
	}
	
	public void fillBoard() {
		gameBoard = new int[rows][columns];
		for(int row = 0; row < rows; row++) {
			for(int column = 0; column < columns; column++) {
				gameBoard[row][column] = 0;
				spotRevealed[row][column] = false;
				flagged[row][column] = false;
			}
		}
	}
	
	public void placeMines() {
		Random rnd = new Random();
		int minesMade = 0;
		
		while(minesMade < mines) {
			int row = rnd.nextInt(rows);
			int column = rnd.nextInt(columns);
			
			if(gameBoard[row][column] != -1) {
				gameBoard[row][column] = -1;
				minesMade++;
			}
		}
	}
	
	
	public void revealed(int row, int column) {
		if(!isValid(row, column) || spotRevealed[row][column] || flagged[row][column]) {
			return;
		}
		
		spotRevealed[row][column] = true;
		
		if(gameBoard[row][column] == -1) {
		gameOver = true;
		//} else if(gameBoard[row][column] != -1) {
			//for(int r = -1; r <= 1; r++) {
				//for(int c = -1; c <= 1; c++) {
					//int newRow = row + r;
					//int newColumn = column + c;
					//if(isValid(newRow, newColumn)) {
						//revealed(newRow, newColumn);
					//}
					//revealed(row + r, column + c);
				//}
			//}
		}
	}
	
	public void revealAll(int row, int column) {
		for(int r = 0; r <= rows; r++) {
			for(int c = 0; c <= columns; c++) {
				revealed(r, c);
			}
		}
	}

	public void adjacentMines() {
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				if(gameBoard[i][j] != -1) {
					int count = 0;
					for(int r = -1; r <= 1; r++) {
						for(int c = -1; c <= 1; c++ ) {
							if((r != 0 || c != 0) && isValid(i + r, j + c) && gameBoard[i + r][j + c] == -1) {
								count++;
							}
						}
					}
					if(count > 0) {
						gameBoard[i][j] = count;
					}
				}
			}
		}
	}
	
	public boolean isValid(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}
	
	public void flagged(int row, int column) {
		if(!spotRevealed[row][column]) {
			flagged[row][column] = !flagged[row][column];
		}
	}
	
	public int getSpot(int row, int column) {
		return gameBoard[row][column];
	}
	
	public int getRows() {
		return rows;
	}
	
	public int getColumns() {
		return columns;
	}
	
	public int getMines() {
		return mines;
	}
	
	public boolean isRevealed(int row, int column) {
		return spotRevealed[row][column];
	}
	
	public boolean isFlagged(int row, int column) {
		return flagged[row][column];
	}
	
	public boolean isGameOver() {
		return gameOver;
	}
	
	public boolean isGameWon() {
		return won;
	}
}