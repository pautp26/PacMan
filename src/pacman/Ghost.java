package pacman;

import java.util.Random;

/**
 * Each instance of this class represents a ghost in a Pac-Man maze.
 * 
 * @invar | getSquare() != null
 * @invar | getDirection() != null
 * 
 * @mutates
 */
public class Ghost {
	
	/**
	 * @invar | square != null
	 * @invar | direction != null
	 */
	private Square square;
	private Direction direction;
	
	/**
	 * Returns the square of the ghost in the maze.
	 * 
	 * @basic
	 *
	 */
	public Square getSquare() { return square; }
	
	/**
	 * Returns the direction in which this ghost will preferably move next.
	 * 
	 * @basic
	 */
	public Direction getDirection() { return direction; }
	
	/**
	 * Initiates a ghost object in a particular square with a particular direction.
	 * 
	 */
	public Ghost(Square square, Direction direction) { 
		this.square = square;
		this.direction = direction;
	}
	
	/**
	 * 
	 * @throws IllegalArgumentException | square == null
	 * @throws IllegalArgumentException | this.square.getMazeMap() != square.getMazeMap()
	 * 
	 * @mutates | this
	 * @inspects | square
	 * 
	 * @post | square.getMazeMap() == old(square.getMazeMap())
	 */
	public void setSquare(Square square) { 
		if (square == null) {
			throw new IllegalArgumentException("'square' cannot be null.");
		}
		if (this.square.getMazeMap() != square.getMazeMap()) {
			throw new IllegalArgumentException("'mazeMap has to be the same");
		}
		
		this.square = square;
	}
	
	/**
	 *@throws IllegalArgumentException | direction == null
	 * 
	 *@mutates | this
	 *@inspects | direction
	 * 
	 *@post | direction.getOpposite() != old(direction.getOpposite()
	 */
	public void setDirection(Direction direction) {
		if (direction == null){
			throw new IllegalArgumentException("'direction' cannot be null");
		}
		
		this.direction = direction;
	}
	
	private static int MOVE_FORWARD_PREFERENCE = 10;
	
	// No formal document required
	public Direction chooseNextMoveDirection(Random random) {
		int moveForwardPreference = getSquare().canMove(getDirection()) ? MOVE_FORWARD_PREFERENCE : 0;
		Direction[] passableDirections = getSquare().getPassableDirectionsExcept(getDirection().getOpposite());
		if (passableDirections.length == 0)
			return getDirection().getOpposite();
		int result = random.nextInt(moveForwardPreference + passableDirections.length);
		if (result < moveForwardPreference)
			return getDirection();
		return passableDirections[result - moveForwardPreference];
	}
	
	// No formal document required
	public void move(Random random) {
		setDirection(chooseNextMoveDirection(random));
		setSquare(getSquare().getNeighbor(getDirection()));
	}
}
