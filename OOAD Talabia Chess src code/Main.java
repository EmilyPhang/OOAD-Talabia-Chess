// by Lim Cai Qing
import javax.swing.SwingUtilities;

// Represents the entry point of the program
public class Main {
    public static void main(String[] args) {
        // Start the Swing application on the event dispatch thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameView gameView = new GameView(null); // Pass null initially
                gameView.setVisible(true);
            }
        });
    }
}
