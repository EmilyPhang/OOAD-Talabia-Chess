// by Emily Phang Ru Ying

import javax.swing.ImageIcon;

// Represents Point Chess Piece
public class Point extends ChessPiece {
    public Point(String side, String moveDirection){
        super(side);
        setMoveDirection(moveDirection);
        setPieceIcon(side+"_point_"+getMoveDirection()+".png");
        
    }

    @Override
    public boolean toMove(int x, int y, Board board, boolean validateMove) {
        int absDiffX = x - getX();
        int absDiffY = y - getY();

        // Check if the move direction matches the allowed directions ('up' or 'down')
        if ((getMoveDirection().equals("up") && absDiffY < 0) || (getMoveDirection().equals("down") && absDiffY > 0)) {
            // Check if the move is vertical 
            if (Math.abs(absDiffX) == 0 && Math.abs(absDiffY) >= 1 && Math.abs(absDiffY) <= 2) {
                int middleY;
                if (getMoveDirection().equals("up")) {
                    middleY = getY() - 1;
                } else {
                    middleY = getY() + 1;
                }

                if (Math.abs(absDiffY) == 2) {
                    if (isPiecePresent(x, middleY, board)) {
                        return false;
                    }
                }

                // Initialize to -1, indicating that no target piece is found yet
                int targetPieceIndex = -1;
                for (int i = 0; i < 28; i++) {
                    ChessPiece piece = board.getChessPiece(i);
                    if (piece.getX() == x && piece.getY() == y) {
                        targetPieceIndex = i; // target piece found
                        break;
                    }
                }

                // Check if a target piece is found
                if (targetPieceIndex != -1) {
                    ChessPiece targetPiece = board.getChessPiece(targetPieceIndex);
                    if (targetPiece.getSide() == this.getSide()) {
                        return false;
                    } else {
                        if (!validateMove) {
                            capture(x, y, board);
                            setPosition(x, y);
                        }
                        return true;
                    }
                } else {
                    if (!validateMove) {
                        setPosition(x, y);
                    }
                    return true;
                }
            }
        }

        return false;
    }


    // check if a chess piece is present at a specific position on the board
    public boolean isPiecePresent(int x, int y, Board board) {
        for (int i = 0; i < 28; i++) {
            ChessPiece piece = board.getChessPiece(i);
            if (piece.getY() == y && piece.getX() == x) {
                return true;
            }
        }
        return false;
    }
    
    // Method to get the ImageIcon for the Point piece
    @Override
    public ImageIcon getPieceIcon(){
        return loadPieceImage(getSide()+"_point_"+ getMoveDirection() +".png");
    }
}