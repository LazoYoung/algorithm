package problems;

import common.Problem;
import common.Algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 입력: n개의 수로 구성된 배열
 * 출력: 가장 많이 등장하는 수
 */
public class ArrayFrequency extends Problem<List<Integer>, Integer> {

    // O(n^2)
    private final Algorithm<List<Integer>, Integer> traversal = input -> {
        int size = input.size();
        int digit = -1;
        int freq = 0;

        for (int i = 0; i < size - 1; ++i) {
            int x = input.get(i);
            int x_freq = 0;

            for (int j = 1; j < size; ++j) {
                if (input.get(j) == x) {
                    ++x_freq;
                }
            }

            if (x_freq > freq) {
                digit = x;
                freq = x_freq;
            }
        }

        return digit;
    };

    /**
     * [Time complexity]
     * If HashMap were used: O(n)
     * If TreeMap were used: O(log(n))
     */
    private final Algorithm<List<Integer>, Integer> mapping = input -> {
        var map = new HashMap<Integer, Integer>();

        for (int x : input) {
            int count = map.getOrDefault(x, 1);
            map.put(x, count);
        }

        var iter = map.entrySet().iterator();
        int freq = 0;
        int digit = -1;

        while (iter.hasNext()) {
            var next = iter.next();
            var key = next.getKey();
            var value = next.getValue();

            if (value > freq) {
                digit = key;
                freq = value;
            }
        }

        return digit;
    };

    public ArrayFrequency() {
        super("Most Repetitive Element");
        assign(1, "Selection Sort", traversal);
        assign(2, "Selection Sort", mapping);
    }

    @Override
    protected List<Integer> getBatchInput() {
        int size = 10000;
        var list = new ArrayList<Integer>();

        for (int i = 0; i < size; ++i) {
            list.add(size - i);
        }

        list.set(size - 1, 2);
        return list;
    }
}
