package se.umejug.practice;

public class Minefield {

	private String[] cells;

	public Minefield(String[] cells) {
		this.cells = cells;
	}

	public String[] getHints() {
		String[] result = new String [cells.length];
		for (int row = 0; row < result.length; row++) {
			result[row] = "";
			for(int col = 0; col < cells[row].length(); col++)
				result[row] += getHint(row, col);
		}
		return result;
	}

	private String getHint(int row, int col) {
		if (hasMine(row, col)) return "*";
		int mineCount = 0;
		for (int r=row-1; r<=row+1; r++) {
			for (int c=col-1; c<=col+1; c++) {
				if (hasMine(r, c)) mineCount++;
			}
		}
		return "" + mineCount;
	}

	private boolean hasMine(int row, int col) {
		if (col < 0 || col >= cells[0].length()) return false;
		if (row < 0 || row >= cells.length) return false;
		return cells[row].charAt(col) == '*';
	}

}
