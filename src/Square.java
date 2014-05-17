import java.util.ArrayList;
import java.util.List;


public class Square {
	
	private Integer[][] myCells;
	private int myDimension;
	
	public Square(Integer[][] cells) {
		myCells = cells;
		myDimension = myCells.length;
	}
	
	public Integer getCell(int x, int y) {
		return myCells[x][y];
	}
	
	public void setCell(Integer num, int x, int y) {
		myCells[x][y] = num;
	}
	
	public void printSquare() {
		for (int i = 0; i < myCells.length; i++) {
			for (int j = 0; j < myCells.length; j++) {
				System.out.print(myCells[j][i]+ " ");
			}
			System.out.println();
		}
	}

	public boolean isValid(int val, int x, int y) {
		for (int i = 0; i < myDimension; i++) {
			for (int j = 0; j < myDimension; j++) {
				if (getCell(j,i) == val) return false; // check here
			}
		}
		return true;
	}

	public List<Integer> getRow(int yLoc) {
		List<Integer> myList = new ArrayList<Integer>();
		for (int i = 0; i < myDimension; i++) {
			myList.add(myCells[i][yLoc]);
		}
		return myList;
	}

	public List<Integer> getColumn(int xLoc) {
		List<Integer> myList = new ArrayList<Integer>();
		for (int i = 0; i < myDimension; i++) {
			myList.add(myCells[xLoc][i]);
		}
		return myList;
	}
	

	public Square copySquare() {
		Integer[][] cellCopy = new Integer[myDimension][myDimension];
		for (int i = 0; i < myDimension; i++) {
			for (int j = 0; j < myDimension; j++) {
				cellCopy[j][i]	= myCells[j][i];
			}
		}
		return new Square(cellCopy);
	}
	
}
