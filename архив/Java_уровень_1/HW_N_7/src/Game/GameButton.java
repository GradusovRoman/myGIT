package Game;


import javax.swing.*;

public class GameButton extends JButton {
    private int buttonIndex;
    private GameBoard gameBoard;

    public GameButton(int _buttonIndex, GameBoard _gameBoard){
        this.buttonIndex = _buttonIndex;
        this.gameBoard = _gameBoard;
        this.addActionListener(new GameActionListener(this));
    }

    public GameBoard getBoard(){
        return this.gameBoard;
    }

    public int get_buttonIndex(){
        return this.buttonIndex;
    }
}
