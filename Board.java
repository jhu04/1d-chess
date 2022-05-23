import java.util.Scanner;

public class Board {
    private final int n;
    private final Piece[] tiles;
    private final Piece[][] pieces;
    private int player;
    private boolean finished;

    public Board(int n) {
        if (n <= 6)
            throw new IllegalArgumentException("Board must be at least 6 tiles long");
        
        this.n = n;
        this.tiles = new Piece[n];
        this.pieces = new Piece[2][3];
        pieces[0][0] = new King(this, 0, 0);
        pieces[0][1] = new Knight(this, 0, 1);
        pieces[0][2] = new Rook(this, 0, 2);
        pieces[1][0] = new King(this, 1, n - 1);
        pieces[1][1] = new Knight(this, 1, n - 2);
        pieces[1][2] = new Rook(this, 1, n - 3);

        for (Piece[] playerPieces : pieces)
            for (Piece playerPiece : playerPieces)
                tiles[playerPiece.getX()] = playerPiece;
        
        this.player = 0;
        this.finished = false;
    }

    public Board() {
        this(8);
    }

    public int size() {
        return n;
    }

    public Piece getPiece(int x) {
        return tiles[x];
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (Piece p : tiles) {
            if (p == null)
                sb.append(" . ");
            else
                sb.append(String.format(" %s ", p.toString()));
        }

        sb.append('\n');
        for (int i = 0; i < Math.min(n, 10); ++i)
            sb.append(String.format(" %s ", i));

        return sb.toString();
    }

    public void move(String piece, int x) {
        if ("king".equals(piece.toLowerCase()) || "k".equals(piece.toLowerCase()))
            move(pieces[player][0], x);
        else if ("knight".equals(piece.toLowerCase()) || "n".equals(piece.toLowerCase()))
            move(pieces[player][1], x);
        else if ("rook".equals(piece.toLowerCase()) || "r".equals(piece.toLowerCase()))
            move(pieces[player][2], x);
    }

    public void move(Piece piece, int x) {
        piece.setX(x);
    }

    public Piece getTilesElement(int x) {
        return tiles[x];
    }

    public void setTilesElement(int x, Piece piece) {
        tiles[x] = piece;
    }

    public void capture(int x) {
        tiles[x].captured();
    }

    public int getPlayer() {
        return player;
    }

    public int nextPlayer() {
        ++player;
        player %= 2;
        return player;
    }

    public boolean getFinished() {
        return finished;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        Board b = new Board();

        int player = 0;
        while (!b.getFinished()) {
            System.out.println("BOARD:");
            System.out.println(b);

            System.out.println("PLAYER " + player + "'S MOVE");

            String[] move = sc.nextLine().strip().split(" ");

            System.out.println();

            try {
                b.move(move[0], Integer.parseInt(move[1]));
            } catch(ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid input");
            } catch(IllegalArgumentException e) {
                System.out.println("Invalid move");
                continue;
            }

            b.nextPlayer();
        }

        sc.close();
    }
}