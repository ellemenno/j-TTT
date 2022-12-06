import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;


public class Prompt {
  private static final Random prng = new Random();
  private static final String[] asks = { "which spot?", "where to?", "row&col?", "enter coords:", "location?", "grid coords?" };

  private static String randomPrompt() { return asks[prng.nextInt(asks.length)]; }

  public static int askPlayers(Scanner input, PrintStream out) {
    boolean asking = true;
    int n = 0;
    while (asking) {
      out.format("how many players (2-4)? ");
      n = Integer.parseInt(input.nextLine());
      if (n > 4 || n < 2) { out.format("choose 2, 3, or 4.%n"); }
      else { asking = false; }
    }
    return n;
  }
  public static int askSize(Scanner input, PrintStream out) {
    boolean asking = true;
    int n = 0;
    while (asking) {
      out.format("what size board (10-3)? ");
      n = Integer.parseInt(input.nextLine());
      if (n > 10 || n < 3) { out.format("pick a size from 10 to 3.%n"); }
      else { asking = false; }
    }
    return n;
  }
  public static void askMove(Scanner input, PrintStream out, Board board, Move move) {
    out.format("%s ", randomPrompt());
    String coords = input.nextLine().toLowerCase();
    while (!board.isValidCoords(coords)) {
      out.format("not valid. %s ", randomPrompt());
      coords = input.nextLine().toLowerCase();
    }
    board.setCoords(coords, move);
  }

}