package Xokyopo.hw_2.exception;

public class MyArraySizeException extends Exception {
    //исключение некоректного размера массива
    public MyArraySizeException() {
        super("Некоректный размер массива");
    }
}
