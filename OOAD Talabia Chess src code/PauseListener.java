// by Lim Cai Qing
import java.awt.event.*;

// ActionListener to handle the action of pausing the game
public class PauseListener implements ActionListener{
    private BoardView boardView;

    public PauseListener(BoardView boardView)
    {
        this.boardView=boardView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println("Game Paused");
        boardView.setDefaults();

    }
}
