package org.xokyopo.massiveandsorting.masive;

@FunctionalInterface
public interface Sort {
    void sort(int[] arr, int leftIndex, int rightIndex);
}
