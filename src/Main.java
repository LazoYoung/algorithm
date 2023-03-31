import common.Problem;
import problems.ArrayFrequency;
import problems.ArraySort;
import problems.MaxSubArray;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        var problems = new ArrayList<Problem<?, ?>>();
        problems.add(new ArrayFrequency());
        problems.add(new ArraySort());
        problems.add(new MaxSubArray());

        for (var e : problems) {
            e.execute();
        }
    }
}