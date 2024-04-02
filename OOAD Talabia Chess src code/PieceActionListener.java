// by Toh Ee Lin
import java.awt.Color;
import java.awt.event.*;

// ActionListener to handle chess piece actions on the game board
public class PieceActionListener implements ActionListener {
    private static boolean isHolding = false;
    private static int currentChessPiece;
    private Board board;
    private BoardView boardView;
    private String currentSide; 

    public PieceActionListener(Board board, BoardView boardView) {
        this.board = board;
        this.boardView = boardView;
    }

    public static void setIsHolding(boolean hold) {
        isHolding = hold;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String[] position = e.getActionCommand().toString().split("\\s+");
        int currentX = Integer.parseInt(position[1]);
        int currentY = Integer.parseInt(position[0]);

        System.out.println(position[1] + " " + position[0] + " was clicked");

        if (!isHolding) {
            handleFirstClick(currentX, currentY);
        } else {
            handlePieceMove(currentX, currentY);
        }

        System.out.println("IS HOLDING: " + isHolding + "\n");
    }

    // Handle the first click when selecting a chess piece
    public void handleFirstClick(int currentX, int currentY) {
        if ((board.getPlayerOneMoveCount() + board.getPlayerTwoMoveCount()) % 2 == 0) {
            currentSide = "yellow";
        } else {
            currentSide = "blue";
        }
        // Iterate through chess pieces to find the one selected
        for (int i = 0; i < 28; i++) {
            ChessPiece piece = board.getChessPiece(i);

            if (piece.getX() == currentX && piece.getY() == currentY && piece.getSide().equals(currentSide)) {
                System.out.println("Choosen Chess Piece: " + i + "   Class: " + piece.getClass());
                System.out.println("Move Direction:" + piece.getMoveDirection());
                currentChessPiece = i;
                isHolding = true;
                boardView.changeBoardColor(piece.getX(), piece.getY(), new Color(173, 216, 230));
                highlightValidMoves();
                boardView.updateView();
                break;
            }
        }

        if (!isHolding) {
            boardView.gameDialog("Please choose a " + currentSide + " chess piece");
        }
    }

    // Highlight valid moves for the currently held chess piece
    public void highlightValidMoves() {
        for (int j = 0; j < 6; j++) {
            for (int k = 0; k < 7; k++) {
                if (board.getChessPiece(currentChessPiece).toMove(k, j, board, true)) {
                    System.out.println("Valid move: x=" + k + ", y=" + j);
                    boardView.changeBoardColor(k, j, new Color(188, 245, 188));
                }
            }
        }
    }

    // Handle the movement of the currently held chess piece
    public void handlePieceMove(int currentX, int currentY) {
        System.out.println("Now holding , currentX : " + currentX + "   currentY : " + currentY);

        ChessPiece movingPiece = board.getChessPiece(currentChessPiece);

        if (movingPiece.toMove(currentX, currentY, board, false)) {
            System.out.println("Selected chesspiece move successfully");
            handleMoveSuccess(currentX, currentY);
        } else {
            boardView.gameDialog("Move is invalid.");
        }

        clearBoardColors();
        isHolding = false;
    }

    // Handle the success of a chess piece move
    public void handleMoveSuccess(int currentX, int currentY) {
        if ((board.getPlayerOneMoveCount() + board.getPlayerTwoMoveCount()) % 2 == 0) {
            board.setPlayerOneMoveCount(board.getPlayerOneMoveCount() + 1);
            currentSide = "yellow";
            boardView.setLabelSide("blue", new Color(0, 0, 255));
        } else {
            board.setPlayerTwoMoveCount(board.getPlayerTwoMoveCount() + 1);
            currentSide = "blue";
            boardView.setLabelSide("yellow", new Color(204,204,0));
        }

        // Check if the game has been won
        if (board.getWon()) {
            boardView.gameDialog("Congratulations " + currentSide + " is the winner!");
            board.init();
            boardView.setDefaults();
        } else {
            board.checkTurnPiece(); // Check if any pieces need to be turned
            board.checkReverseDirection(currentChessPiece, currentX, currentY);
            board.flipBoard(); // to flip the board
        }

        boardView.updateView(); // update board to the latest view
    }

    // Clear the highlighting colors on the chess board
    public void clearBoardColors() {
        for (int j = 0; j < 6; j++) {
            for (int k = 0; k < 7; k++) {
                boardView.changeBoardColor(k, j, Color.white);
            }
        }
    }
}
