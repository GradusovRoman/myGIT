package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Random;

public class GameBoard extends JFrame {
    static int dimension = 3;
    static int cellSize = 150;
    private char[][] gameField;
    private GameButton[] gameButtons;

    private Game game;

    static char nullSymbol = '\u0000';

    public GameBoard(Game currentGame) {
        this.game = currentGame;
        initField();
    }

    private void initField() {
        setBounds(cellSize * dimension, cellSize * dimension, 400, 300);
        setTitle("Крестики-нолики");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());
        JPanel controlPanel = new JPanel();

        JButton newGameButton = new JButton("Новая игра");
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                emptyField();
            }
        });

        JCheckBoxMenuItem checkbox = new JCheckBoxMenuItem("Включить АI");

        checkbox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    game.setHardMode(true);
                } else {
                    game.setHardMode(false);
                }
            }
        });

        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
        controlPanel.add(newGameButton);
        controlPanel.add(checkbox);
        controlPanel.setSize(cellSize * dimension, 150);

        JPanel gameFieldPanel = new JPanel();
        gameFieldPanel.setLayout(new GridLayout(dimension, dimension));
        gameFieldPanel.setSize(cellSize * dimension, cellSize * dimension);

        gameField = new char[dimension][dimension];
        gameButtons = new GameButton[dimension * dimension];

        for (int i = 0; i < (dimension * dimension); i++) {
            GameButton fieldButton = new GameButton(i, this);
            gameFieldPanel.add(fieldButton);
            gameButtons[i] = fieldButton;
        }

        getContentPane().add(controlPanel, BorderLayout.NORTH);
        getContentPane().add(gameFieldPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    void emptyField() {

        //установим первого игрока x(0)
        game.setFirstTurn();

        for (int i = 0; i < (dimension * dimension); i++) {
            gameButtons[i].setText("");

            int x = i / GameBoard.dimension;
            int y = i % GameBoard.dimension;

            gameField[x][y] = nullSymbol;

        }
    }

    Game getGame() {
        return game;
    }
    /*x - по горизонтали
      y - по вертикали*/

    boolean isTurnable(int x, int y) {
        boolean result = false;
        if (gameField[y][x] == nullSymbol) {
            result = true;
        }
        return result;
    }

    void updateGameField(int x, int y) {
        gameField[y][x] = game.getCurrentPlayer().getPlayerSign();
    }

    boolean checkWin() {
        boolean result = false;
        char playerSimbol = getGame().getCurrentPlayer().getPlayerSign();


        if (checkWinDiagonals(playerSimbol) || checkWinLines(playerSimbol)) {
            result = true;
        }
        return result;
    }

    boolean isFull() {
        boolean resault = true;
        breakLoop:
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (gameField[i][j] == nullSymbol) {
                    resault = false;
                    break breakLoop;     //прирываем цикл на первом nullsymbol

                }
            }
        }
        return resault;
    }

    public GameButton getButton(int buttonIndex) {
        return gameButtons[buttonIndex];
    }

    private boolean checkWinLines(char playerSimbol) {
        boolean cols, rows, result;
        result = false;

        for (int col = 0; col < dimension; col++) {
            cols = true;
            rows = true;

            for (int row = 0; row < dimension; row++) {
                cols &= (gameField[col][row] == playerSimbol);
                rows &= (gameField[row][col] == playerSimbol);
            }
            if (cols || rows) {
                result = true;
                break;
            }
            if (result) {
                break;
            }
        }
        return result;
    }

    private boolean checkWinDiagonals(char playerSimbol) {
        boolean leftRight, rightLeft, result;

        leftRight = true;
        rightLeft = true;
        result = false;

        for (int i = 0; i < dimension; i++) {
            leftRight &= (gameField[i][i] == playerSimbol);
            rightLeft &= (gameField[dimension - i - 1][i] == playerSimbol);
        }
        if (rightLeft || leftRight) {
            result = true;
        }
        return result;
    }


    int[] getBestMove() {// добавим только поиск оптимального хода  по вертикали и горизонтали, если нет то любая пустая. По хорошему можно усложнить и добавить диагональ и блокировку победного хода противника.
        int x, y;

        char realPlayerSign = game.getRealPlayer().getPlayerSign();

        int[] arr = new int[2];
        arr[0] = -1;
        arr[1] = -1;

        int cellX = -1;
        int rowX = -1;
        int cellY = -1;
        int rowY = -1;


        for (int cell = 0; cell < dimension; cell++) {

            boolean cels = true;
            boolean rows = true;


            for (int row = 0; row < dimension; row++) {

                cels &= gameField[cell][row] != realPlayerSign; // если не вражеская клетка

                if ((gameField[cell][row] != realPlayerSign) && (gameField[cell][row] == nullSymbol)) {  // если пустая клетка сохраним для передачи
                    cellX = cell;
                    rowX = row;
                }
                rows &= gameField[row][cell] != realPlayerSign;
                if ((gameField[row][cell] != realPlayerSign) && (gameField[row][cell] == nullSymbol)) {  // если пустая клетка сохраним для передачи
                    cellY = row;
                    rowY = cell;
                }
            }
            if (cels != false || rows != false) {
                if ((cellX != -1) && (cels == true)) {
                    arr[0] = cellX;
                    arr[1] = rowX;
                    break;
                } else if ((cellY != -1) && (rows == true)) {
                    arr[0] = cellY;
                    arr[1] = rowY;
                    break;
                }
            }
        }
        if (arr[0] == -1) {
            Random rnd = new Random();
            do {

                x = rnd.nextInt(GameBoard.dimension);
                y = rnd.nextInt(GameBoard.dimension);
            } while (!isTurnable(x, y));
            arr[0] = y;
            arr[1] = x;
        }
        return arr;
    }
}
