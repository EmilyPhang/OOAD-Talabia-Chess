// by Emily Phang Ru Ying

// Represents a transformation strategy for converting a Plus piece into a Time piece
public class PlusToTime implements TransformStrategy {
    @Override
    public void executeTransform(Board board, ChessPiece chessPiece) {
        int x = chessPiece.getX();
        int y = chessPiece.getY();

        for (int i = 0; i < 28; i++) {
            ChessPiece currentPiece = board.getChessPiece(i);

            // Find the chess piece at the same position as the provided chessPiece
            if (currentPiece.getX() == x && currentPiece.getY() == y) {
                // Create a new Time piece with the same side and move direction
                ChessPiece newTimePiece = new TimePiece(currentPiece.getSide(), chessPiece.getMoveDirection());
                newTimePiece.setPosition(x, y);

                // Replace the existing chess piece with the new Time piece
                board.switchChessPiece(newTimePiece, i);
                break;
            }
        }
    }

}