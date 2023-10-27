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

    public String getName() {
        return name;
    }

    public void execute() {
        System.out.printf("[%s]\n", name);
        I input = getBatchInput();

        if (input != null) {
            printInput(input);
        }

        for (var entry : algList.entrySet()) {
            var id = entry.getKey();
            var algorithm = entry.getValue();

            System.out.printf("#%d %s%n", id, algNames.get(id));

            if (getInput(id) != null) {
                input = getInput(id);
                printInput(input);
            }

            try {
                var startTime = System.nanoTime();
                O output = algorithm.compute(input);
                var elapsedTime = System.nanoTime() - startTime;

                System.out.printf("   Output: %s%n", output.toString());
                System.out.printf("   Elapsed time: %.1f ms%n", elapsedTime / Math.pow(10, 6));
            } catch (StackOverflowError e) {
                System.out.println("   Error: Stack overflow");
                e.printStackTrace(System.out);
            } catch (Exception e) {
                System.out.printf("   Error: %s%n", e.getMessage());
                e.printStackTrace(System.out);
            }
        }
        System.out.println();
    }

    protected I getBatchInput() {
        return null;
    }

    protected I getInput(int id) {
        return null;
    }

    protected void assign(int id, String name, Algorithm<I, O> algorithm) {
        algList.put(id, algorithm);
        algNames.put(id, name);
    }

    private void printInput(I input) {
        if (input.getClass().isArray()) {
            var string = new StringBuilder("[");

            for (int i = 0; i < Array.getLength(input); i++) {
                Object e = Array.get(input, i);
                string.append(e).append(',');
            }

            string.deleteCharAt(string.length() - 1).append(']');
            System.out.printf("   Input: %s%n", string);
        } else {
            System.out.printf("   Input: %s%n", input);
        }
    }

}
