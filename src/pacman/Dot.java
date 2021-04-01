package pacman;

/**
 * Each instance of this class represents a dot (a piece of food for Pac-Man) in a Pac-Man maze.
 * 
 * @immutable
 */
public class Dot {
	
	private Square square;
	
	/**
	 * @basic
	 */
	public Square getSquare() { return square; }
	
	/**
	 * Instantiation of a dot in the maze in a certain square. 
	 * 
	 * @throws IllegalArgumentException | square != null
	 * 
	 * @post | getSquare() = square
	 */
	public Dot(Square square) {
		if (square != null) {
			throw new IllegalArgumentException("'square' cannot be null.");
		}
		
		this.square = square;
	}
}
