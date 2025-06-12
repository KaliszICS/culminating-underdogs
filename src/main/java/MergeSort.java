public class MergeSort implements SortAlgorithm {
    @Override
    public void sort(String[] arr) {
        if (arr.length < 2) {
            return;
        }
        int mid = arr.length/2;
        String[] left = new String[mid];
        String[] right = new String[arr.length - mid];
        for (int i = 0; i < mid; i++) {
            left[i] = arr[i];
        }
        for (int i = mid; i < arr.length; i++) {
            right[i - mid] = arr[i];
        }

        mergeSort(left);
        mergeSort(right);
        merge(left,right,arr);
    }

    private void mergeSort(String[] arr) {
        if (arr.length < 2) {
            return;
        }

        int mid = arr.length / 2;
        String[] left = new String[mid];
        String[] right = new String[arr.length - mid];

        for (int i = 0; i < mid; i++) {
            left[i] = arr[i];
        }

        for (int i = mid; i < arr.length; i++) {
            right[i - mid] = arr[i];
        }

        mergeSort(left);
        mergeSort(right);
        merge(left,right,arr);

    }

    private void merge(String[] left, String[] right, String[] arr) {
        int leftIndex = 0, rightIndex = 0, ogIndex = 0;
        
        while (leftIndex < left.length && rightIndex < right.length) {
              if (left[leftIndex].compareTo(right[rightIndex]) <= 0) {
                arr[ogIndex++] = left[leftIndex++];
            }
            else {
                arr[ogIndex++] = right[rightIndex++];
            }
        }
        while (leftIndex < left.length) { 
            arr[ogIndex++] = left[leftIndex++];
        }
        while (rightIndex < right.length) {
            arr[ogIndex++] = right[rightIndex++];
        }
    }
}