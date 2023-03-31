package common;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

public abstract class Problem<I, O> {

    private final Map<Integer, Algorithm<I, O>> algList = new HashMap<>();
    private final Map<Integer, String> algNames = new HashMap<>();
    private final String name;

    public Problem(String name) {
        this.name = name;
    }

    public void execute() {
        System.out.printf("[%s]\n", name);

        for (var entry : algList.entrySet()) {
            var id = entry.getKey();
            var algorithm = entry.getValue();

            var input = getInput(id);
            System.out.printf("#%d %s\n", id, algNames.get(id));

            if (input.getClass().isArray()) {
                var string = new StringBuilder("[");

                for (int i = 0; i < Array.getLength(input); i++) {
                    Object e = Array.get(input, i);
                    string.append(e).append(',');
                }

                string.deleteCharAt(string.length() - 1).append(']');
                System.out.printf("   Input: %s\n", string);
            } else {
                System.out.printf("   Input: %s\n", input);
            }

            var startTime = System.nanoTime();
            O output = algorithm.compute(input);
            var elapsedTime = System.nanoTime() - startTime;

            System.out.printf("   Output: %s\n", output.toString());
            System.out.printf("   Elapsed time: %.1f ms\n", elapsedTime / Math.pow(10, 6));
        }
        System.out.println();
    }

    protected abstract I getInput(int id);

    protected void assign(int id, String name, Algorithm<I, O> algorithm) {
        algList.put(id, algorithm);
        algNames.put(id, name);
    }

}
