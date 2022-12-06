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

  public boolean isWinningMove(Move move, Move cursor) {
    char c = charAt(move.col, move.row);
    int w = placesToWin();
    int n;

    n = 1;
    cursor.setTo(move);
    while(peekWest(cursor) && (charAt(cursor.col, cursor.row)) == c) { n++; }
    if (n >= w) { return true; }
    cursor.setTo(move);
    while(peekEast(cursor) && (charAt(cursor.col, cursor.row)) == c) { n++; }
    if (n >= w) { return true; }

    n = 1;
    cursor.setTo(move);
    while(peekNorthWest(cursor) && (charAt(cursor.col, cursor.row)) == c) { n++; }
    if (n >= w) { return true; }
    cursor.setTo(move);
    while(peekSouthEast(cursor) && (charAt(cursor.col, cursor.row)) == c) { n++; }
    if (n >= w) { return true; }

    n = 1;
    cursor.setTo(move);
    while(peekNorth(cursor) && (charAt(cursor.col, cursor.row)) == c) { n++; }
    if (n >= w) { return true; }
    cursor.setTo(move);
    while(peekSouth(cursor) && (charAt(cursor.col, cursor.row)) == c) { n++; }
    if (n >= w) { return true; }

    n = 1;
    cursor.setTo(move);
    while(peekNorthEast(cursor) && (charAt(cursor.col, cursor.row)) == c) { n++; }
    if (n >= w) { return true; }
    cursor.setTo(move);
    while(peekSouthWest(cursor) && (charAt(cursor.col, cursor.row)) == c) { n++; }
    if (n >= w) { return true; }

    return false;
  }

  public boolean peek(Move cursor, int dc, int dr) {
    if (cursor.col + dc < 0 || cursor.col + dc >= cols) { return false; }
    if (cursor.row + dr < 0 || cursor.row + dr >= rows) { return false; }
    cursor.col += dc;
    cursor.row += dr;
    return true;
  }

  public boolean peekWest(Move cursor) { return peek(cursor, -1, 0); }
  public boolean peekNorthWest(Move cursor) { return peek(cursor, -1, -1); }
  public boolean peekNorth(Move cursor) { return peek(cursor, 0, -1); }
  public boolean peekNorthEast(Move cursor) { return peek(cursor, +1, -1); }
  public boolean peekEast(Move cursor) { return peek(cursor, +1, 0); }
  public boolean peekSouthEast(Move cursor) { return peek(cursor, +1, +1); }
  public boolean peekSouth(Move cursor) { return peek(cursor, 0, +1); }
  public boolean peekSouthWest(Move cursor) { return peek(cursor, -1, +1); }

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
