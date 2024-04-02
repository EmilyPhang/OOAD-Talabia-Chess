// by Emily Phang Ru Ying & Lim Cai Qing

// Represents the game board, managing the game state and chess pieces.
public class Board {
    private boolean gameWon = false;
    private int playerOneMoveCount = 0; 
    private int playerTwoMoveCount = 0;
    private ChessPiece[] chessPiece = new ChessPiece[28];

    // by Cai Qing
    public boolean getWon(){
        return gameWon;
    }
    // by Cai Qing
    public int getPlayerOneMoveCount(){
        return playerOneMoveCount;
    }
    // by Cai Qing
    public int getPlayerTwoMoveCount(){
        return playerTwoMoveCount;
    }
    // by Cai Qing
    public ChessPiece getChessPiece(int index){
        return chessPiece[index];
    }
    // by Cai Qing
    public void setWon(boolean gameWon){
        this.gameWon = gameWon;
    }
    // by Cai Qing
    public void setPlayerOneMoveCount(int moveCount){
        playerOneMoveCount = moveCount;
    }
    // by Cai Qing
    public void setPlayerTwoMoveCount(int moveCount){
        playerTwoMoveCount = moveCount;
    }
    // by Cai Qing
    // Initializes the game board, chess pieces, and their positions
    public void init(){
        gameWon = false;
        playerOneMoveCount = 0;
        playerTwoMoveCount = 0;
        
        
        chessPiece[0] = new Plus("blue","down");
        
        chessPiece[1] = new Hourglass("blue","down");
        chessPiece[2] = new TimePiece("blue","down");
        chessPiece[3] = new Sun("blue","down");
        chessPiece[4] = new TimePiece("blue","down");
        chessPiece[5] = new Hourglass("blue","down");
        chessPiece[6] = new Plus("blue","down");
        chessPiece[7] = new Point("blue","down");
        chessPiece[8] = new Point("blue","down");
        chessPiece[9] = new Point("blue","down");
        chessPiece[10] = new Point("blue","down");
        chessPiece[11] = new Point("blue","down");
        chessPiece[12] = new Point("blue","down");
        chessPiece[13] = new Point("blue","down");

        chessPiece[0].setPosition(0,0);
        chessPiece[1].setPosition(1,0);
        chessPiece[2].setPosition(2,0);        
        chessPiece[3].setPosition(3,0);        
        chessPiece[4].setPosition(4,0);        
        chessPiece[5].setPosition(5,0);        
        chessPiece[6].setPosition(6,0);
        chessPiece[7].setPosition(0, 1);       
        chessPiece[8].setPosition(1,1);        
        chessPiece[9].setPosition(2,1); 
        chessPiece[10].setPosition(3,1);    
        chessPiece[11].setPosition(4,1);
        chessPiece[12].setPosition(5,1);        
        chessPiece[13].setPosition(6,1);

        
        chessPiece[14] = new Plus("yellow","up");
        chessPiece[15] = new Hourglass("yellow","up");
        chessPiece[16] = new TimePiece("yellow","up");
        chessPiece[17] = new Sun("yellow","up");
        chessPiece[18] = new TimePiece("yellow","up");
        chessPiece[19] = new Hourglass("yellow","up");
        chessPiece[20] = new Plus("yellow","up");
        chessPiece[21] = new Point("yellow","up");
        chessPiece[22] = new Point("yellow","up");
        chessPiece[23] = new Point("yellow","up");
        chessPiece[24] = new Point("yellow","up");
        chessPiece[25] = new Point("yellow","up");
        chessPiece[26] = new Point("yellow","up");
        chessPiece[27] = new Point("yellow","up");

        chessPiece[14].setPosition(0,5);        
        chessPiece[15].setPosition(1,5);        
        chessPiece[16].setPosition(2,5);        
        chessPiece[17].setPosition(3,5);        
        chessPiece[18].setPosition(4,5);        
        chessPiece[19].setPosition(5,5);        
        chessPiece[20].setPosition(6,5);
        chessPiece[21].setPosition(0,4);
        chessPiece[22].setPosition(1,4);
        chessPiece[23].setPosition(2,4);
        chessPiece[24].setPosition(3,4);
        chessPiece[25].setPosition(4,4);
        chessPiece[26].setPosition(5,4);
        chessPiece[27].setPosition(6,4);

        

    }

    // by Emily
    // Flips the board and reverses the direction of all chess pieces
    public void flipBoard(){
        for (int i = 0; i < 28; i++) {
            int preX = chessPiece[i].getX();
            int preY = chessPiece[i].getY();
            chessPiece[i].setPosition(6 - preX, 5 - preY);
            chessPiece[i].reverseDirection();
        }
    }

    // by Emily
    // Checks if it's time to turn the chess pieces based on player moves
    public void checkTurnPiece(){
        if (playerOneMoveCount % 2 == 0 && playerTwoMoveCount % 2 == 0 && playerTwoMoveCount != 0) {
            for (ChessPiece piece : chessPiece) {
                piece.turnPiece(this);
            }
        }
    }

    // by Emily
    public void switchChessPiece(ChessPiece newChessPiece, int index){
        chessPiece[index] = newChessPiece;
    }

    // by Emily
    public void checkReverseDirection(int currentPiece, int currentX, int currentY){
        if ((currentY == 0 && chessPiece[currentPiece].getMoveDirection().equals("up")) ||
            (currentY == 6 && chessPiece[currentPiece].getMoveDirection().equals("down"))) {
            chessPiece[currentPiece].reverseDirection();
        }
    }

    

    

    

}
