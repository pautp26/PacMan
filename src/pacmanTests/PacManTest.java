package pacmanTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pacman.MazeMap;

class PacManTest {

	@Test
	void test() {
		MazeMap mazeMap = new MazeMap(3, 5, new boolean[] {false, false, false,
			true, true, true,
			true, false, true,
			true, true , true,
			false, false,false});
		
		assertEquals(3, mazeMap.getWidth());
		assertEquals(5, mazeMap.getHeight());
		assertEquals(false, mazeMap.isPassable(0, 0));
	}
}
