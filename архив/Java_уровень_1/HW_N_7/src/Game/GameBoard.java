package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoard extends JFrame {
    private final String title = "Игра в крестики нолики";
    private int mapSize = 3;
    private int cellsize = 150;
    private char[][] gameField;//нахер нужно вообще?
    private GameButton[] gameButton;
    private Game game;
    private int endCount;

    public GameBoard(Game _game){
        //конструктор класса
        this.game = _game;

        this.initField();
        this.emptyField();
        this.setLocationRelativeTo(null);
    }

    public void initField(){
        //Инициализация поля
        this.gameField = new char[this.mapSize][this.mapSize];
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(this.cellsize * this.mapSize, this.cellsize * this.mapSize));
        this.setPreferredSize(this.getPreferredSize());

        this.setLayout(new BorderLayout());
        this.setTitle(this.title);

        this.creatMenu();
        this.creatButton();
    }

    private void creatMenu(){
        JPanel menu = new JPanel();
        this.getContentPane().add(menu, BorderLayout.NORTH);

        JButton newGameButton = new JButton("Новая игра");
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                emptyField();
            }
        });
        menu.add(newGameButton, LEFT_ALIGNMENT);

        JCheckBox modeButton = new JCheckBox("Сильный АИ", !this.getGame().get_mode());
        modeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.set_mode(!game.get_mode());
            }
        });
        menu.add(modeButton, RIGHT_ALIGNMENT);
    }

    private void creatButton(){
        //делаем и размещаем кнопки на поле
        JPanel panel = new JPanel();
        this.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(this.mapSize, this.mapSize));

        this.gameButton = new GameButton[this.mapSize * this.mapSize];
        for (int i = 0; i < this.mapSize * this.mapSize; i++){
            this.gameButton[i] = new GameButton(i,this);
            panel.add(this.gameButton[i]);
        }
    }

    public Game getGame(){
        return this.game;
    }

    public void emptyField(){
        //очистка поля
        this.endCount = this.mapSize * this.mapSize;
        this.getGame().setPlayer_turn(0);
        this.cleaningButton();
    }

    public void cleaningButton(){
        for (int i = 0; i < this.gameButton.length; i++){
            this.gameField[i % this.mapSize][i / this.mapSize] = this.game.get_emptyChar();
            this.gameButton[i].setText(Character.toString(this.game.get_emptyChar()));
        }
    }

    public boolean isTurnable(int _x, int _y){
        //в клетку можно пойти
        return this.gameField[_x][_y] == this.game.get_emptyChar();
    }

    public void updateGameFiedl(int _x, int _y){
        //обновляем поле ходом
        this.gameField[_x][_y] = this.game.getCurentPlayer().getPlayerSign();
        this.gameButton[_x * this.mapSize + _y].setText(Character.toString(this.gameField[_x][_y]));
        this.endCount--;
    }

    public boolean checkWin(){
        //проверка победы
        boolean result = false;

        int ldCount = 0;
        int rdCount =0;
        char valid = this.getGame().getCurentPlayer().getPlayerSign();

        for (int x = 0; x < this.mapSize; x++){
            int hCount = 0;
            int vCount = 0;

            for (int y = 0; y < this.mapSize; y++) {
                //считаем количество совпадения по горизонтали и вертикали
                hCount = (this.gameField[x][y] == valid)? (hCount + 1): hCount;
                vCount = (this.gameField[y][x] == valid)? (vCount + 1): vCount;
            }

            //считаем количество одинаковых символов по горизонтали.
            ldCount = (this.gameField[x][x] == valid)? (ldCount + 1): ldCount;
            rdCount = (this.gameField[x][this.mapSize-x-1] == valid)? (rdCount + 1): rdCount;

            //если у нас есть комбинация то выходим.
            if (hCount == this.mapSize || vCount == this.mapSize || ldCount == this.mapSize || rdCount == this.mapSize){
                result = true;
                break;
            }
        }
        return result;
    }

    public boolean isFull(){
        return !(this.endCount > 0);
    }

    public int get_mapSize(){
        return this.mapSize;
    }

    public char[][] getGameField(){
        return this.gameField;
    }
    //конец
}
