// by Emily Phang Ru Ying
import java.lang.Math;

// Represents Plus Chess Piece
public class Plus extends ChessPiece {
    public Plus(String side, String moveDirection){
        super(side);
        setPieceIcon(side + "_plus.png");
        setMoveDirection(moveDirection);
        setTransformStrategy(new PlusToTime());
    }

    @Override
    public boolean toMove(int x, int y, Board board, boolean validateMove) {
        ChessPiece targetPiece = null; // Initialize to null

        for (int i = 0; i < 28; i++) {
            ChessPiece piece = board.getChessPiece(i);
            if (piece.getY() == y && piece.getX() == x) {
                targetPiece = piece; 
                break; 
            }
        }

        if (targetPiece != null && targetPiece.getSide() == this.getSide()) {
            return false; // Cannot capture your own piece
        }

        // Calculate the absolute difference in x and y coordinates
        int absDiffX = x - getX();
        int absDiffY = y - getY();

        // Check if the move is either horizontally within a range of 6 squares or vertically within a range of 5 squares
        if (((Math.abs(absDiffX) <= 6 && absDiffY == 0) || (Math.abs(absDiffY) <= 5 && absDiffX == 0))) {
            // Check if there are pieces blocking the move in the specified direction
            if (absDiffY < 0) { // go up
                for (int i = 1; i < -absDiffY; i++) {
                    if (isPiecePresent(x, getY() - i, board)) {
                        return false;
                    }
                }
            } else if (absDiffY > 0) { // go down
                for (int i = 1; i < absDiffY; i++) {
                    if (isPiecePresent(x, getY() + i, board)) {
                        return false;
                    }
                }
            } else if (absDiffX < 0) { // go left
                for (int i = 1; i < -absDiffX; i++) {
                    if (isPiecePresent(getX() - i, y, board)) {
                        return false;
                    }
                }
            } else if (absDiffX > 0) { // go right
                for (int i = 1; i < absDiffX; i++) {
                    if (isPiecePresent(getX() + i, y, board)) {
                        return false;
                    }
                }
            }

            boolean doneValidate = !validateMove;
            if (doneValidate) {
                if (targetPiece != null) {
                    capture(x, y, board);
                }
                setPosition(x, y);
            }
            return true;
        } else {
            return false;
        }
    }

    // check if a piece is present at a specified position
    public boolean isPiecePresent(int x, int y, Board board) {
        for (int i = 0; i < 28; i++) {
            ChessPiece piece = board.getChessPiece(i);
            if (piece.getY() == y && piece.getX() == x) {
                return true;
            }
        }
        return false;
    }

}
