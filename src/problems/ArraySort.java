package problems;

import common.Problem;
import common.Algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Input: Unsorted integer list of size N
 * Output: Same list sorted in ascending order
 */
public class ArraySort extends Problem<List<Integer>, List<Integer>> {
    private final Algorithm<List<Integer>, List<Integer>> selectionSort = (input) -> {
        for (int i = 0; i < input.size() - 1; ++i) {
            int iv = input.get(i);
            int min = iv;
            int idx = i;

            for (int j = i + 1; j < input.size(); ++j) {
                int jv = input.get(j);

                if (jv < min) {
                    min = jv;
                    idx = j;
                }
            }

            input.set(i, min);
            input.set(idx, iv);
        }

        return input;
    };

    private final Algorithm<List<Integer>, List<Integer>> insertionSort = (input) -> {
        for (int i = 1; i < input.size(); ++i) {
            for (int j = i - 1; j > -1; --j) {
                int head = input.get(j);
                int tail = input.get(j + 1);

                if (head <= tail)
                    break;

                input.set(j, tail);
                input.set(j + 1, head);
            }
        }

        return input;
    };

    public ArraySort() {
        super("Array Sorting");
        assign(1, "Selection Sort", selectionSort);
        assign(2, "Insertion Sort", insertionSort);
    }

    @Override
    protected List<Integer> getBatchInput(int id) {
        int size = 10000;
        var list = new ArrayList<Integer>(size);

        switch (id) {
            case 1 -> {
                for (int i = 0; i < size; ++i) {
                    list.add(i + 1);
                }
                list.set(list.size() - 1, 0);
            }
            case 2 -> {
                for (int i = 0; i < size; ++i) {
                    list.add(size - i);
                }
            }
            default -> throw new IllegalArgumentException("Invalid id: " + id);
        }

        return list;
    }
}
