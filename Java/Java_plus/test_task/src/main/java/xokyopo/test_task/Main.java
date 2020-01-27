package xokyopo.test_task;

public class Main {

    public static void main(String[] args) {
        int[][] map = new int[5][5];

        FillingMap fillingMap = new FillingMap(map, (int n) -> (n + 1), 1);

        fillingMap.fill();

        fillingMap.printMap();
    }
}
