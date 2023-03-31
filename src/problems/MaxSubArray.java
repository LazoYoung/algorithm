package problems;

import common.Algorithm;
import common.Problem;

import java.util.Random;
import java.util.Scanner;

public class MaxSubArray extends Problem<Object, Integer> {

    private final Algorithm<Object, Integer> bruteForce = (input) -> {
        int[] arr = (int[]) input;
        int max = 0;

        for (int i = 0; i < arr.length - 1; i++) {
            int sum = arr[i];

            for (int j = i + 1; j < arr.length; j++) {
                sum += arr[j];
                max = Math.max(sum, max);
            }
        }

        return max;
    };

    private final Algorithm<Object, Integer> recursiveAlgo = (input) -> {
        int[] arr = (int[]) input;
        return recursive(arr, 0, false);
    };

    private final Algorithm<Object, Integer> memoization = (input) -> {
        int[] arr = (int[]) input;
        // todo method stub
        return -1;
    };

    public MaxSubArray() {
        super("Maximum Subarray");
        assign(1, "Brute Force", bruteForce);
        assign(2, "Recursive", recursiveAlgo);
        assign(3, "Memoization", memoization);
    }

    @Override
    protected Object getInput(int id) {
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Size of int array: ");
        int size = scanner.nextInt();
        int[] arr = new int[size];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(-9, 10);
        }

        return arr;
    }

    // Stackoverflow occurs at arr.length > 7000
    private int recursive(int[] arr, int i, boolean mustPick) {
        if (i >= arr.length) return 0;

        if (mustPick) {
            return Math.max(0, arr[i] + recursive(arr, i + 1, true));
        } else {
            return Math.max(recursive(arr, i + 1, false), arr[i] + recursive(arr, i + 1, true));
        }
    }
}
