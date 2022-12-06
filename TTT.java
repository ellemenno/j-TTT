import java.util.Scanner;


public class TTT {
  private static Scanner input;
  private static Board board;

  private static void clearScreen() { System.out.print("\u001Bc"); }
  private static void fmt(String template, Object... args) { System.out.format(template, args); }
  private static int askSize(Scanner input) {
    boolean asking = true;
    int n = 0;
    while (asking) {
      fmt("what size board would you like (10-3)? ");
      n = Integer.parseInt(input.nextLine());
      if (n > 10 || n < 3) { fmt("pick a size from 10 to 3.%n"); }
      else { asking = false; }
    }
    return n;
  }

  public static void main(String[] args) {
    input = new Scanner(System.in);

    clearScreen();
    board = new Board(askSize(input));
    fmt("tic-tac-toe, %d in a row. let's go!%n", board.placesToWin());
    input.nextLine();

    clearScreen();
    board.print(System.out);

    input.close();
    fmt("%n");
  }
}
