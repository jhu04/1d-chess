public class Rook extends Piece {
    public Rook(Board board, int player, int x) {
        super(board, player, x);
    }

    public boolean validPieceMovement(int x) {
        boolean res = true;
        if (x < this.x)
            for (int i = this.x - 1; i >= x; --i)
                res = res && inBounds(i);
        else for (int i = this.x + 1; i <= x; ++i)
            res = res && inBounds(i);
        return res && (this.x != x);
    }

    public String toString() {
        return "R";
    }
}
