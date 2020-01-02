package Xokyopo.saper;

import java.util.Random;

public class GameBoard {
    private final int MAPSIZE = 10;
    private final int MINECOUNT = this.MAPSIZE * this.MAPSIZE * 15 / 100;
    private int[][] mineMap = new int[this.MAPSIZE][this.MAPSIZE];
    private int[][] gameMap = new int[this.MAPSIZE][this.MAPSIZE];
    private final int MINE = -1;
    private final int EMPTY = 0;
    private final int FREE = -2;
    private Random random = new Random();

    private GameGUI gameGUI;
    private Game game;

    public GameBoard(Game _game){
        this.game = _game;
        this.initialize();
        this.gameGUI = new GameGUI(this);

    }

    public void initialize(){
        //инициализируем игру, переменные, поля и так далее...
        this.initMineMap();
        this.initGameMap();

        for (int row = 0 ; row < this.MAPSIZE ; row++){
            for (int col = 0 ; col < this.MAPSIZE ; col++){
                System.out.print(this.mineMap[row][col] + "\t");
            }
            System.out.println("");
        }
    }

    private void initMineMap(){
        //инициализируем поле с минами
        this.inilializeArrayByKey(this.mineMap , this.EMPTY);
        this.generatingMineFild();
        this.initHintsinMineMap();
    }

    private void generatingMineFild(){
        //Заполняем минное поле минами в случайном порядке
        int[] coord = {0,0};
        for (int i = 1 ; i <= this.MINECOUNT ; i++){
            do{
                coord = this.getingRandomCoord();
            }while(this.mineMap[coord[0]][coord[1]] == this.MINE);
            this.mineMap[coord[0]][coord[1]] = this.MINE;
        }
    }

    private int[] getingRandomCoord(){
        //получаем случайные координаты
        return new int[] {this.random.nextInt(this.MAPSIZE), this.random.nextInt(this.MAPSIZE)};
    }

    private void initHintsinMineMap(){
        //добавляем подсказки на поле с минами
        //обходим поле и для каждой клетки считаем количество мин вогруг елси это не мина конечно
        for (int i = 0 ; i < this.MAPSIZE * this.MAPSIZE; i++){
            int row = i / this.MAPSIZE;
            int col = i % this.MAPSIZE;
            this.mineMap[row][col] = (this.mineMap[row][col] != this.MINE) ? this.countingMineAroundCell(row, col) : this.mineMap[row][col];
        }
    }

    private int countingMineAroundCell(int _row , int _col){
        //подсчитываем количество мин вокруг клетки
        int count = 0;
        for (int row = _row - 1 ; row <= _row + 1 ; row++){
            for (int col = _col - 1 ; col <= _col + 1 ; col++){
                count += (this.isCellOnMap(row , col) && this.mineMap[row][col] == this.MINE) ? 1 : 0;
            }
        }
        return count;
    }

    private boolean isCellOnMap(int _row, int _col){
        return (_row >= 0 && _row < this.MAPSIZE && _col >= 0 && _col < this.MAPSIZE);
    }
    private void inilializeArrayByKey(int[][] arr , int _key){
        for (int i = 0 ; i < this.MAPSIZE * this.MAPSIZE; i++){
            arr[i / this.MAPSIZE][i % this.MAPSIZE] = _key;
        }
    }

    private void initGameMap(){
        //инициализируем поле для игрока
        this.inilializeArrayByKey(this.gameMap, this.FREE);
    }

    public boolean OpeningCell(int _row , int _col){
        //Открываем клетку и проверяем на конец игры
        this.gameMap[_row][_col] = this.mineMap[_row][_col];
        return this.checkingCell(_row , _col) && !isEndGame();
    }

    private boolean checkingCell(int _row , int _col){
        //Проверка ячейки (мина, пусто или число)
        boolean result = true;
        if (mineMap[_row][_col] == this.MINE){
            result = false;
        } else if (mineMap[_row][_col] == this.EMPTY){
            //если пусто то открываем карту до ближайших чисел
            this.openEmptyAroundCell(_row , _col);
        }
        return result;
    }

    private void openEmptyAroundCell(int _row , int _col){
        //рекурсивная функция для открытия пустых зоне вокруг ячейки
        for (int row = _row - 1 ; row <= _row + 1 ; row++){
            for (int col = _col - 1 ; col <= _col + 1 ; col++){
                if (this.isCellOnMap(row , col) && this.mineMap[row][col] != this.MINE && this.gameMap[row][col] != this.mineMap[row][col]){
                    this.gameMap[row][col] = this.mineMap[row][col];
                    if (this.mineMap[row][col] == this.EMPTY){
                        this.openEmptyAroundCell(row , col);
                    }
                }
            }
        }
    }

    private boolean isEndGame(){
        //Проверка на конец игры, которое наступает когда мин столько же сколько не открытых ячеек на игровом поле
        return !(this.countingFreeCell() > this.MINECOUNT);
    }

    private int countingFreeCell(){
        //Считаем свободные клетки на поле дял игрока
        int count = 0;
        for (int i = 0 ; i < this.MAPSIZE * this.MAPSIZE ; i++ ){
            count += (this.gameMap[i / this.MAPSIZE][i % this.MAPSIZE] == this.FREE)? 1 : 0;
        }
        return count;
    }

    public String endingGame(){
        //игре конец, формируем сообщение окончания игры
        String msgLos = "Вы подорвались, ваша жизнь оборвалась....";
        String msgWin = "Вы победили, у вас остались две ноги и две руки";
        return (this.isEndGame()) ? msgWin : msgLos ;
    }


    public int[][] getGameMap(){
        return this.gameMap;
    }

    public int getMAPSIZE(){
        return this.MAPSIZE;
    }

    public int getFREE(){
        return this.FREE;
    }

    public int getEMPTY(){
        return this.EMPTY;
    }

    public int getMINECOUNT(){
        return this.MINECOUNT;
    }
    //конец
}
