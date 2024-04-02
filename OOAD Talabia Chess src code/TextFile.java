// by Toh Ee Lin & Teo Yu Jie

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

// Represents a class responsible for handling saving and loading game data
public class TextFile {
    private Board board;
    private BoardView boardView;

    public TextFile(Board board, BoardView boardView){
        this.board = board;
        this.boardView = boardView;
    }

    // by Ee Lin
    public void save() {
        try {
            FileWriter writer = new FileWriter("Save.txt");
            saveChessPieces(writer);
            savePlayerMoveCounts(writer);

            writer.close();
            System.out.println("\n\nSave Successful...\n\n");
        } catch (IOException e) {
            System.out.println(e);
            boardView.gameDialog("Failed to Save.");
        }
    }

    // by Ee Lin
    public void saveChessPieces(FileWriter writer) throws IOException {
        for (int i = 0; i < 28; i++) {
            ChessPiece piece = board.getChessPiece(i);

            writer.write(piece.getSide() + " " + piece.getClass().getSimpleName());
            writer.write("\nMove Direction: " + piece.getMoveDirection() + "\n");

            if (piece.getX() == -1) {
                writer.write("X Coordinate: Chesspiece Eaten\nY Coordinate: Chesspiece Eaten\n\n");
            } else {
                writer.write("X Coordinate: " + Integer.toString(piece.getX()) +
                        "\nY Coordinate: " + Integer.toString(piece.getY()) + "\n\n");
            }
        }
    }

    // by Ee Lin
    public void savePlayerMoveCounts(FileWriter writer) throws IOException {
        writer.write("Current Player One Move Count: " + board.getPlayerOneMoveCount());
        writer.write("\nCurrent Player Two Move Count: " + board.getPlayerTwoMoveCount());
    }


    // by Yu Jie
    public void load() {
        try {
            ArrayList<String> datalist = readDataFromFile("Save.txt");
            setPlayerMoveCounts(datalist);
            loadChessPieces(datalist);

            boardView.setDefaults();
            datalist.clear();
            System.out.println("Load Successful...");
        } catch (Exception e) {
            System.out.println(e);
            boardView.gameDialog("No Saved File Yet.");
        }
    }

    // by Yu Jie
    // Read data from a file and return it as an ArrayList of strings
    public ArrayList<String> readDataFromFile(String fileName) throws FileNotFoundException {
        ArrayList<String> datalist = new ArrayList<>();
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            datalist.add(scanner.nextLine());
        }

        scanner.close();
        return datalist;
    }

    // by Yu Jie
    public void setPlayerMoveCounts(ArrayList<String> datalist) {
        board.setPlayerOneMoveCount(Integer.parseInt(datalist.get(140).substring(31)));
        System.out.println(board.getPlayerOneMoveCount());
        board.setPlayerTwoMoveCount(Integer.parseInt(datalist.get(141).substring(31)));
        System.out.println(board.getPlayerTwoMoveCount());
    }

    // by Yu Jie
    public void loadChessPieces(ArrayList<String> datalist) {
        String[] chessInfo;

        for (int i = 0; i < 28; i++) {
            ChessPiece piece = board.getChessPiece(i);
            chessInfo = (datalist.get(i * 5)).split("\\s+");

            if (datalist.get(i * 5 + 2).substring(14).equals("Chesspiece Eaten")) {
                piece.setPosition(-1, -1);
            } else {
                piece.setPosition(
                    Integer.parseInt(datalist.get(i * 5 + 2).substring(14)),
                    Integer.parseInt(datalist.get(i * 5 + 3).substring(14))
                );
            }
            piece.setMoveDirection(datalist.get(i * 5 + 1).substring(16).toString());

            if (!piece.getClass().getSimpleName().equals(chessInfo[1])) {
                piece.turnPiece(board);
            }
        }
    }

}