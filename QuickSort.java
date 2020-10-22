import java.lang.Math;
import java.util.Arrays;
import java.util.Scanner;

public class QuickSort {
    static Scanner reader = new Scanner(System.in);
    static boolean demo = false;
    public static void main(String[] args) {
        int[] array = fill_array(1000, 0, 10000);
        sort(array);

        System.out.println("The result is:");
        print_array(array);
    }

    // fill array with random numbers form min to (max - 1)
    public static int[] fill_array(int length, int min, int max) {
        int[] result = new int[length];
        for (int idx = 0; idx < length; idx++) {
            result[idx] = (int) (Math.random() * (max - min)) +min;
        }
        return result;
    }

    public static void swap_elements(int[] array, int first_idx, int second_idx) {
        print_swap(array, first_idx, second_idx);
        int buffer = array[second_idx];
        array[second_idx] = array[first_idx];
        array[first_idx] = buffer;
    }

    // recursive function
    public static int[] sort(int[] array) {
        System.out.println("Now sorting:");
        print_array(array);
        System.out.println();
        if (array.length <= 1) {
            print_array(array);
            System.out.println("------------------------------layer done------------------------------");
            return array;
        }
        // select and move pivot
        int pivot_idx = get_pivot_idx(array);
        // three or less already got sorted by get_pivot_idx
        if (array.length <= 3) {
            print_array(array);
            System.out.println("------------------------------layer done------------------------------");
            return array;
        }
        System.out.print("    Swap pivot with the last element, ");
        swap_elements(array, pivot_idx, array.length - 1);
        pivot_idx = array.length - 1;
        // move items from left and items form right
        while (true) {
            int idx_from_left = get_idx_from_left(array, array[pivot_idx]);
            int idx_from_right = get_idx_from_right(array, array[pivot_idx]);
            if (idx_from_left == -1) {
                System.out.println("### The pivot is the largest element.");
                break;
            }
            if (idx_from_right == -1) {
                System.out.println("### The pivot is the smallest element.");
                System.out.print("Swap pivot with the first element, ");
                swap_elements(array, 0, pivot_idx);
                pivot_idx = 0;
                break;
            }
            System.out.println("### Current element from the left and right:");
            print_elements(array, new int[]{idx_from_left, idx_from_right});

            if (idx_from_left > idx_from_right) {
                System.out.println("The index of the item from the left is larger than from the item from the right.");
                System.out.print("Swapping item from the left with the pivot, ");
                swap_elements(array, idx_from_left, pivot_idx);
                pivot_idx = idx_from_left;
                break;
            }
            if (array[idx_from_left] > array[idx_from_right]) {
                System.out.print("The item from the left is larger than the item form the right, ");
                swap_elements(array, idx_from_left, idx_from_right);
            } else {
                System.out.println("No changes needed.");
            }
        }

        print_array(array);
        System.out.println("------------------------------layer done------------------------------");

        // sort part before and after pivot
        int[] beginning = sort(Arrays.copyOfRange(array, 0, pivot_idx));
        int[] ending = sort(Arrays.copyOfRange(array, pivot_idx + 1, array.length));

        // overwrite beginning and ending of array
        System.arraycopy(beginning, 0, array, 0, beginning.length);
        System.arraycopy(ending, 0, array, beginning.length + 1, ending.length);

        return array;
    }

    // using median of three
    public static int get_pivot_idx(int[] array) {
        System.out.println("@@@ Sorting first, middle and last element and choosing pivot:");
        int first_idx = 0;
        int middle_idx = array.length / 2;
        int last_idx = array.length - 1;

        if (array[first_idx] > array[middle_idx])
            swap_elements(array, first_idx, middle_idx);
        if (array[first_idx] > array[last_idx])
            swap_elements(array, first_idx, last_idx);
        if (array[middle_idx] > array[last_idx])
            swap_elements(array, middle_idx, last_idx);
        return middle_idx;
    }

    // get first element from the left that's larger than pivot
    public static int get_idx_from_left(int[] array, int pivot) {
        for (int idx = 0; idx < array.length; idx++) {
            if (array[idx] > pivot)
                return idx;
        }
        return -1;
    }

    // get first element from the right that's smaller than pivot
    public static int get_idx_from_right(int[] array, int pivot) {
        for (int idx = array.length - 1; idx > -1; idx--) {
            if (array[idx] < pivot)
                return idx;
        }
        return -1;
    }

    public static void print_array(int[] array) {
        for (int idx = 0; idx < array.length; idx++) {
            // when this is the first element, no delimiter needed
            if (idx != 0)
                System.out.print(", ");
            System.out.print(array[idx]);
        }
        System.out.println();
    }

    public static void print_swap(int[] array, int first_idx, int second_idx) {
        System.out.print("swapping " + (first_idx + 1) + ". element and " + (second_idx + 1) + ". element:");
        // wait for input
        if (demo)
            reader.nextLine();
        else
            System.out.println();
        print_array(array);

        for (int idx = 0; idx < array.length; idx++) {
            // when this is the first element, no delimiter needed
            if (idx != 0) {
                // when this element is between first and second
                if (idx > first_idx && idx < second_idx + 1)
                    System.out.print("--");
                else
                    System.out.print("  ");
            }

            // first or second
            if (idx == first_idx || idx == second_idx)
                System.out.print("^".repeat(String.valueOf(array[idx]).length()));
            // in between
            else if (idx > first_idx && idx < second_idx + 1)
                System.out.print("-".repeat(String.valueOf(array[idx]).length()));
            else
                System.out.print(" ".repeat(String.valueOf(array[idx]).length()));
        }
        System.out.println();
        System.out.println();
    }

    public static void print_elements(int[] array, int[] elements_idx) {
        print_array(array);
        for (int idx = 0; idx < array.length; idx++) {
            // delimiter
            if (idx != 0)
                System.out.print("  ");

            boolean is_element = false;
            for (int element : elements_idx) {
                if (element == idx) {
                    is_element = true;
                    break;
                }
            }
            if (is_element)
                System.out.print("^".repeat(String.valueOf(array[idx]).length()));
            else
                System.out.print(" ".repeat(String.valueOf(array[idx]).length()));
        }
        System.out.println();
    }
}
