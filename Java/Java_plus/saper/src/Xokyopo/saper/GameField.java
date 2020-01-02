package Xokyopo.saper;

import javax.swing.*;
import java.awt.*;

public class GameField extends JPanel {
    private int mapSize;
    private GameGUI gameGUI;
    private GameCell[] buttonArray;

    public GameField(int _mapSize, GameGUI _gameGUI){
        this.mapSize = _mapSize;
        this.gameGUI = _gameGUI;
        this.setLayout(new GridLayout(this.mapSize, this.mapSize));
        this.creatingButtom();
        this.initialize();
    }

    public void initialize(){
        //TODO: подготавлиаем поле к игре
        this.enablingButtons();
    }

    private void enablingButtons(){
        //переводим все кнопки в активное состояние
        for (int i = 0 ; i < this.mapSize * this.mapSize ; i++){
            this.buttonArray[i].setText("");
            this.buttonArray[i].formatingCellByText();
            this.buttonArray[i].setEnabled(true);
        }
    }

    private void creatingButtom(){
        //создаем кнопки и ложим их в таблицу
        this.buttonArray = new GameCell[this.mapSize * this.mapSize];
        for (int i = 0 ; i < this.mapSize * this.mapSize ; i++){
            this.buttonArray[i] = new GameCell(i , this);
            this.add(this.buttonArray[i]);
        }
    }

    private void repaintingButtom(){
        //заполняем кнопки данными после согласно this.gameGUI.getGameBoard().getGameMap()
        for (int i = 0 ; i < this.mapSize * this.mapSize ; i++){
            if (this.gameGUI.getGameBoard().getGameMap()[i / this.mapSize][i % this.mapSize] != this.gameGUI.getGameBoard().getFREE()){
                String text ="";
                if(this.gameGUI.getGameBoard().getGameMap()[i / this.mapSize][i % this.mapSize] != this.gameGUI.getGameBoard().getEMPTY()){
                    text = "" + this.gameGUI.getGameBoard().getGameMap()[i / this.mapSize][i % this.mapSize];
                }
                this.buttonArray[i].setText(text);
                this.buttonArray[i].formatingCellByText();
                this.buttonArray[i].setEnabled(false);
            }
        }
    }

    public void refreshMap(){
        this.repaintingButtom();
    }

    public GameGUI getGameGUI(){
        return this.gameGUI;
    }
}
