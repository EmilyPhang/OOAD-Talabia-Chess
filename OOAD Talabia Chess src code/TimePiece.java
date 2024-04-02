// by Emily Phang Ru Ying
import java.lang.Math;

// Represents the Time Chess Piece
public class TimePiece extends ChessPiece {
    public TimePiece(String side, String moveDirection){
        super(side);
        setPieceIcon(side + "_time.png");
        setMoveDirection(moveDirection);
        setTransformStrategy(new TimeToPlus());
    }

    @Override
    public boolean toMove(int x, int y, Board board, boolean validateMove) {
        ChessPiece targetPiece = null; 

        for (int i = 0; i < 28; i++) {
            ChessPiece piece = board.getChessPiece(i);
            
            
            if (piece.getX() == x && piece.getY() == y) {
                targetPiece = piece; 
                break; 
            }
        }

        if (targetPiece != null && targetPiece.getSide() == this.getSide()) {
            return false; 
        }

        // Absolute difference
        int absDiffX = x - getX(); 
        int absDiffY = y - getY(); 
        // Check if the move is along a diagonal (absolute change in x equals absolute change in y)
        if (Math.abs(absDiffX) == Math.abs(absDiffY)) {
            

            // Loop through the positions between the current position and the target position
            for (int i = 1; i < Math.abs(absDiffY); i++) {
                int newX = getX();
                int newY = getY();

                // Update the new position based on the direction of the move (diagonal)
                if (absDiffX < 0) {
                    newX -= i; // Move left
                } else {
                    newX += i; // Move right
                }

                if (absDiffY < 0) {
                    newY -= i; // Move up
                } else {
                    newY += i; // Move down
                }

                // Check if there's a piece at the new position
                if (isPiecePresent(newX, newY, board)) {
                    return false; // move is blocked and invalid
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

     // Method to check if there's a piece at a specific position
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
