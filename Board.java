import java.io.PrintStream;


class Board {
  private static final String labelC = "012";
  private static final String labelR = "abc";
  private static final char empty = '.';
  private final StringBuilder sb = new StringBuilder();
  private int cols = 0;
  private int rows = 0;

  private void setSize(int size) {
    cols = size;
    rows = size;
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


  public Board() { setSize(3); }
}
