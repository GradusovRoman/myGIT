package org.xokyopo.massiveandsorting.matrix;

import org.xokyopo.massiveandsorting.matrix.interfaces.MatrixImpl;

public class FillingRect implements MatrixImpl<Integer> {
    private Integer[][] map;

    @Override
    public Integer[][] generatingMatrix(Integer[] arraysT, int height, int width) {
        this.init(height, width);
        this.fillingMatrix(arraysT);
        return this.map;
    }

    private void init(int height, int width) {
        this.map = new Integer[height][width];
    }

    private void fillingMatrix(Integer[] arr) {
        boolean[][] fillMask = new boolean[this.map.length][this.map[0].length];
        int currentHeight = 0;
        int currentWidth = 0;
        boolean mirroring = false;
        int pointCount = this.map.length * this.map[0].length;

        for (int i = 0; i < pointCount; i++) {
            this.map[currentHeight][currentWidth] = arr[i%arr.length];
            fillMask[currentHeight][currentWidth] = true;

            if (isValidCoordinate(fillMask, currentHeight, currentWidth + (mirroring? -1 : 1))) {
                currentWidth +=(mirroring)? -1 : 1;
            } else if (isValidCoordinate(fillMask, currentHeight + (mirroring? -1 : 1), currentWidth)) {
                currentHeight +=(mirroring)? -1 : 1;
            } else {
                mirroring = !mirroring;
                currentWidth += (mirroring)? -1 : 1;
            }
        }
    }

    private boolean isValidCoordinate(boolean[][] arr, int height, int width) {
        return this.isInside(arr, height, width) && !(arr[height][width]);
    }

    private boolean isInside(boolean[][] arr, int height, int width) {
        return (height < arr.length &&
                height >= 0 &&
                width < arr[0].length &&
                width >= 0);
    }
}
