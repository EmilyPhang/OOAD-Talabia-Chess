// by Emily Phang Ru Ying

// Represents a "NoTransform" strategy for transforming ChessPieces
// Implements the TransformStrategy interface but performs no actual transformation
class NoTransform implements TransformStrategy {
    @Override
    // Used for ChessPieces that do not have a transformation behavior
    public void executeTransform(Board board, ChessPiece chessPiece) {
        
        
    }
}
