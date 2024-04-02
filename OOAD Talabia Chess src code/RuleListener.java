// by Toh Ee Lin

import java.awt.event.*;
import javax.swing.JOptionPane;

// ActionListener to display game rules when triggered
public class RuleListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e){
        // Show a message dialog with the game rules
        JOptionPane.showMessageDialog(null, 
        "Talabia Chess Rules: \n" +
        "The Sun Piece can only move one step in any direction. " +
        "The game ends when the Sun Piece is captured by the other side. \n" +
        "The Point Piece can only move forward 1 or 2 steps. If it reaches the end of the board, it turns around and starts heading back the other way. \n" +
        "The Hourglass Piece moves in a 3x2 L shape in any orientation. It can skip over other pieces.  \n" +
        "The Time Piece can only move diagonally but can go any distance. \n" +
        "The Plus Piece can move horizontally and vertically only but can go any distance. \n" +
        "All pieces cannot skip over other pieces except the Hourglass Piece.");
    }

}

