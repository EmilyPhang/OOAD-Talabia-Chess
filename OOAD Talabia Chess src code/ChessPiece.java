// by Emily Phang Ru Ying & Teo Yu Jie

import java.awt.*;
import java.net.URL;
import javax.swing.ImageIcon;

// This abstract class represents a chess piece in the game
public abstract class ChessPiece{
    private int x; 
    private int y; 
    private String side; 
    private String moveDirection = "up"; 
    private TransformStrategy transformStrategy = new NoTransform();
    private ImageIcon pieceIcon = null;
    
    

    public ChessPiece(String side){
        setSide(side);
    }

    // by Emily
    // Load and return the image icon for the chess piece from a given image path
    protected ImageIcon loadPieceImage(String imagePath){
        URL imageUrl = this.getClass().getResource(imagePath);
        if (imageUrl == null) {
            // Handle the case where resource is not found
            System.out.println("Resource not found: " + imagePath);
            return null;
        }
        Image image = new ImageIcon(this.getClass().getResource(imagePath)).getImage();
        Image scaledImage = image.getScaledInstance(150,150,Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    // by Emily
    // Abstract method to determine if the chess piece can move to a specified location
    public abstract boolean toMove(int x, int y, Board board, boolean validateMove);

    // by Yu Jie
    public void setPieceIcon(String imagePath){
        pieceIcon = loadPieceImage(imagePath);
    }

    // by Yu Jie
    public void setSide(String side){
        this.side = side;
    }

    // by Yu Jie
    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    // by Yu Jie
    public void setMoveDirection(String moveDirection){
        this.moveDirection = moveDirection;
    }

    // by Emily
    public void setTransformStrategy(TransformStrategy transformStrategy){
        this.transformStrategy = transformStrategy;
    }

    // by Yu Jie
    public ImageIcon getPieceIcon(){
        return pieceIcon;
    }

    // by Yu Jie
    public int getX(){
        return x;
    }

    // by Yu Jie
    public int getY(){
        return y;
    }

    // by Yu Jie
    public String getMoveDirection(){
        return moveDirection;
    }

    // by Yu Jie
    public String getSide(){
        return side;
    }

    // by Emily
    // Captures a chess piece at a specified position on the game board
    public void capture(int x, int y, Board board){
        for (int i = 0; i < 28; i++){
            ChessPiece piece = board.getChessPiece(i);
            if(piece.getX() == x && piece.getY() == y){
                if (piece.getClass() == Sun.class ){
                    board.setWon(true);
                }
                piece.setPosition(-1,-1);
            }
        }
    }

    // by Emily
    public void reverseDirection(){
        if (moveDirection.equals("up")){
            moveDirection = "down";
        }
        else{
            moveDirection = "up";
        }
    }

    // by Emily
    // Turn the chess piece based on the transform strategy
    public void turnPiece(Board board){ 
        //Time and Plus pieces will change 
        transformStrategy.executeTransform(board, this);
    }

}
