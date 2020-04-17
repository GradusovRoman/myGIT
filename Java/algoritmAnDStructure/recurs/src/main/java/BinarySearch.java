
public class BinarySearch<T extends Comparable<T>> {
    public int binarySearch(T[] arr, T elementOfFind) {
        int left = 0;
        int right = arr.length;
        int middle;
        while (left <= right) {
            middle = (left + right)/2;
            if (arr[middle].compareTo(elementOfFind) == 0) {
               return middle;
            } if (arr[middle].compareTo(elementOfFind) > 0) {
                right = middle;
            } else {
                left = middle;
            }
        }
        return -1;
    }
}
