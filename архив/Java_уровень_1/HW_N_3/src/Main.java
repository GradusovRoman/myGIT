import java.util.Random;
import java.util.Scanner;

/*
1. Полностью разобраться с кодом, попробовать переписать с нуля, стараясь не подглядывать;
2. Реализовать логику более умного компьютера, который определяет свой ход на основании соседних клеток;
3. Найти в коде неоптимальные места и улучшить их;
4. *Усилить логику алгоритмом с подсчётом очков для каждой клетки.
*/
public class Main{

    private static char[][] map;
    private static int mapSize = 3;
    private static char emptyDot = '*';
    private static char playerDot = 'X';
    private static char aiDot = 'O';
    private static Scanner scanner = new Scanner(System.in);
    private static int emptyStep = 0 ;
    private static boolean easyMode = true;
    private static boolean playerTurn = true;
    private static Random random = new Random();
    private static String acceptText = "Yes";
    private static int[][] aiMap;

    public static void main(String[] arg){
        System.out.print("Это игра крестики нолики вы играете: " + playerDot + "\n для выбора экспертного режима наберите \"Yes\" : ");
        String s = scanner.nextLine();
        easyMode = !s.equals(acceptText);

        genMap();
        showMap();

        while (!isEndGame()){
            if (playerTurn) {
                getPlayerInput();
                playerTurn = false;
            } else {
                getAiInput();
                playerTurn = true;
            }
            showMap();
            emptyStep--;
        }

    }

    static void genMap(){
        aiMap = new int[mapSize][mapSize];
        if (emptyStep == 0){
            map = new char[mapSize][mapSize];
            for (char[] l :map){
                for (int i = 0; i < l.length; i++){
                    l[i] = emptyDot;
                }
            }
            emptyStep = mapSize * mapSize;
        }
    }

    static void showMap(){
            System.out.println("");
            System.out.print(" \t");
        for (int i = 1; i <= mapSize; i++){
            System.out.print(i + "\t");
        }
        System.out.println("");
        for (int x = 0; x < mapSize; x++){
            System.out.print((x + 1) + "\t");
            for (int y = 0; y < mapSize; y++){
                System.out.print(map[x][y] + "\t");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    static boolean isEndGame(){
        boolean result = false;
        String name = (playerTurn)? "Компьютер" : "Игрок";
        char valid = (playerTurn)? aiDot : playerDot;

        if (emptyStep > 0){
            int ldCount = 0;
            int rdCount =0;

            for (int x = 0; x < mapSize; x++){
                int hCount = 0;
                int vCount = 0;
                for (int y = 0; y < mapSize; y++) {
                    hCount = (map[x][y] == valid)? (hCount + 1): hCount;
                    vCount = (map[y][x] == valid)? (vCount + 1): vCount;
                    ldCount = (map[x][y] == valid && x == y)? (ldCount + 1): ldCount;
                    rdCount = (map[x][mapSize-y-1] == valid && x == y)? (rdCount + 1): rdCount;
                }
                if (hCount == mapSize || vCount == mapSize || ldCount == mapSize || rdCount == mapSize){
                    result = true;
                    System.out.println("\n У нас есть победитель и это: " + name);
                    break;
                }
            }
        }else{
            System.out.println("\nВ этой партии нет победителя.");
            result = true;
        }
        return result;
    }

    static void getPlayerInput(){
        int x = 0;
        int y = 0;

        String msg = "Введите координаты x  и  y  ( от 1 до " + mapSize + " ) через пробел: ";
        String msgError = "Вы ввели некоректные координаты повторите ввод";
        String msgNoEmpty = "Вы пытаететь сходить в занятую ячейку";
        String msgTurn = "Вы сходили по координатам ";

        do{
            System.out.print(msg);
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
            if (!(x >= 0 && x < mapSize && y >= 0 && y < mapSize)){
                System.out.println(msgError);
            } else if (map[x][y] != emptyDot){
                System.out.println(msgNoEmpty);
            }else {
                break;
            }
        }while(true);

        map[x][y] = playerDot;
        System.out.println(msgTurn + (x + 1) + " " + (y + 1) );
    }

    static void getAiInput(){
        int[] coord = {0,0};

        do{
            aiMind(coord);
        }while(map[coord[0]][coord[1]] != emptyDot);


        map[coord[0]][coord[1]] = aiDot;
        System.out.println("Компьютер сходи по координатам: " + (coord[0] + 1) + " " + (coord[1] + 1));
    }

    static void aiMind(int[] coord){
        coord[0] = random.nextInt(mapSize);
        coord[1] = random.nextInt(mapSize);
        if (!easyMode){
            if(emptyStep >= (mapSize*mapSize - 1) && map[1 + (mapSize -1)%2][1 + (mapSize -1)%2] == emptyDot){
                coord[0] = 1 + (mapSize - 1)%2;
                coord[1] = 1 + (mapSize -1)%2;
            }else {
                for (int x = 0; x < mapSize; x++) {
                    for (int y = 0; y < mapSize; y++) {
                        aiMap[x][y] = 0;
                        if (map[x][y] == emptyDot) {
                            aiRefreshMap(x, y);
                        }
                    }
                }

                int maxCellWeight = 0;
                int cellsByMaxWeight = 0;
                for (int x = 0; x < mapSize; x++) {
                    for (int y = 0; y < mapSize; y++) {
                        if (maxCellWeight < aiMap[x][y]) {
                            maxCellWeight = aiMap[x][y];
                            cellsByMaxWeight = 1;
                        } else if (maxCellWeight == aiMap[x][y]) {
                            cellsByMaxWeight++;
                        }
                    }
                }

                int count = (cellsByMaxWeight > 1) ? 1 + random.nextInt((cellsByMaxWeight - 1)) : 1;

                for (int x = 0; x < mapSize; x++) {
                    for (int y = 0; y < mapSize; y++) {
                        if (count > 0 && aiMap[x][y] == maxCellWeight) {
                            coord[0] = x;
                            coord[1] = y;
                            count--;
                        }
                    }
                }
            }
        }
    }

    static void aiRefreshMap( int x , int y){
        int[] axis = {x,y};
        int[] cellWeight = new int[3];
        int[] playerCells = new int[3];
        int[] aiCells = new int[3];

        for (int i = 0; i < mapSize; i++){
            if (map[i][y] == aiDot){
                aiCells[0] = 1;
                cellWeight[0]++;
            }else if (map[i][y] == playerDot){
                playerCells[0] = 1;
                cellWeight[0]++;
            }
            if (map[x][i] == aiDot){
                aiCells[1] = 1;
                cellWeight[1]++;
            }else if(map[x][i] == playerDot){
                playerCells[1] = 1;
                cellWeight[1]++;
            }
            if(x == y){
                if (map[i][i] == aiDot){
                    aiCells[2] = 1;
                    cellWeight[2]++;
                }else if(map[i][i] == playerDot){
                    playerCells[2] = 1;
                    cellWeight[2]++;
                }
            }else if (x == (mapSize - 1 - y)){
                if (map[i][mapSize - i - 1] == aiDot){
                    aiCells[2] = 1;
                    cellWeight[2]++;
                }else if(map[i][mapSize - i - 1] == playerDot){
                    playerCells[2] = 1;
                    cellWeight[2]++;
                }
            }
        }

        for (int i = 0; i < cellWeight.length; i++){
            if (!(aiCells[i] > 0 && playerCells[i] > 0)){
                aiMap[x][y] += cellWeight[i];
            }
        }

    }
}
