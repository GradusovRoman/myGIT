package xokyopo.lamda;

/*
1.

Сделать методы для калькулятора (сложение двух чисел, деление и умножение) через лямбду

2.

Operationable operation = (int x, int y)-> {

    if(y==0)
        return 0;
    else
        return x/y;
};

нужно добивать обощение чтобы можно было использовать вот так

Operationable<Integer> operation1 = (x, y)-> x + y;
Operationable<String> operation2 = (x, y) -> x + y;
//TODO стринг поделить конечно сложно будет...
 */


public class Lambda {

    public static void main(String[] args) {
        Operationable<Integer> plus = (x, y) -> (x + y);
        Operationable<Integer> minus = ( x,  y) -> (x - y);
        Operationable<Integer> multiple = ( x,  y) -> (x * y);
        Operationable<Integer> division = ( x,  y) -> (y != 0)? (x * y) : 0;
    }

}
