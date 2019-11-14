package Game;

import java.util.Random;

public class GameAI {
    private char ai;
    private char player;
    private char empty;
    private char[][] gameMap;
    private int mapSize;
    private int[][] aiMap;
    private Random random = new Random();

    public GameAI(char[][] _gameMap, char _ai, char _player, char _empty){
        this.ai = _ai;
        this.empty = _empty;
        this.player = _player;
        this.gameMap = _gameMap;
        this.mapSize = this.gameMap.length;
        this.aiMap = new int[this.mapSize][this.mapSize];
    }

    public int[] getXY(){
        int[] coord = {0,0};
        //если по центру не занято то ставим туда
        if(this.gameMap[1 + (this.mapSize -1)%2][1 + (this.mapSize -1)%2] == this.empty){
            coord[0] = 1 + (this.mapSize - 1)%2;
            coord[1] = 1 + (this.mapSize -1)%2;
        }else {
            //обновляем массив данных ИИ
            this.aiRefreshMap();

            //ищим ячейки с наибольшим весом и считаем сколько их
            int maxCellWeight = 0;
            int cellsByMaxWeight = 0;
            for (int x = 0; x < this.mapSize; x++) {
                for (int y = 0; y < this.mapSize; y++) {
                    if (maxCellWeight < this.aiMap[x][y]) {
                        maxCellWeight = this.aiMap[x][y];
                        cellsByMaxWeight = 1;
                    } else if (maxCellWeight == this.aiMap[x][y]) {
                        cellsByMaxWeight++;
                    }
                }
            }

            //случайным образом выбираем из нескольких ячеек одну
            int count = (cellsByMaxWeight > 1) ? 1 + this.random.nextInt((cellsByMaxWeight - 1)) : 1;

            //ищим эту ячейку
            for (int i = 0; i < this.mapSize * this.mapSize; i++){
                coord[0] = i / this.mapSize;
                coord[1] = i % this.mapSize;
                if (count > 0 && this.aiMap[coord[0]][coord[1]] == maxCellWeight) {
                    count--;
                    if (count <=0){
                        break;
                    }
                }
            }
        }
        return coord;
    }


    private void aiRefreshMap(){
        //обходим все поле и определяем вес для каждой пустой клетки
        for (int x = 0; x < this.mapSize; x++) {
            for (int y = 0; y < this.mapSize; y++) {
                this.aiMap[x][y] = 0;
                if (this.gameMap[x][y] == this.empty) {
                    this.verifyCell(x, y);
                }
            }
        }
    }

    private void verifyCell( int x , int y){
        //проверяем по 3м направления по этому три
        int lineCount = 3;
        int[] cellWeight = new int[lineCount];
        int[] playerCells = new int[lineCount];
        int[] aiCells = new int[lineCount];

        for (int i = 0; i < this.mapSize; i++){
            //вертикаль
            if (this.gameMap[i][y] == this.ai){
                aiCells[0] = 1;
                cellWeight[0]++;
            }else if (this.gameMap[i][y] == this.player){
                playerCells[0] = 1;
                cellWeight[0]++;
            }
            //горизонталь
            if (this.gameMap[x][i] == this.ai){
                aiCells[1] = 1;
                cellWeight[1]++;
            }else if(this.gameMap[x][i] == this.player){
                playerCells[1] = 1;
                cellWeight[1]++;
            }

            //проверка диагоналей
            if(x == y){
                //прямая
                if (this.gameMap[i][i] == this.ai){
                    aiCells[2] = 1;
                    cellWeight[2]++;
                }else if(this.gameMap[i][i] == this.player){
                    playerCells[2] = 1;
                    cellWeight[2]++;
                }
            }else if (x == (mapSize - 1 - y)){
                //обратная
                if (this.gameMap[i][mapSize - i - 1] == this.ai){
                    aiCells[2] = 1;
                    cellWeight[2]++;
                }else if(this.gameMap[i][mapSize - i - 1] == this.player){
                    playerCells[2] = 1;
                    cellWeight[2]++;
                }
            }
        }

        //прибавляем вес только для тех клеток с которыми возможна комбинация.
        for (int i = 0; i < cellWeight.length; i++){
            if (!(aiCells[i] > 0 && playerCells[i] > 0)){
                aiMap[x][y] += cellWeight[i];
            }
        }

    }
}
