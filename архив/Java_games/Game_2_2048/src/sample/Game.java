package sample;

public class Game {
    private GameField gameField;
    private  Player player = new Player();
    private int gameMapSize = 4;

    public Game() {
        gameField = new GameField(this.gameMapSize);
        this.initialize();
    }

    public void initialize() {
        this.gameField.cleaningMap();
        this.gameField.addNewNumberOnMap();
        this.gameField.addNewNumberOnMap();
    }

    public GameField getGameField(){
        return this.gameField;
    }

    public boolean isEndGame(){
        return this.gameField.isNoMoves();
    }
    //конец
}
