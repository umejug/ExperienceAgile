package se.umejug.practice;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class MinefieldTest {
	
	@Test
	public void showHints() throws Exception {
		assertHintsForMinefield(new String[] {"....", ".*..", ".*.*", "...*"},
				                new String [] {"1110", "2*31", "2*4*", "113*"});
	}
	
	@Test
	public void showEmptyMinefield() throws Exception {
		assertHintsForMinefield(new String[] { "...", "..." }, new String[] { "000", "000" });
	}
	
	@Test
	public void showDimensions() throws Exception {
		assertHintsForMinefield(new String[] { "....." }, new String[] { "00000" });
	}

	@Test
	public void showMines() throws Exception {
		assertHintsForMinefield(new String[] { "****" }, new String[] { "****" });
	}
	
	@Test
	public void showHintsAroundMine() throws Exception {
		assertHintsForMinefield(new String [] {"...", ".*.", "..."}, new String [] {"111", "1*1", "111"});
	}

	@Test
	public void countMines() throws Exception {
		assertHintsForMinefield(new String [] {"***", "*.*", "***"}, new String [] {"***", "*8*", "***"});
	}
	
	private void assertHintsForMinefield(String[] field, String[] expectedHints) {
		Minefield minefield = new Minefield(field);
		assertArrayEquals(expectedHints, minefield.getHints());
	}
}
