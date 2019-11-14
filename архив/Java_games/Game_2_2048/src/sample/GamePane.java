package sample;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class GamePane extends GridPane {
    private Button[][] buttonsArray;
    private Game game = new Game();

    //TODO рисуем настраиваем кнопки в зависимости ор массива
    public GamePane( ){
        this.buttonsArray = new Button[this.game.getGameField().getMap().length][this.game.getGameField().getMap()[0].length];
        this.creatingButton();
    }

    private void creatingButton() {
        for (int row = 0 ; row < this.buttonsArray.length ; row++) {
            for (int col = 0 ; col < this.buttonsArray[0].length ; col++) {
                this.buttonsArray[row][col] = new Button("");
                this.buttonsArray[row][col].setPrefSize(Integer.MAX_VALUE , Integer.MAX_VALUE);
                this.buttonsArray[row][col].setFocusTraversable(false);
                this.add(this.buttonsArray[row][col] , col , row);
            }
        }
    }

    public void ubdateSourceByMap() {
        int[][] _map = this.game.getGameField().getMap();
        for (int row = 0 ; row < this.buttonsArray.length ; row++) {
            for (int col = 0 ; col < this.buttonsArray[0].length ; col++) {
                if (_map[row][col] > 0) {
                    this.buttonsArray[row][col].setText("" + _map[row][col]);
                }
                else {
                    this.buttonsArray[row][col].setText("");
                }
                this.buttonsArray[row][col].setStyle("-Fx-font:" + this.buttonsArray[row][col].getHeight()/this.game.getGameField().getMap().length + " arial;");
            }
        }
    }

    public Game getGame() {
        return this.game;
    }

    //TODO слишком муторно
    private String getColorByInt(int _number) {
        String color = "SILVER";
        if (_number % 32  == 0) {
            color = "PERU";
        }else if (_number % 16  == 0) {
            color = "ORANGERED";
        } else if (_number % 8  == 0) {
            color = "PURPLE";
        }  else if (_number % 6  == 0) {
            color = "VIOLET";
        }  else if (_number % 4  == 0) {
            color = "BLUE";
        }  else if (_number % 2  == 0) {
            color = "yellow";
        }
        return color;
    }
    //конец
}
