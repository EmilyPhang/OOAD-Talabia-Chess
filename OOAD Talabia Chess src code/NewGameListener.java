// by Lim Cai Qing

// ActionListener to handle the action of starting a new game
import java.awt.event.*;
public class NewGameListener implements ActionListener {
    private BoardView boardView;
    private Board board;
    public NewGameListener(Board board, BoardView boardView)
    {
        this.board= board;
        this.boardView = boardView;
    }

    //reset the board and boardview
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        board.init(); 
        boardView.setDefaults();
    }
}