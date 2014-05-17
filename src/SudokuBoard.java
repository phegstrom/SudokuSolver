import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public class SudokuBoard {
	
	private Square[][] mySquares;
	private int myDimension;
	private int mySquareDimension;
	
	public SudokuBoard(Square[][] s) {
		mySquares = s;
		myDimension = (int) Math.pow(s.length, 2);
		mySquareDimension = s.length;
	}
	
	public void setSquare(Square s, int x, int y) {
		mySquares[x][y] = s;
	}
	
	public Square getSquare(int x, int y) {
		return mySquares[x][y];
	}
	
	public Integer getCell(int x, int y) {
		int xLoc = getCellLoc(x);
		int yLoc = getCellLoc(y);
		int xCell = getSquareLoc(x);
		int yCell = getSquareLoc(y);
		return mySquares[xCell][yCell].getCell(xLoc, yLoc);
	}

	
	public List<Point> getValidPositions() {
		List<Point> myList = new ArrayList<Point>();
		for (int i = 0; i < myDimension; i++) {
			for (int j = 0; j < myDimension; j++) {
				Integer currentCell = getCell(j,i);
				int value = currentCell;
				if (currentCell == '-') {
					myList.add(new Point(j,i));
				}
			}
		}
		return myList;
	}
	
	public List<Integer> getValidNumbers(int x, int y) {
		List<Integer> myList = new ArrayList<Integer>();
		for (int i = 1; i < myDimension+1; i++) {
			if (validSquare(i,x,y) && validRow(i,y) && validColumn(i,x)) {
				myList.add(i);
			}
		}
		return myList;
	}
	
	/**
	 * Properly clones the board
	 * @return new board
	 */
	public SudokuBoard clone() {
		Square[][] cloned = new Square[mySquareDimension][mySquareDimension];
		for (int i = 0; i < mySquareDimension; i++) {
			for (int j = 0; j < mySquareDimension; j++) {
				cloned[j][i] = mySquares[j][i].copySquare();
			}
		}
		
		return new SudokuBoard(cloned);
	}
	
	
	public void makeMove(int value, int x, int y) {
		int xLoc = getCellLoc(x);
		int yLoc = getCellLoc(y);
		int xSquareLoc = getSquareLoc(x);
		int ySquareLoc = getSquareLoc(y);
		
		mySquares[xSquareLoc][ySquareLoc].setCell(value, xLoc, yLoc);
	}
	
	private int getCellLoc(int loc) {
		return loc % mySquareDimension;
	}
	
	private boolean validColumn(int val, int x) {
		List<Integer> myCol = getColumnValues(x);
		if (myCol.contains(val)) return false;
		return true;
	}

	private List<Integer> getColumnValues(int xLoc) {
		List<Integer> myList = new ArrayList<Integer>();
		int squareLoc = getSquareLoc(xLoc);
		for (int i = 0; i < mySquareDimension; i++) {
			Square currentSquare = mySquares[squareLoc][i];
			List<Integer> squareRow = currentSquare.getColumn(getCellLoc(xLoc));
			appendToList(myList, squareRow);
		}
		return myList;
	}

	private boolean validRow(int val, int y) {
		List<Integer> myRow = getRowValues(y);
		if (myRow.contains(val)) return false;
		return true;
	}

	private List<Integer> getRowValues(int yLoc) {
		List<Integer> myList = new ArrayList<Integer>();
		int squareLoc = getSquareLoc(yLoc);
		for (int i = 0; i < mySquareDimension; i++) {
			Square currentSquare = mySquares[i][squareLoc];
			List<Integer> squareRow = currentSquare.getRow(getCellLoc(yLoc));
			appendToList(myList, squareRow);
		}
		return myList;
	}

	private void appendToList(List<Integer> myList, List<Integer> squareRow) {
		for (int i = 0; i < squareRow.size(); i++) {
			myList.add(squareRow.get(i));
		}
	}

	private boolean validSquare(int val, int x, int y) {
		return mySquares[getSquareLoc(x)][getSquareLoc(y)].isValid(val, getCellLoc(x),getCellLoc(y));
	}

	private int getSquareLoc(int val) {
		int count = 0;
		int squareLoc = 0;
		while(count <= val) {
			if (count % mySquareDimension == 0 && count != 0) {
				squareLoc++;
			}
			count++;
		}
		return squareLoc;
	}

	private void initializeBoard() {
		for(int i = 0; i < myDimension; i++) {
			for(int j = 0; j < myDimension; j++) {
				Integer[][] cells = new Integer[myDimension][myDimension];
				mySquares[i][j] = new Square(cells);
			}
		}
	}
	
	
	public void printBoard() {
		int colCount = 0;
		int rowCount = 0;
		Square currentSquare = null;
		
		for(int i = 0; i < myDimension; i++) {
			if (i % 3 == 0 && i != 0) {
				rowCount++;
			}
			for (int j = 0; j < myDimension; j++) {
				if (j % 3 == 0) {
					currentSquare = mySquares[colCount][rowCount];
					colCount++;
				}
				int currentCell = currentSquare.getCell(j % mySquareDimension,i % mySquareDimension);
				if (currentCell != '-') {
					System.out.print(currentCell + " ");
				} else {
					System.out.print("- ");
				}
			}
			System.out.println();
			colCount = 0;
		}
	}
}
