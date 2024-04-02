// by Toh Ee Lin & Lim Cai Qing

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Represents the graphical user interface for the game board
public class BoardView extends JFrame {
    private Board board;
    private JButton[][] buttons = new JButton[6][7];
    private JButton startButton = new JButton("Start");
    private JButton pauseButton = new JButton("Pause");
    private JPanel gamePanel = new JPanel(new GridLayout(6, 7));
    private JPanel topPanel = new JPanel(new BorderLayout());
    private JPanel statusPanel = new JPanel(new FlowLayout());
    private JPanel buttonPanel = new JPanel(new FlowLayout());
    private JMenuBar menuBar = new JMenuBar();
    private JLabel labelInstruction = new JLabel();
    private JLabel labelSide = new JLabel();

    public BoardView(String name, Board board) {
        super(name);
        this.board = board;
        initializeComponents();
        configureLayout();
        setListeners();
        setDefaults();
    }

    // by Cai Qing
    // Initialize buttons and UI components
    // Add listeners and set properties for buttons
    public void initializeComponents() {
        startButton.setBackground(new Color(102,255,102));
        pauseButton.setBackground(new Color(255,102,102));
        startButton.addActionListener(new StartListener(this, board));
        pauseButton.addActionListener(new PauseListener(this));

        buttonPanel.add(startButton);
        buttonPanel.add(pauseButton);

        statusPanel.add(labelInstruction);
        statusPanel.add(labelSide);

        topPanel.add(statusPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.PAGE_START);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setActionCommand(i + " " + j);
                buttons[i][j].setBackground(Color.WHITE);
                gamePanel.add(buttons[i][j]);
            }
        }
    }

    // by Cai Qing
    /// Configure the layout of the UI components
    // Add menu items to the menu bar
    public void configureLayout() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(800, 800));
        addMenu();
        setJMenuBar(menuBar);
        add(topPanel, BorderLayout.NORTH);
        add(gamePanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // by Cai Qing
    // Set the label for game instructions
    public void setLabelInstruction(String instruction){
        labelInstruction.setText(instruction);
    }

    // by Cai Qing
    // Set the label to display the current player's side and color
    public void setLabelSide(String side,Color color){
        labelSide.setText(side);
        labelSide.setForeground(color);
    }

    // by Ee Lin
    // Set action listeners for buttons
    public void setListeners() {
        deleteListeners();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                addButtonActionListener(buttons[i][j], i, j);
                buttons[i][j].setActionCommand(i + " " + j);
            }
        }
    }

    // by Ee Lin
    // Add action listener for a specific button
    public void addButtonActionListener(JButton button, int row, int col) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PieceActionListener(board, BoardView.this).actionPerformed(e);
            }
        });
    }

    // by Ee Lin
    // Remove all action listeners from buttons
    public void deleteListeners() {
        
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                ActionListener[] actionListeners = buttons[i][j].getActionListeners();
                int listenerCount = actionListeners.length;

                for (int k = 0; k < listenerCount; k++) {
                    buttons[i][j].removeActionListener(actionListeners[k]);
                }
            }
        }
        
    }

    // by Ee Lin
    // Reset UI components to default state
    public void setDefaults() {
        deleteListeners();
        PieceActionListener.setIsHolding(false);
        labelInstruction.setText("Click on Start to Play");
        labelSide.setText("");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                buttons[i][j].setBackground(Color.white);
            }
        }
        updateView();
    }

    // by Ee Lin
    // Update the graphical representation of the game board
    public void updateView() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                buttons[i][j].setIcon(null);
            }
        }

        for (int i = 0; i < 28; i++) {
            if ((board.getChessPiece(i).getY() >= 0 && board.getChessPiece(i).getY() < 6) && (board.getChessPiece(i).getX() >= 0 && board.getChessPiece(i).getX() < 7)) {
                buttons[board.getChessPiece(i).getY()][board.getChessPiece(i).getX()].setIcon(board.getChessPiece(i).getPieceIcon());
            }
        }
    }

    // by Cai Qing
    // Change the background color of a specific button on the board
    public void changeBoardColor(int x, int y, Color color) {
        buttons[y][x].setBackground(color);
    }

    // by Cai Qing
    // Display a game-related message dialog
    public void gameDialog(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    // by Cai Qing
    public void addMenu() {
        JMenu settingsMenu = new JMenu("Settings");
        menuBar.add(settingsMenu);

        JMenuItem newGame = new JMenuItem("New Game");
        settingsMenu.add(newGame);
        newGame.addActionListener(new NewGameListener(board, this));

        JMenuItem loadGame = new JMenuItem("Load Game");
        settingsMenu.add(loadGame);
        loadGame.addActionListener(new LoadListener(board, this));

        JMenuItem saveGame = new JMenuItem("Save Game");
        settingsMenu.add(saveGame);
        saveGame.addActionListener(new SaveListener(board, this));

        JMenuItem exit = new JMenuItem("Exit");
        settingsMenu.add(exit);
        exit.addActionListener(new ExitListener());

        JMenu helpMenu = new JMenu("How To Play");
        menuBar.add(helpMenu);

        JMenuItem rule = new JMenuItem("Rules");
        helpMenu.add(rule);
        rule.addActionListener(new RuleListener());
    }
}
