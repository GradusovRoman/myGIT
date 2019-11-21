package Xokyopo.hw_3.UniqueArrayCounter;

import java.util.HashMap;
import java.util.Map;

public class UniqueArrayCounter {
    private HashMap<String, Integer> arr = new HashMap<>();

    public UniqueArrayCounter(String... _arr) {
        this.addAll(_arr);
    }

    private void addAll(String... _arr) {
        for (int i = 0; i < _arr.length; i++) {
            this.arr.put(_arr[i] , (this.arr.get(_arr[i]) != null) ? this.arr.get(_arr[i]) + 1 : 1);
        }
    }

    public void printingArr(boolean _wihtRepitsCount) {
        System.out.println("Масссив:");
        for (Map.Entry arrElement: this.arr.entrySet()) {
            System.out.print(arrElement.getKey() + ((_wihtRepitsCount) ? "\tвстречалось : \t" + arrElement.getValue() + " раз(а) в исходном массиве\n":" "));
        }
        System.out.println("");
    }

}
