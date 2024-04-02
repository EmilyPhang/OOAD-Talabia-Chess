// by Toh Ee Lin & Lim Cai Qing

import javax.swing.*;
import java.awt.*;
import java.net.URL;

// Represents the main GUI for the Talabia Chess game.
// It provides a menu with options to start the game or quit.
public class GameView extends JFrame {
    
    private JPanel mainPanel = new BackgroundPanel();
    private JLabel titleLabel = new JLabel("Talabia Chess", JLabel.CENTER);
    private JButton startGameButton;
    private JButton quitButton;
    
    public GameView(GameView gameView) {

        setTitle("Talabia Chess");
        setSize(400, 300);

        // Configure mainPanel layout and appearance
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setOpaque(false);  // Make mainPanel transparent

        // Load the background image 
        BackgroundPanel.setBackgroundImage(loadBgImage("background.png"));        

        // Configure titleLabel appearance
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 50));

        startGameButton = createButton("START");
        quitButton = createButton("QUIT");

        // Add GUI components to mainPanel
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(startGameButton);
        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(quitButton);
        mainPanel.add(Box.createVerticalGlue());

        // Attach action listeners to buttons
        startGameButton.addActionListener(new StartGameListener(this));
        quitButton.addActionListener(new ExitListener());

        // Add mainPanel to the frame and configure frame
        add(mainPanel);
        pack();
        setMinimumSize(new Dimension(800, 800));
        setLocationRelativeTo(null);
        setBackground(new Color(1, 50, 32)); // Set the background color of the frame
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // by Cai Qing
    // Method to load and scale a background image
    protected ImageIcon loadBgImage(String imagePath) {
        URL imageUrl = this.getClass().getResource(imagePath);
        if (imageUrl == null) {
            System.out.println("Resource not found: " + imagePath);
            return null;
        }
        Image image = new ImageIcon(imageUrl).getImage();
        Image scaledImage = image.getScaledInstance(800, 800, Image.SCALE_AREA_AVERAGING);
        return new ImageIcon(scaledImage);
    }
    
    // by Cai Qing
    // Method to create and configure a button
    public JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFont(new Font("Arial", Font.BOLD, 24));
        button.setForeground(Color.WHITE);
        button.setBackground(Color.GRAY);
        button.setFocusPainted(false); // Remove the focus ring around the text
        Dimension buttonSize = new Dimension(250, 125);
        button.setPreferredSize(buttonSize);
        button.setMaximumSize(buttonSize);
        return button;
    }

    // by Ee Lin
    // Custom JPanel to draw the background image
    private class BackgroundPanel extends JPanel {
        private static final long serialVersionUID = 1L;
        private static ImageIcon backgroundImage;

        // Set the background image for the panel
        public static void setBackgroundImage(ImageIcon image) {
            backgroundImage = image;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                // Draw background image to fit the panel dimensions
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

}