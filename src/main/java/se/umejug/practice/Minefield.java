package se.umejug.practice;

public class Minefield {

	private String[] cells;

	public Minefield(String[] cells) {
		this.cells = cells;
	}

	public String[] getHints() {
		String[] hints = new String[cells.length];
		for (int row = 0; row < cells.length; row++) {
			hints[row] = "";
			for(int col=0; col < cells[row].length(); col++) {
				hints[row] += calculateHint(row, col);
			}
		}
		return hints;
	}

	private String calculateHint(int row, int col) {
		if (cellHasMine(row, col)) return "*";
		int numberOfMines = 0;
		for (int r=row-1;r<=row+1; r++) {
			for (int c=col-1;c<=col+1; c++) {
				if (cellHasMine(r, c)) 
					numberOfMines++;
			}
		}
		return ""+numberOfMines;
	}

	private boolean cellHasMine(int row, int col) {
		if (row < 0 || cells.length <= row) return false;
		if (col < 0 || cells[row].length() <= col) return false;
		return cells[row].charAt(col) == '*';
	}

}
