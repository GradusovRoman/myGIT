package game;

import javax.swing.*;

public class Game {

    private GameBoard board;
    private GamePlayer[] gamePlayers = new GamePlayer[2];
    private int playersTurn = 0;
    private boolean hardMode = false;

    public Game(){
        this.board = new GameBoard(this);
    }
    public void initGame(){
        gamePlayers[0] = new GamePlayer(true, 'X');
        gamePlayers[1] = new GamePlayer(false, '0');
    }

    void passTurn(){
        if (playersTurn == 0){
            playersTurn = 1;
        } else {
            playersTurn = 0;
        }
    }
    void setFirstTurn(){
        playersTurn = 0;
    }
    void setHardMode(boolean hardModeOn){hardMode = hardModeOn;}// добавил кнопочку с хардмодом

    GamePlayer getCurrentPlayer(){return gamePlayers[playersTurn];}

    GamePlayer getRealPlayer(){return gamePlayers[0];}

    boolean isHarmodeOn(){return hardMode;}

    void showMessage(String messageText){
        JOptionPane.showMessageDialog(board,messageText);
    }
}
