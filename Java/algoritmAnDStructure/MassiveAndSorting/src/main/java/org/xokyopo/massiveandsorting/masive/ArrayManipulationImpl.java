package org.xokyopo.massiveandsorting.masive;

/*
Написать методы удаления, добавления, поиска элемента массива
 */
public interface ArrayManipulationImpl {
    int[] remove(int[] arr, int element);
    int[] add(int[] arr, int element);
    int findElement(int[] arr, int element);
}
