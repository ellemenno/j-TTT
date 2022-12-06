import java.util.Scanner;


public class TTT {
  private static Scanner input;
  private static Board board;

  private static void clearScreen() { System.out.print("\u001Bc"); }
  private static void fmt(String template, Object... args) { System.out.format(template, args); }

  public static void main(String[] args) {
    input = new Scanner(System.in);

    clearScreen();
    board = new Board();
    fmt("tic-tac-toe, 3 in a row. let's go!%n");
    input.nextLine();

    clearScreen();
    board.print(System.out);

    input.close();
    fmt("%n");
  }
}
