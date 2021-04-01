package pacman;

/**
 * Each instance of this class represents a position in a maze, specified by a row index and a column index.
 * The top row and the leftmost column have index 0.
 * 
 * @invar | getMazeMap() != null
 * @invar | getRowIndex() <= mazeMap.getWidth()
 * @invar | getColumnIndex() <= mazeMap.getHeight()
 * 
 *@immutable
 */
public class Square {
	
	/**
	 * @invar | rowIndex >= 0 
	 * @invar | columnIndex >= 0
	 * @invar | mazeMap != null
	 */
	
	private int rowIndex;
	private int columnIndex;
	private MazeMap mazeMap;
	public static Square square;
	private Direction[] passableDirections;
	private Direction[] copyPassableDirections;
	private static Ghost ghost;
	
	/**
	 * @basic
	 */
	public MazeMap getMazeMap() { return this.mazeMap; }

	/**
	 * @basic
	 */
	public int getRowIndex() { return this.rowIndex; }
	

	/**
	 * @basic
	 */
	public int getColumnIndex() { return this.columnIndex; }
	
	/**
	 * @inspects | square.getMazeMap().isPassable(square.getRowIndex(), square.getColumnIndex())
	 */
	public boolean isPassable() { 
		return square.getMazeMap().isPassable(square.getRowIndex(), square.getColumnIndex()) == true ? true : false;
	}
	
	/**
	 * Returns a Square object already created and stored, or creates a new one.
	 * 
	 * @throws IllegalArgumentException | mazeMap == null
	 * @throws IllegalArgumentException | rowIndex > mazeMap.getWidth()
	 * @throws IllegalArgumentException | columnIndex > mazeMap.getHeight()
	 * 
	 * @post | mazeMap == getMazeMap()
	 * @post | rowIndex == getRowIndex()
	 * @post | columnIndex == getColumnIndex()
	 */
	public static Square of(MazeMap mazeMap, int rowIndex, int columnIndex) {
		if (mazeMap == null) {
			throw new IllegalArgumentException("mazeMap cannot be null.");
		}
		if (rowIndex > mazeMap.getWidth()) {
			throw new IllegalArgumentException("'rowIndex' cannot be larger than the width of the maze.");
		}
		if (columnIndex > mazeMap.getHeight()) {
			throw new IllegalArgumentException("'columnIndex' cannot be larger than the height of the maze.");
		}
		
		if (square == null) {
			square = new Square(mazeMap, rowIndex, columnIndex);
			return square;
		} else {
			return square;
		}
	}
	
	/**
	 * Returns this square's neighbor in the given direction.
	 * If this square has no neighbor in the given direction, return the square that is furthest away in the opposite direction.
	 */
	// No formal documentation required
	public Square getNeighbor(Direction direction) {
		int row = square.getRowIndex();
		int col = square.getColumnIndex();
		Square neighborSquare = null;
		
		if(direction == Direction.RIGHT) {
			 if (col <= square.getMazeMap().getWidth() && col != 0) {
				 neighborSquare = new Square(mazeMap, row, col - 1);
			 } else if (col == 0) {
				 neighborSquare = new Square(mazeMap, row,  square.getMazeMap().getWidth());
			 }
		} else if(direction == Direction.LEFT) {
			if (col < square.getMazeMap().getWidth()) {
				 neighborSquare = new Square(mazeMap, row, col + 1);
			 } else if (col == square.getMazeMap().getWidth()) {
				 neighborSquare = new Square(mazeMap, row,  Math.floorMod(square.getMazeMap().getWidth(), square.getMazeMap().getWidth()));
			 }
		} else if(direction == Direction.DOWN) {
			if (row < square.getMazeMap().getHeight()) {
				 neighborSquare = new Square(mazeMap, row + 1, col);
			 } else if (row == square.getMazeMap().getHeight()) {
				 neighborSquare = new Square(mazeMap, Math.floorMod(square.getMazeMap().getHeight(), square.getMazeMap().getHeight()),  col);
			 }
		} else if(direction == Direction.UP) {
			if (row <= square.getMazeMap().getHeight() && row != 0) {
				 neighborSquare = new Square(mazeMap, row - 1, col);
			 } else if (col == 0) {
				 neighborSquare = new Square(mazeMap, square.getMazeMap().getHeight(),  col);
			 }
		}
		return neighborSquare;
	}

	/**
	 * Returns whether this square's neighbor in the given direction is passable.
	 */
	// No formal documentation required
	public boolean canMove(Direction direction) {
		return square.getNeighbor(direction).isPassable();
	}

	/**
	 * Returns the directions that are different from the given excluded direction and such that the neighbor in that direction is passable.
	 * The returned array shall have no null elements and shall have no duplicates.
	 */
	// No formal documentation required
	public Direction[] getPassableDirectionsExcept(Direction excludedDirection) {
		if(excludedDirection != ghost.getDirection().getOpposite())
			throw new IllegalArgumentException("'excludeDirection' has to be the opposite to the current direction");
		
		passableDirections = null;
		
		int i = 0;
		if (square.canMove(Direction.DOWN) == true) {
			passableDirections[i] = Direction.DOWN;
			i++;
		} else if (square.canMove(Direction.UP) == true) {
			passableDirections[i] = Direction.UP;
			i++;
		} else if (square.canMove(Direction.LEFT) == true) {
			passableDirections[i] = Direction.LEFT;
			i++;
		} else if (square.canMove(Direction.RIGHT) == true) {
			passableDirections[i] = Direction.RIGHT;
			i++;
		}
		
		copyPassableDirections = new Direction[passableDirections.length - 1];
		for (int j = 0, k = 0; j <= copyPassableDirections.length; j++) {
			while(passableDirections[j] != excludedDirection) {
				copyPassableDirections[k] = passableDirections[j];
				k++;
			}
		}
		
		return copyPassableDirections;
	}
	
	/**
	 * Returns whether the given square refers to the same {@code MazeMap} object and has the same row and column index as this square.  *
	 *
	 *@inspects | other
	 */
	public boolean equals(Square other) {
		return square.getMazeMap() == other.getMazeMap() && square.getRowIndex() == other.getRowIndex() && square.getColumnIndex() == other.getColumnIndex() ? true : false;
	}
	
	/**
	 * Creates a private instantiation of the class.
	 */
	private Square(MazeMap mazeMap, int rowIndex, int columnIndex) {
		this.mazeMap = mazeMap;
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
	}
	
}
