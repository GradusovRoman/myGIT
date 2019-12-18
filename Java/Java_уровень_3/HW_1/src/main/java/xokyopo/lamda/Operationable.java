package xokyopo.lamda;

@FunctionalInterface
public interface Operationable<T> {
    T doit(T x, T y);
}
