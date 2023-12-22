import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;


public class Prompt {
  private static final Random prng = new Random();
  private static final String[] asks = { "which spot?", "where to?", "row&col?", "enter coords:", "location?", "grid coords?" };

  private static String randomPrompt() { return asks[prng.nextInt(asks.length)]; }

  public static int askPlayers(Scanner input, PrintStream out) {
    boolean asking = true;
    int n = 0, min = 2, max = 4;
    while (asking) {
      out.format("how many players (%d-%d)? ", min, max);
      n = Integer.parseInt(input.nextLine());
      if (n > max || n < min) { out.format("choose from %d up to %d.%n", min, max); }
      else { asking = false; }
    }
    return n;
  }
  public static int askSize(Scanner input, PrintStream out, int numPlayers) {
    boolean asking = true;
    int n = 0, min = Math.max(3, numPlayers+1), max = 10;
    while (asking) {
      out.format("what size board (%d-%d)? ", min, max);
      n = Integer.parseInt(input.nextLine());
      if (n > max || n < min) { out.format("pick a size from %d to %d.%n", min, max); }
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