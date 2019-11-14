package Xokyopo.saper;

import javax.swing.*;
import java.awt.*;

public class GameGUI extends JFrame {
    private final String TITLE = "САПЕР";
    private GameBoard gameBoard;
    private GameField gameField;


    public GameGUI(GameBoard _gameBoard){
        this.gameBoard = _gameBoard;
        this.gameField = new GameField(_gameBoard.getMAPSIZE(), this);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(500 , 500));
        this.setMinimumSize(new Dimension(500 , 500));

        this.setTitle(this.TITLE + " на " + this.gameBoard.getMINECOUNT() + " мин");
        this.getContentPane().add(this.gameField);

        this.setVisible(true);

    }

    public GameBoard getGameBoard(){
        return this.gameBoard;
    }

    private void showingEndingMessage(String _msg){
        //вывод сообщения о конце игры
        JOptionPane.showMessageDialog(this,_msg );

    }

    public void gameOver(){
        //запускается при конце игры, выводим сообщение и обновляем все данные
        this.showingEndingMessage(this.gameBoard.endingGame());
        this.gameBoard.initialize();
        this.gameField.initialize();
    }
}
