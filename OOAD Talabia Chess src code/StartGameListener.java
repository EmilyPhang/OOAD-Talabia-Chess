// by Lim Cai Qing
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// ActionListener to start a new game
public class StartGameListener implements ActionListener {
    private GameView gameView;

    public StartGameListener(GameView gameView) {
        this.gameView = gameView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameView != null) {
            Board board = new Board();
            board.init();
            BoardView boardView = new BoardView("Talabia Chess", board);
            boardView.setDefaults();
            // Dispose of the current GameView to start a new game
            gameView.dispose();
        }
    }
}
