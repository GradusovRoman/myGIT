package sample;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

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
                this.buttonsArray[row][col].setStyle(this.getFont(this.buttonsArray[row][col]) + this.getColorByButton(this.buttonsArray[row][col]));
            }
        }
    }

    public Game getGame() {
        return this.game;
    }

    //TODO слишком муторно
    private String getFont(Button _button) {
        return String.format("-Fx-font: %s arial;", this.getNewFontSize(_button));
    }

    private double getNewFontSize(Button _button) {
        return (_button.getHeight()/this.game.getGameField().getMap().length);
    }

    private String getColorByButton(Button _button) {
        if (!_button.getText().equals("")) {
            return ("-fx-background-color:#" + getColorByInt(Integer.parseInt(_button.getText())) + ";");
        }
        return "";
    }

    private String getColorByInt(int _number) {
        int n = (16777215 / 2048); //16777215 это ffffff в 16 ричной системе
        String hexNumber = Integer.toHexString(n * (_number % 2048));
        while (hexNumber.length() < 6 ) {
            hexNumber = "0" + hexNumber;
        }
        return hexNumber;
    }
    //конец
}
