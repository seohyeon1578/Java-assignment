package binarySearch;

public class BinarySearch implements Searcher{

    @Override
    public int search(int[] sortedValues, int value){
        int start = 0;
        int end = sortedValues.length - 1;
        int mid;

        while(start <= end) {
            mid = (start + end) / 2;

            if(sortedValues[mid] == value) {
                return mid;
            } else if(sortedValues[mid] < value){
                start = mid + 1;
            }else if(sortedValues[mid] > value){
                end = mid - 1;
            }
        }

        return -1;
    }
}
