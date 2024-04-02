// by Emily Phang Ru Ying

// Transformation strategy to transform a TimePiece into a PlusPiece
public class TimeToPlus implements TransformStrategy {
    // Method to execute the transformation from TimePiece to Plus
    @Override
    public void executeTransform(Board board, ChessPiece chessPiece) {
        int x = chessPiece.getX();
        int y = chessPiece.getY();

        
        for (int i = 0; i < 28; i++) {
            ChessPiece currentPiece = board.getChessPiece(i);

            // Find the chess piece at the same position as the provided chessPiece
            if (currentPiece.getX() == x && currentPiece.getY() == y) {
                // Create a new Plus piece with the same side and move direction
                ChessPiece newPlusPiece = new Plus(chessPiece.getSide(), chessPiece.getMoveDirection());
                newPlusPiece.setPosition(x, y);

                // Replace the existing chess piece with the new Plus piece
                board.switchChessPiece(newPlusPiece, i);
                break;
            }
        }
}

}
