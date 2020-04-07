package org.xokyopo.massiveandsorting.masive;

public class ArraySorter {

    private void swap(int[] arr, int firstIndex, int secondIndex) {
        int buffer = arr[firstIndex];
        arr[firstIndex] = arr[secondIndex];
        arr[secondIndex] = buffer;
    }

    public void selectSort(int[] arr, int leftIndex, int rightIndex) {
        for (int i = leftIndex; i <= rightIndex - 1; i++ ) {
            int markedIndex = i;
            for (int count = i + 1 ; count <= rightIndex; count++) {
                if (arr[count] < arr[markedIndex]) markedIndex = count;
            }
            swap(arr, i, markedIndex);
        }
    }

    public void classicSort(int[] arr, int leftIndex, int rightIndex) {
        for (int i = leftIndex ; i <= rightIndex; i++) {
            for (int count = rightIndex; count > i; count--) {
                if (arr[count] < arr[count - 1]) this.swap(arr, count, count - 1);
            }
        }
    }

    public void insertSort(int[] arr, int leftIndex, int rightIndex) {
        for (int i = leftIndex + 1 ; i <= rightIndex; i++) {
            for (int count = i; count > leftIndex && arr[count - 1] > arr[count]; count--) {
                this.swap(arr, count, count - 1);
            }
        }

    }

    public void margeSort(int[] arr, int leftIndex, int rightIndex) {
        if (leftIndex < rightIndex) {
            int middleIndex = (rightIndex + leftIndex) / 2;
            this.margeSort(arr, leftIndex, middleIndex);
            this.margeSort(arr, middleIndex + 1, rightIndex);
            this.mergingForMargeSort(arr, leftIndex, middleIndex, rightIndex);
        }
    }

    //данный алгорит я писал для тестового задания для устройства на работу. по этому скопировал его оттуда для проверки.
    //так как были ограничения заложенные в изначальном коде он не совсем отражает поведение класической сортировки слиянием
    //но все же он неплохо работает как я думаю.
    //одним из ограничений было то что невозможно было передавать массив обратно в сетод, по этому пришлось изголяться.
    //так же я подумал что если массив будет большого размера то будет не целесообразно перемещать сначала в него элементы,
    //а потом в тот что мы получили на входе, а рациональный выполнеть вставку нужного эелемента в отсортированный массив.
    //считаю данный подход лучшим из того что можно было тут сделать.
    private void mergingForMargeSort(int[] arr, int leftIndex, int middleIndex, int LeftIndex) {
        int leftSeed = leftIndex;
        int rightSeed = middleIndex + 1;

        while (leftSeed <= middleIndex) {
            if (arr[leftSeed] > arr[rightSeed]) {
                this.swap(arr, leftSeed, rightSeed);

                for (int i = rightSeed ; i < LeftIndex  && arr[i] > arr[i + 1]; i++) {
                        this.swap(arr, i, i + 1);
                }
            }
            leftSeed++;
        }
    }

    public void quickSort(int[] arr, int leftIndex, int rightIndex) {
        if (leftIndex < rightIndex) {
            int middle = (rightIndex + leftIndex) /2;
            int quickSortMarker = arr[middle]; //должно быть неизменяемым значени в функии. так как элементы перемещаются.

            int leftSeed = leftIndex;
            int rightSeed = rightIndex;

            while (leftSeed <= rightSeed) {

                while (arr[leftSeed] < quickSortMarker) {
                    leftSeed++;
                }

                while (arr[rightSeed] > quickSortMarker) {
                    rightSeed--;
                }

                if (leftSeed <= rightSeed) {
                    swap(arr, leftSeed, rightSeed);
                    leftSeed++;
                    rightSeed--;
                }
            }

            if (leftIndex < rightSeed) {
                this.quickSort(arr, leftIndex, rightSeed);
            }

            if (rightIndex > leftSeed) {
                this.quickSort(arr, leftSeed, rightIndex);
            }

        }
    }

}
