package problems;

import common.Algorithm;
import common.Problem;

import java.util.Arrays;
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
        return solveRecursively(arr, 0, false);
    };

    private final Algorithm<Object, Integer> memoization = (input) -> {
        int[] arr = (int[]) input;
        int[][] mem = new int[2][arr.length];
        Arrays.fill(mem[0], -1);
        Arrays.fill(mem[1], -1);
        return solveMemoization(arr, mem, 0, 0);
    };

    private final Algorithm<Object, Integer> kadane = (input) -> solveKadane((int[]) input);

    private final Algorithm<Object, Integer> daq = (input) -> {
        int[] arr = (int[]) input;
        return divideAndConquer(arr, 0, arr.length - 1);
    };

    public MaxSubArray() {
        super("Maximum Subarray");
        assign(1, "Brute Force", bruteForce);
        assign(2, "Recursive", recursiveAlgo);
        assign(3, "Memoization", memoization);
        assign(4, "Kadane Algorithm", kadane);
        assign(5, "Divide And Conquer", daq);
    }

    @Override
    protected Object getBatchInput() {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("Size of int array: ");
        int size = scanner.nextInt();
        int[] arr = new int[size];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(-9, 10);
        }

        return arr;
    }

    // Stackoverflow at arr.length > 7000
    private int solveRecursively(int[] arr, int i, boolean mustPick) {
        if (i >= arr.length) return 0;

        if (mustPick) {
            return Math.max(0, arr[i] + solveRecursively(arr, i + 1, true));
        } else {
            return Math.max(solveRecursively(arr, i + 1, false), arr[i] + solveRecursively(arr, i + 1, true));
        }
    }

    // Stackoverflow at arr.length > 5000
    private int solveMemoization(int[] arr, int[][] mem, int i, int mustPick) {
        if (i >= arr.length) return 0;
        if (mem[mustPick][i] > -1) return mem[mustPick][i];

        if (mustPick == 1) {
            mem[mustPick][i] = Math.max(0, arr[i] + solveMemoization(arr, mem, i + 1, 1));
        } else {
            mem[mustPick][i] = Math.max(solveMemoization(arr, mem, i + 1, 0), arr[i] + solveMemoization(arr, mem, i + 1, 1));
        }

        return mem[mustPick][i];
    }
    
    private int solveKadane(int[] arr) {
        int curMax = 0;
        int max = Integer.MIN_VALUE;

        for (int num : arr) {
            curMax = Math.max(num, curMax + num);
            max = Math.max(max, curMax);
        }

        return max;
    }

    private int divideAndConquer(int[] arr, int L, int R) {
        if (L > R) return Integer.MIN_VALUE;

        int mid = (L + R) / 2;
        int lSum = 0;
        int rSum = 0;

        for (int i = mid - 1, curSum = 0; i >= L; i--) {
            curSum += arr[i];
            lSum = Math.max(lSum, curSum);
        }

        for (int i = mid + 1, curSum = 0; i <= R; i++) {
            curSum += arr[i];
            rSum = Math.max(rSum, curSum);
        }

        int max1 = Math.max(divideAndConquer(arr, L, mid - 1), divideAndConquer(arr, mid + 1, R));
        return Math.max(max1, lSum + arr[mid] + rSum);
    }
}
