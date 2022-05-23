public class Knight extends Piece {
    public Knight(Board board, int player, int x) {
        super(board, player, x);
    }

    public boolean validPieceMovement(int x) {
        return Math.abs(x - this.x) == 2;
    }
    
    public String toString() {
        return "N";
    }
}
