// by Toh Ee Lin

import java.awt.*;
import java.awt.event.*;

// ActionListener to start the game and set up the initial state
public class StartListener implements ActionListener{
    private BoardView boardView;
    private Board board;

    public StartListener(BoardView boardView,Board board){
        this.boardView=boardView;
        this.board=board;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println("Game started.");
        // Set up the boardView with listeners
        boardView.setListeners(); 
        boardView.setLabelInstruction("Current side to move: ");
        if ((board.getPlayerOneMoveCount()+board.getPlayerTwoMoveCount())%2==0){
            boardView.setLabelSide("yellow", new Color(204,204,0));
        }
        else{
            boardView.setLabelSide("blue", new Color(0,0,255));
        }

    }
}