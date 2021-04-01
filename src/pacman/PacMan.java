package pacman;

/**
 * Each instance of this class represents the player-controlled Pac-Man character in a Pac-Man maze.
 * 
 * @invar | getSquare() != null
 * @invar | getNbLives => 0
 * 
 * @mutates
 */
public class PacMan {
	
	/**
	 * @invar | square != null
	 * @invar | nbLives >= 0
	 */
	private Square square;
	private int nbLives;
	
	/**
	 * @basic
	 */
	public Square getSquare() { return square; }
	
	/**
	 * @basic
	 */
	public int getNbLives() { return nbLives; }

	
	/**
	 * @throws IllegalArgumentException | nbLives <= 0
	 * @throws IllegalArgumentException | square == null
	 * 
	 * @post | getNbLives() == nbLives
	 * @post | getSquare() == square
	 */
	public PacMan(int nbLives, Square square) {
		if (nbLives <= 0) {
			throw new IllegalArgumentException("'nbLives' cannot be less or equal to 0.");
		}
		if (square == null) {
			throw new IllegalArgumentException("'square' cannot be null.");
		}
		
		this.nbLives = nbLives;
		this.square = square;
	}
	
	
	/**
	 * @throws IllegalArgumentException | square == null
	 * @throws IllegalArgumentException | this.square.getMazeMap() != square.getMazeMap()
	 * 
	 * @mutates | this
	 * @inspects | square
	 * 
	 * @post | this.square.getMazeMap() == square.getMazeMap()
	 * @post | square == getSquare()
	 */
	public void setSquare(Square square) {
		if (square == null) {
			throw new IllegalArgumentException("'square' cannot be null.");
		}
		if (this.square.getMazeMap() != square.getMazeMap()) {
			throw new IllegalArgumentException("'mazeMap' cannot change.");
		}
		
		this.square = square;
	}
	
	/**
	 * Decreases this Pac-Man character's number of lives by one.
	 * 
	 * @throws IllegalArgumentException | nbLives == 0
	 * 
	 * @mutates | nbLives
	 * 
	 * @post | nbLives = getNbLives()
	 */
	public void die() { 
		if (nbLives == 0) {
			throw new IllegalArgumentException("nbLives cannot be 0.");
		}
		
		while (nbLives > 0) {
			nbLives --;
		}
	}

}
