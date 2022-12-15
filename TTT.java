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

  public static void main(String[] args) {
    input = new Scanner(System.in);
    output = System.out;
    move = new Move();
    cursor = new Move();
    boolean playing = true;
    Player player;

    clearScreen();
    numPlayers = Prompt.askPlayers(input, output);
    board = new Board(Prompt.askSize(input, output));
    fmt("tic-tac-toe, %d in a row. hit enter, let's go!%n", board.placesToWin());
    input.nextLine();

    clearScreen();
    board.print(output);

    while (playing) {
      player = players[turn];
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
