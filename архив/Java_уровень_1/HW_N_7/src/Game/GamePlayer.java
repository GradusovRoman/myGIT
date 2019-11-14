package Game;

public class GamePlayer {
    private char playerSign;
    private boolean isRealPlayer;

    public GamePlayer(char _playerSign, boolean _isRealPlayer){
        this.playerSign = _playerSign;
        this.isRealPlayer = _isRealPlayer;
    }

    public boolean isRealPlayer(){
        return this.isRealPlayer;
    }

    public char getPlayerSign(){
        return this.playerSign;
    }
}
