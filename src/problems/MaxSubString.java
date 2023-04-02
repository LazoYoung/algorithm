package problems;

import common.Algorithm;
import common.Problem;

import java.util.*;

public class MaxSubString extends Problem<Map<String, Integer>, List<Integer>> {
    private final int FREQ_SIZE = 'z' - 'a' + 1;
    private final Algorithm<Map<String, Integer>, List<Integer>> algo = (map) -> {
        var iter = map.entrySet().iterator();
        var ret = new ArrayList<Integer>();

        while (iter.hasNext()) {
            Map.Entry<String, Integer> entry = iter.next();
            String str = entry.getKey();
            int k = entry.getValue();
            int result = divide(str.toCharArray(), 0, str.length() - 1, k);
            ret.add(result);
        }

        return ret;
    };

    public MaxSubString() {
        super("Maximum Substring");
        assign(1, "Divide and Conquer", algo);
    }

    @Override
    protected Map<String, Integer> getBatchInput() {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        System.out.print("Number of input: ");
        int num = scanner.nextInt();
        System.out.print("Length of each string: ");
        int len = scanner.nextInt();

        Map<String, Integer> map = new HashMap<>();
        int cSize = 'z' - 'a' + 1;

        for (int i = 0; i < num; i++) {
            var builder = new StringBuilder();
            int k = rand.nextInt(Math.max(2, len / 10));

            for (int j = 0; j < len; j++) {
                char c = (char) ('a' + rand.nextInt(cSize));
                builder.append(c);
            }
            map.put(builder.toString(), k);
        }

        return map;
    }

    private int divide(char[] string, int from, int to, int k) {
        if (k > to - from + 1) return 0;

        int[] freq = new int[FREQ_SIZE];
        int ans = 0;

        // Compute frequency of each character in range: [from, to]
        for (int i = from; i <= to; i++) {
            int idx = string[i] - 'a';
            freq[idx]++;
        }

        for (int i = from; i <= to; i++) {
            if (freq[string[i] - 'a'] < k) {
                // Split string over i-th index to divide and conquer.
                // The i-th character cannot be a part of any substring
                // since it repeats itself less than k times.
                int left = divide(string, from, i - 1, k);
                int right = divide(string, i + 1, to, k);
                return Math.max(ans, Math.max(left, right));
            }
        }

        return to - from + 1;
    }
}
