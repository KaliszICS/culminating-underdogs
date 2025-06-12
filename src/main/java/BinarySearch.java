public class BinarySearch implements SearchAlgorithm {
    @Override
    public int search(String[] list, String word) {
        int low = 0;
        int high = list.length - 1;

        while (low <= high) {
            int mid = (low + high)/2;
            int compare = list[mid].compareTo(word);

            if (compare == 0) {
                return mid;
            }
            else if (compare < 0) {
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }
        return -1;
    }
}