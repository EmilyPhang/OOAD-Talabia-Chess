// by Toh Ee Lin

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// ActionListener to save the game state
public class SaveListener implements ActionListener {
    private TextFile textFile;

    public SaveListener(Board board, BoardView boardView) {
        textFile=new TextFile(board,boardView);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        textFile.save();
    }
}  