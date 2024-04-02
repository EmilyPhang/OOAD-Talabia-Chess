// by Teo Yu jie 

import java.lang.Math;

// Represents Hourglass chess piece
public class Hourglass extends ChessPiece {
    public Hourglass(String side, String moveDirection){
        super(side);
        setPieceIcon(side + "_hourglass.png");
        setMoveDirection(moveDirection);
        
    }

    @Override
    public boolean toMove(int x, int y, Board board, boolean validateMove) {
        ChessPiece targetPiece = null; 

        // Loop through all the chess pieces on the board
        for (int i = 0; i < 28; i++) {
            ChessPiece piece = board.getChessPiece(i);
            
            // Check if the current piece is at the target position (x, y)
            if (piece.getX() == x && piece.getY() == y) {
                targetPiece = piece; // If found, store the target piece
                break; 
            }
        }

        // Check if the target piece exists and has the same side as this piece
        if (targetPiece != null && targetPiece.getSide().equals(this.getSide())) {
            return false; // If yes, the move is invalid (cannot capture your own piece)
        }

        // Calculate the absolute difference in x and y coordinates 
        int absDiffX = Math.abs(x - getX());
        int absDiffY = Math.abs(y - getY());

         // Check if the move is in an L shape (2x3 or 3x2) in any orientation
        if ((absDiffX == 1 || absDiffY == 1) && (absDiffX == 2 || absDiffY == 2)) {
            boolean doneValidate = !validateMove;
            if (doneValidate) {
                if (targetPiece != null) {
                    capture(x, y, board); // If a piece is captured, perform the capture action
                }
                setPosition(x, y); // Update the position of this piece
            }
            return true; // The move is valid
        } else {
            return false; // The move is not in the required L shape, so it's invalid
        }
    }

    
}