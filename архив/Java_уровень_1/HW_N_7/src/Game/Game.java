package Game;

import javax.swing.*;

public class Game {
    private GameBoard board;
    private GamePlayer[] game_player;
    private final int PCOUNT = 2;
    private int player_turn;
    private final char EMPTYCHAR = '\u0000';
    private final char PLAYERCHAR = 'X';
    private final char AICHAR = 'O';
    private boolean easyMode = true;

    public Game(){
        //конструктор класса
        this.initGame();
    }

    private void initGame(){
        //инициализация игры
        this.game_player = new GamePlayer[this.PCOUNT];
        this.player_turn = 0;
        this.game_player[0] = new GamePlayer(this.PLAYERCHAR, true);
        this.game_player[1] = new GamePlayer(this.AICHAR, false);

        this.board = new GameBoard(this);
        this.board.setVisible(true);
    }

    public void passTurn(){
        //передача хода
        this.player_turn = (player_turn == 0)? 1: 0;
    }

    public GamePlayer getCurentPlayer(){
        //получение текущего игрока
        return this.game_player[this.player_turn];
    }

    public void showMessage(String _msg){
        //вывод сообщения
        JOptionPane.showMessageDialog(this.board, _msg);
    }

    public char get_emptyChar(){
        return this.EMPTYCHAR;
    }

    public boolean get_mode(){
        return this.easyMode;
    }

    public void set_mode(boolean _easyMode){
        this.easyMode = _easyMode;
    }

    public void setPlayer_turn(int _i){
        this.player_turn = _i;
    }

    public char get_playerChar(){
        return this.PLAYERCHAR;
    }

    public char get_aiChar(){
        return this.AICHAR;
    }
}
