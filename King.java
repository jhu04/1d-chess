public class King extends Piece {
    public King(Board board, int player, int x) {
        super(board, player, x);
    }

    public boolean validPieceMovement(int x) {
        return Math.abs(x - this.x) == 1;
    }

    // public boolean inCheck() {

    // }

    public String toString() {
        return "K";
    }
}
