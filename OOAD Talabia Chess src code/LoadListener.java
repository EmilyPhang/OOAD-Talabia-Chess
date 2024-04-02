// by Teo Yu Jie

// ActionListener to handle the action of loading a game state
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadListener implements ActionListener {
    private TextFile textFile;

    public LoadListener(Board board, BoardView boardView) {
        textFile=new TextFile(board,boardView);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        textFile.load();
    }
}  