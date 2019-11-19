package Xokyopo.hw_2.exception;

public class MyArrayDataException extends Exception {
    //исключение данных массива, создает сообщение с указанием ячейки и данных в ней.
    public MyArrayDataException(int _row, int _col, String _data) {
        super("Некоректные данные в ячейке [" + _row + "][" + _col + "] массива = " + _data);
    }
}
