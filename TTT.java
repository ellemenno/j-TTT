import java.util.Random;
import java.util.Scanner;


public class TTT {
  private enum Player { X, O }

  private static final Random prng = new Random();
  private static final String[] asks = { "which spot?", "where to?", "row&col?", "enter coords:", "location?", "grid coords?" };
  private static final Player[] players = { Player.X, Player.O };
  private static Scanner input;
  private static Board board;
  private static Move move;
  private static Move cursor;
  private static int turns = 0;
  private static int turn = 0;

  private static void clearScreen() { System.out.print("\u001Bc"); }
  private static void fmt(String template, Object... args) { System.out.format(template, args); }
  private static void nextTurn() { turns++; turn = turns % players.length; }
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
  private static String randomPrompt() { return asks[prng.nextInt(asks.length)]; }
  private static void askMove(Scanner input, Board board, Move move) {
    fmt("%s ", randomPrompt());
    String coords = input.nextLine().toLowerCase();
    while (!board.isValidCoords(coords)) {
      fmt("not valid. %s ", randomPrompt());
      coords = input.nextLine().toLowerCase();
    }
    board.setCoords(coords, move);
  }
  private static boolean noMoreMoves(Board board) { return (turns >= board.getCols()*board.getRows()); }
  private static void announceWinner(Player p) { fmt("  %s wins!%n", p.name()); }
  private static void announceTie() { fmt("  tie game%n"); }

  public static void main(String[] args) {
    input = new Scanner(System.in);
    move = new Move();
    cursor = new Move();
    boolean playing = true;
    Player player;

    clearScreen();
    board = new Board(askSize(input));
    fmt("tic-tac-toe, %d in a row. let's go!%n", board.placesToWin());
    input.nextLine();

    clearScreen();
    board.print(System.out);

    while (playing) {
      player = players[turn];
      fmt("  %s to move. ", player.name());
      switch (player) {
        case O :
          askMove(input, board, move);
          board.place(move.col, move.row, player.name().charAt(0));
          break;
        case X :
          // TODO: npc
          askMove(input, board, move);
          board.place(move.col, move.row, player.name().charAt(0));
          break;
      }
      clearScreen();
      board.print(System.out);
      if (board.isWinningMove(move, cursor)) { playing = false; announceWinner(player); }
      else { nextTurn(); }
      if (noMoreMoves(board)) { playing = false; announceTie(); }
    }

    input.close();
    fmt("%n");
  }
}
