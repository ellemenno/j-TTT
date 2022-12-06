import java.io.PrintStream;


class Board {
  public static final int MIN = 3;
  public static final int MAX = 10;

  private static final String labelC = "0123456789";
  private static final String labelR = "abcdefghij";
  private static final char empty = '.';
  private final StringBuilder sb = new StringBuilder();
  private int cols = 0;
  private int rows = 0;
  private int toWin = 0;

  private void setSize(int size) {
    cols = size;
    rows = size;
    toWin = (size > MIN) ? MIN+1 : MIN;
    if (sb.length() > 0) { sb.delete(0, sb.length()); }
    for (int i = 0; i < size*size; i++) { sb.append(empty); }
  }

  @Override
  public String toString() { return sb.toString(); }

  public void print(PrintStream out) {
    int c, r;
    out.format("%n");
    out.format("  %c ", ' ');
    for (c = 0; c < cols; c++) { out.format("%c ", labelC.charAt(c)); }
    out.format("%n");
    for (r = 0; r < rows; r++) {
      out.format("  %c ", labelR.charAt(r));
      for (c = 0; c < cols; c++) { out.format("%c ", sb.charAt(r*cols+c)); }
      out.format("%n");
    }
    out.format("%n");
  }

  public int getCols() { return cols; }
  public int getRows() { return rows; }

  public int placesToWin() { return toWin; }

  public char charAt(int col, int row) { return (sb.charAt(row*cols+col)); }
  public boolean isEmpty(int col, int row) { return (charAt(col, row) == empty); }

  public void place(int col, int row, char p) { sb.setCharAt(row*cols+col, p); }

  public boolean isValidCoords(String coords) {
    if (coords.length() != 2) { return false; }
    char a = coords.charAt(0);
    char b = coords.charAt(1);
    if (!Character.isLetter(a) && !Character.isDigit(a)) { return false; }
    if (Character.isLetter(a) && !Character.isDigit(b)) { return false; }
    if (Character.isDigit(a) && !Character.isLetter(b)) { return false; }
    int c = Character.isDigit(a) ? Character.digit(a, 10) : Character.digit(b, 10);
    if (c < 0 || c >= cols) { return false; }
    int r = Character.isLetter(a) ? labelR.indexOf(a) : labelR.indexOf(b);
    if (r < 0 || r >= rows) { return false; }
    if (!isEmpty(c, r)) { return false; }
    return true;
  }

  public void setCoords(String coords, Move move) {
    if (Character.isLetter(coords.charAt(0))) {
      move.col = Character.digit(coords.charAt(1), 10);
      move.row = labelR.indexOf(coords.charAt(0));
    }
    else {
      move.col = Character.digit(coords.charAt(0), 10);
      move.row = labelR.indexOf(coords.charAt(1));
    }
  }

  public Board() { setSize(3); }
  public Board(int size) { setSize(size); }
}
