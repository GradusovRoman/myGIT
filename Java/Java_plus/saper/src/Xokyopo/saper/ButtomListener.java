package Xokyopo.saper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtomListener implements ActionListener {
    private GameCell gameCell;

    public ButtomListener(GameCell _gameCell){
        this.gameCell = _gameCell;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        int[] coord = this.gameCell.getCoord();
        boolean result = this.gameCell.getGameField().getGameGUI().getGameBoard().OpeningCell(coord[0] , coord[1]);
        this.gameCell.getGameField().refreshMap();
        if (!result){
            this.gameCell.getGameField().getGameGUI().gameOver();
        }
    }
}
