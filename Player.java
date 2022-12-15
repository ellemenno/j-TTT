
public enum Player {
  // 16 color palette
  //  h: [0..7], +60 for high intensity (0=black, 1=red, 2=green, 3=yellow, 4=blue, 5=magenta, 6=cyan, 7=white)
  // bg: "\e[Cm" where C = 40 + h [+ 60]
  // fg: "\e[Cm" where C = 30 + h [+ 60]
  X (46, 97), // cyan under bright white
  O (45, 97), // magenta under bright white
  V (43, 97), // yellow under bright white
  S (42, 97), // green under bright white
  EMPTY (100, 37), // bright black (dark grey) under dark white (light grey)
  LABEL(0, 90); // empty under bright black (dark grey)

  private final String esc = "\u001b["; // \e = \u001b
  private final int ansi_reset = 0; // reset: "\e[0m"
  private final int ansi_bg;
  private final int ansi_fg;

  static Player[] roster() { Player[] r = { X, O, V, S }; return r; }

  Player(int bg, int fg) {
    ansi_bg = bg;
    ansi_fg = fg;
  }

  String ansi() { return ansi(symbol()); }

  String ansi(char c) { return esc+ansi_bg+"m" + esc+ansi_fg+"m" + c + esc+ansi_reset+"m"; }

  String ansiFg(char c) { return esc+ansi_fg+"m" + c + esc+ansi_reset+"m"; }

  String ansiBg(char c) { return esc+ansi_bg+"m" + c + esc+ansi_reset+"m"; }

  char symbol() { return (this.ordinal() == EMPTY.ordinal()) ? '.' : this.name().charAt(0); }
}
