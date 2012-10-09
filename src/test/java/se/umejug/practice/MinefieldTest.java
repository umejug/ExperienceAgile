package se.umejug.practice;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class MinefieldTest {
	
	@Test
	public void showMineHints() throws Exception {
		Minefield minefield = new Minefield(new String [] { "....",
											 				".*..",
															".*.*",
															"...*"});
		assertArrayEquals(new String [] {"1110",
												"2*31",
												"2*4*",
												"113*"}, minefield.getHints());
	}
	
	@Test
	public void showZeroForEmptyField() throws Exception {
		assertHintsEquals(new String[] { "00", "00" }, new String[] { "..", ".." });
	}
	
	@Test
	public void shouldShowMines() throws Exception {
		assertHintsEquals(new String[] { "***", "***" }, new String[] { "***", "***" });
	}
	
	@Test
	public void shouldShowHintsAroundMine() throws Exception {
		assertHintsEquals(new String[] { "111",  "1*1", "111" }, new String[] { "...",  ".*.", "..." });
	}
	
	@Test
	public void shouldCountMinesAroundCell() throws Exception {
		assertHintsEquals(new String[] { "***",  "*8*", "***" }, new String[] { "***",  "*.*", "***" });
	}
	
	private void assertHintsEquals(String[] expectedHint, String[] cells) {
		Minefield minefield = new Minefield(cells);
		assertArrayEquals(expectedHint, minefield.getHints());
	}

}
