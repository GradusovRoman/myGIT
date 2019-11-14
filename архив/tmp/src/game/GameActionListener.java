package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameActionListener implements ActionListener {
    private int cell;
    private int row;
    private GameButton button;

    public GameActionListener(int row, int cell, GameButton gButton) {
        this.row = row;
        this.cell = cell;
        this.button = gButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GameBoard board = button.getBoard();

        if (board.isTurnable(row, cell)) {
          boolean playerWon = false;
           playerWon = updateByPlayersData(board);
            if (board.isFull()) {
                board.getGame().showMessage("Ничья");
                board.emptyField();
            } else if (!playerWon){ //добавили проверку на победу игрока перед ходом Аi. иначе даже если пользователь победил аи делает ход

                updateByAiDate(board);
            }
        } else {
            board.getGame().showMessage("Некорректный ход");
        }

    }

    private boolean updateByPlayersData(GameBoard board) {
        boolean playerWon = false;
        board.updateGameField(row, cell);
        button.setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSign()));

        if (board.checkWin()) {
            button.getBoard().getGame().showMessage(" you win");
            board.emptyField();
            playerWon = true;
        } else {
            board.getGame().passTurn();
        }
        return playerWon;
    }

    private void updateByAiDate(GameBoard board){
        int x,y;
        if (board.getGame().isHarmodeOn()){
           // если включен хардмоде добавим усложненый аи
           int[] arr = new int[2];
           arr = board.getBestMove();

               x = arr[1];
               y = arr[0];

        } else {
            Random rnd = new Random();
            do {
                x = rnd.nextInt(GameBoard.dimension);
                y = rnd.nextInt(GameBoard.dimension);
            } while (!board.isTurnable(x, y));
        }

        board.updateGameField(x, y);
        int cellIndex = GameBoard.dimension * x + y;
        board.getButton(cellIndex).setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSign()));

        if (board.checkWin()){
            button.getBoard().getGame().showMessage("computer win");
            board.emptyField();
        } else {
            board.getGame().passTurn();
        }
    }


}
