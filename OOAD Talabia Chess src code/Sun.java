// by Teo Yu Jie
import java.lang.Math;

// Represents the Sun Chess Piece
public class Sun extends ChessPiece {
    public Sun(String side, String moveDirection){
        super(side);
        setPieceIcon(side + "_sun.png");
        setMoveDirection(moveDirection);
    }

    @Override
    public boolean toMove(int x, int y, Board board, boolean validateMove) {
        ChessPiece targetPiece = null; // Initialize to null

        for (int i = 0; i < 28; i++) {
            ChessPiece piece = board.getChessPiece(i);
            if (piece.getX() == x && piece.getY() == y) {
                targetPiece = piece; // Store the target piece
                break; // No need to continue searching
            }
        }

        if (targetPiece != null && targetPiece.getSide() == this.getSide()) {
            return false;
        }

        // Check if the move is within a one-cell radius
        if (Math.abs(x - getX()) <= 1 && Math.abs(y - getY()) <= 1) {
            boolean doneValidate = !validateMove;
            if (doneValidate) {
                if (targetPiece != null) {
                    capture(x, y, board);
                }
                setPosition(x, y);
            }
            return true;
        } 

        return false;
    }

}

