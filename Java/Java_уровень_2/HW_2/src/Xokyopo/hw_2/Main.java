package Xokyopo.hw_2;

import Xokyopo.hw_2.exception.MyArrayDataException;
import Xokyopo.hw_2.exception.MyArraySizeException;

/*
1. Напишите метод, на вход которого подается двумерный строковый массив размером 4х4, при подаче массива другого размера необходимо бросить исключение MyArraySizeException.
2. Далее метод должен пройтись по всем элементам массива, преобразовать в int, и просуммировать. Если в каком-то элементе массива преобразование не удалось
(например, в ячейке лежит символ или текст вместо числа), должно быть брошено исключение MyArrayDataException – с детализацией, в какой именно ячейке лежат неверные данные.
3. В методе main() вызвать полученный метод, обработать возможные исключения MySizeArrayException и MyArrayDataException и вывести результат расчета.
 */
public class Main {
    private static String[][] arr = {
            {"1","2","3","4"},
            {"5","6","7","8"},
            {"9","10","11","12"},
            {"13","14","15","16"}
    };
    public static void main(String[] args) {
	// write your code here
        try {
            System.out.println(summ(arr));
        } catch (MyArraySizeException e) {
            e.printStackTrace();
        } catch (MyArrayDataException e) {
            e.printStackTrace();
        }
    }

    private static int summ(String[][] _arr) throws MyArraySizeException, MyArrayDataException {
        int result = 0 ;
        if (_arr.length != 4 || _arr[0].length != 4 || _arr[1].length != 4 || _arr[2].length != 4 || _arr[3].length != 4) {
            throw new MyArraySizeException();
        }

        for (int row = 0 ; row < 4 ; row++) {
            for (int col = 0 ; col < 4 ; col++) {
                try {
                    result += Integer.parseInt(_arr[row][col]);
                } catch (Exception e) {
                    throw new MyArrayDataException(row, col,_arr[row][col]);
                }
            }
        }
        return result;
    }

}
