public class Piece { // what does abstract do?
    final Board board; // what does protected do?
    private final int player;
    protected int x;
    private boolean taken;

    public Piece(Board board, int player, int x) {
        this.board = board;
        this.player = player;
        this.x = x;
        this.taken = false;
    }

    public boolean inBounds(int x) {
        return 0 <= x && x < board.size() && (board.getPiece(x) == null || board.getPiece(x).getPlayer() != this.player);
    }

    // TODO: what does abstract do when applied to a method?
    // public abstract boolean validPieceMovement(int x);

    public boolean valid(int x) {
        // return inBounds(x) && validPieceMovement(x);

        // TODO: PLACEHOLDER, remove
        return false;
    }

    public int getPlayer() {
        return player;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        if (valid(x)) {
            int temp = this.x;
            this.x = x;

            board.setTilesElement(temp, null);

            if (board.getTilesElement(this.x) != null)
                board.capture(this.x);

            board.setTilesElement(this.x, this);
        } else
            throw new IllegalArgumentException("Invalid x for setX");
    }

    public boolean getTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public void captured() {
        setTaken(true);
    }

    // TODO: see line 18
    // public abstract String toString();
}