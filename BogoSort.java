import java.util.Random;

public class BogoSort {
    static Random rand = new Random();

    public static void main(String[] args) {
        int[] array = fill_array(5, 0, 100);
        sort(array);
        System.out.println("The result is:");
        print_array(array);
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

    // fill array with random numbers form min to (max - 1)
    public static int[] fill_array(int length, int min, int max) {
        int[] result = new int[length];
        for (int idx = 0; idx < length; idx++) {
            result[idx] = (int)(Math.random() * (max - min)) + min;
        }
        return result;
    }

    public static boolean is_sorted(int[] array) {
        for (int idx = 0; idx < array.length - 1; idx++) {
            if (array[idx] > array[idx + 1])
                return false;
        }
        return true;
    }

    public static void shuffle_array(int[] array) {
        for (int idx = 0; idx < array.length; idx++) {
            int random_index_to_swap = rand.nextInt(array.length);
            int temp = array[random_index_to_swap];
            array[random_index_to_swap] = array[idx];
            array[idx] = temp;
        }
    }

    public static void sort(int[] array) {
        while (!is_sorted(array)) {
            print_array(array);
            shuffle_array(array);
        }
    }
}
