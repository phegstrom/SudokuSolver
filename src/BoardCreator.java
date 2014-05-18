import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class BoardCreator {

	public BoardCreator() {
	}
	
	/**
	 * The method that creates the SudokuBoard to be solved.
	 * @param filename file name that contains the  board
	 * @return the board
	 */
	public SudokuBoard createBoard(String filename) throws FileNotFoundException {
		String[] stringBoard = processFile(filename);
	
		int squareDimension = (int) Math.sqrt(stringBoard.length);

		Square[][] mySquares = initializeSquares(squareDimension);
		Square currentSquare = null;
		
		int colCount = 0;
		int rowCount = 0;
		
		for (int i = 0; i < stringBoard.length; i++) {
			String row = stringBoard[i];
			String[] array = row.split("\\s+");
			if (i % squareDimension == 0 && i != 0) {
				rowCount++;
			}
			for (int j = 0; j < stringBoard.length; j++) {
				if (j % squareDimension == 0) {
					currentSquare = mySquares[colCount][rowCount];
					colCount++;
				}
				int value;
				
				if (!array[j].equals("-")) {
					value = Integer.parseInt(array[j]);
				} else {
					value = 45;
				}
				
				currentSquare.setCell(value, j % squareDimension, i % squareDimension);
			}
			colCount = 0;
		}
		
		System.out.println();
		SudokuBoard toReturn = new SudokuBoard(mySquares);
		
		return toReturn;
	}
	
	
	private String[] processFile(String filename) throws FileNotFoundException {
		String[] toReturn;
		int row = 0;
		BufferedReader myReader = new BufferedReader(new FileReader(filename));
		String line = "";
		
		try {
			line = myReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] firstLine = line.split("\\s+");
		toReturn = new String[firstLine.length];
		
		while(line != null) {
			toReturn[row] = line;
			row++;
			try {
				line = myReader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return toReturn;
	}

	private Square[][] initializeSquares(int length) {
		Square[][] toRet = new Square[length][length];
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				toRet[i][j] = initializeCells(length);
			}
		}
		
		return toRet;
	}

	private Square initializeCells(int length) {
		Integer[][] myCells = new Integer[length][length];
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				myCells[i][j] = (int) '-';
			}
		}
		
		Square toRet = new Square(myCells);
		return toRet;
	}
}
