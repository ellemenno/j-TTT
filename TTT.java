
public class TTT {

  private static void clearScreen() { System.out.print("\u001Bc"); }
  private static void fmt(String template, Object... args) { System.out.format(template, args); }

  public static void main(String[] args) {
    clearScreen();
    fmt("tic-tac-toe, 3 in a row. let's go!%n");
    fmt("%n");
  }
}
