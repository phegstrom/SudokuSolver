import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

/**
 * A class that will solve any nxn sudoku puzzle. The solver takes in a text file that represents the 
 * board to be solved where each value is separated by a space, and the unknown values are represented by
 * "-."
 * @author Parker Hegstrom
 *
 */
public class SudokoSolver {

	public static void main(String[] args) throws FileNotFoundException {	

		BoardCreator creator = new BoardCreator();
		SudokuBoard myBoard = creator.createBoard("exampleBoard.txt");
		myBoard.printBoard();
		System.out.println();	

		SudokuBoard answer = solveBoard(myBoard);

		System.out.println("\n\n ANSWER \n\n");
		
		if (answer != null) answer.printBoard();
		else System.out.println("No solution");

	}

	/**
	 * The method that will solve the puzzle. Each recursive iteration, the board is copied and a move
	 * is made. If the recursive solver fails, that board is thrown away and a new solution will be sought.
	 * @param board 
	 * @return sudoku board
	 */
	private static SudokuBoard solveBoard(SudokuBoard board) {
		
		List<Point> myPositions = board.getValidPositions();

		if (myPositions.isEmpty()) {
			return board;
		}

		Point p = myPositions.get(0);
		List<Integer> myNums = board.getValidNumbers(p.x, p.y);
		
		if (myNums.isEmpty()) {
			return null;
		}
		
		for (Integer number : myNums) {
			if (number == 7) {
				int x = 5;
			}
			SudokuBoard newBoard = board.clone();
			newBoard.makeMove(number, p.x, p.y);
			SudokuBoard ss = solveBoard(newBoard);
			if (ss != null) {
				return ss;
			}
		}

		return null;
	}
}
