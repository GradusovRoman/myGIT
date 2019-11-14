package Xokyopo.saper;

import javax.swing.*;
import java.awt.*;

public class GameCell extends JButton {
    private int number;
    private GameField gameField;
    private Color defaultBackgroundColor;
    private Color defaultTextColor;
    private char mine = '\u2738';
    private char alert = '\u2757';

    public GameCell(int _number , GameField _gameField){
        this.number = _number;
        this.gameField = _gameField;
        this.defaultBackgroundColor = this.getBackground();
        this.defaultTextColor = this.getForeground();
        this.addActionListener(new ButtomListener(this));
        this.addMouseListener(new ButtonRightClickListener(this));
    }

    public int[] getCoord(){
        return new int[] {this.number / this.gameField.getGameGUI().getGameBoard().getMAPSIZE() , this.number % this.gameField.getGameGUI().getGameBoard().getMAPSIZE()};
    }

    public GameField getGameField(){
        return this.gameField;
    }

    public void formatingCellByText(){
        String text = this.getText();
        Color textColor = this.defaultTextColor;
        Color buttonColor = this.defaultBackgroundColor;

        if (!text.equals("") && !text.equals(Character.toString(this.alert)) && !text.equals("-1")){
            buttonColor = this.getColorByInt(Integer.parseInt(text));
            textColor = Color.BLACK;
        }

        if(text.equals("-1")){
            this.setText(Character.toString(this.mine));
        }

        this.setForeground(textColor);
        this.setBackground(buttonColor);
    }

    private Color getColorByInt(int _type){
        Color result= Color.magenta;
        switch (_type){
            case 1 : result = Color.GREEN;
                break;
            case 2 : result = Color.ORANGE;
                break;
            case 3 : result = Color.BLUE;
                break;
            case 4 : result = Color.RED;
                break;
        }

        return result;
    }

    public char getAlert(){
        return this.alert;
    }
}
