import java.io.PrintStream;
import java.util.Scanner;


public class TTT {
  private static final Player[] players = Player.roster();
  private static Scanner input;
  private static PrintStream output;
  private static Board board;
  private static Move move;
  private static Move cursor;
  private static int numPlayers = 2;
  private static int turns = 0;
  private static int turn = 0;

  private static void clearScreen() { System.out.print("\u001Bc"); }
  private static void fmt(String template, Object... args) { System.out.format(template, args); }
  private static void nextTurn() { turns++; turn = turns % numPlayers; }
  private static boolean noMoreMoves(Board board) { return (turns >= board.getCols()*board.getRows()); }
  private static void announceWinner(Player p) { fmt("  %s wins!%n", p.name()); }
  private static void announceTie() { fmt("  tie game%n"); }

  // improve:
  // find tie game earlier
  // add menu
  // back to menu on game completion
  // quit game with 'q'
  // restart game with 'r'
  // introduce npc

  // menu config:
  // num players (2..4)
  // player types (human|bot)
  // bot difficulty (1..3)
  // board size (3..10)
  // win size (board size|4)
  // num pieces (unlimited|board size)
  // if num pieces >= 4, allow squares?

  public static void main(String[] args) {
    input = new Scanner(System.in);
    output = System.out;
    move = new Move();
    cursor = new Move();
    boolean playing = true;
    Player player;

    clearScreen();
    numPlayers = Prompt.askPlayers(input, output);
    board = new Board(Prompt.askSize(input, output, numPlayers));
    fmt("tic-tac-toe, %d in a row. now hit enter, let's go!%n", board.placesToWin());
    input.nextLine();

    clearScreen();
    board.print(output);

    while (playing) {
      player = players[turn];
      fmt("  %d empty squares.%n", board.emptySquares());
      fmt("  %s to move. ", player.name());
      switch (player) {
        case X :
          Prompt.askMove(input, output, board, move);
          board.place(move.col, move.row, player);
          break;
        case O :
        case V :
        case S :
          // TODO: npc
          Prompt.askMove(input, output, board, move);
          board.place(move.col, move.row, player);
          break;
      }
      clearScreen();
      board.print(output);
      if (board.isWinningMove(move, cursor)) { playing = false; announceWinner(player); }
      else { nextTurn(); }
      if (noMoreMoves(board)) { playing = false; announceTie(); }
    }

    input.close();
    fmt("%n");
  }
}
