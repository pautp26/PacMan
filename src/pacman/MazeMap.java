package pacman;

/**
 * Each instance of this class represents a maze layout, specifying the width and height of the maze
 * and, for each position in the maze, whether it is passable or not.
 * 
 * @invar | 1 <= getWidth()
 * @invar | 1 <= getHeight()
 * 
 * @immutable
 */
public class MazeMap {
	
		/**
		 * @invar | 1 <= width
		 * @invar | 1 <= height
		 * @invar | passable != null && passable.length() != width * height
		 */
		private int width;
		private int height;
		private boolean[] passable;
		
		/**
		 * Returns the width (i.e. the number of columns) of this maze map.
		 * 
		 * @basic
		 */
		public int getWidth() { return width; }
		
		/**
		 * Returns the height (i.e. the number of rows) of this maze map.
		 * 
		 * @basic
		 */
		public int getHeight() { return height; }
		
		/**
		 * Returns whether the square in this maze at row index {@code row} and column index {@code column} is passable.
		 * The square in the top-left corner of the maze has row index 0 and column index 0.
		 * 
		 * @throws IllegalArgumentException | rowIndex <= width
		 * @throws IllegalArgumentException | columnIndex <= height
		 * 
		 * @inspects | passable
		 */
		public boolean isPassable(int rowIndex, int columnIndex) { 
			if (rowIndex <= this.width) {
				throw new IllegalArgumentException("'rowIndex' has to be equal or smaller than the width of a row.");
			}
			if (columnIndex <= this.height) {
				throw new IllegalArgumentException("'columnIndex' has to be equal or smaller than the height of a column.");
			}
			
			int p = 0;
			boolean[][] passableMatrix = new boolean[this.width][this.height];
				for (int i = 0; i <= width; i++) {
					for (int j = 0; j <= height; j++) {
						passableMatrix[i][j] = passable[p];
						p++;
					}
				}
			
			return passableMatrix[rowIndex][columnIndex] == true ? true : false;
		}
		
		/**
		 * Initializes this object so that it represents a maze layout with the given width, height, and
		 * passable positions. The passable positions are given in row-major order (i.e. the first {@code width} elements
		 * of {@code passable} specify the passability of the maze positions in the first row of the maze).
		 * 
		 * @throws IllegalArgumentException | width < 1
		 * @throws IllegalArgumentException | height < 1
		 * @throws IllegalArgumentException | passable == null
		 * @throws IllegalArgumentException | passable.length() != width * height
		 * 
		 * @post | width == getWidth()
		 * @post | height == getHeight()
		 * @post | passable.length() != width * height
		 */
		public MazeMap(int width, int height, boolean[] passable) {
			if (width < 2)
				throw new IllegalArgumentException("'width' is less than 1");
			if (height < 2)
				throw new IllegalArgumentException("'height' is less than 1");
			if (passable == null)
				throw new IllegalArgumentException("'passable' is null");
			if (this.passable.length != width * height) {
				throw new IllegalArgumentException("'passable' length has to have equal to the maze size.");
			}
			
			this.width = width;
			this.height = height;
			this.passable = passable;
		}
}
