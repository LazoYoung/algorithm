import common.Problem;
import problems.ArrayFrequency;
import problems.ArraySort;
import problems.MaxSubArray;
import problems.MaxSubString;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        var problems = new ArrayList<Problem<?, ?>>();
        problems.add(new ArrayFrequency());
        problems.add(new ArraySort());
        problems.add(new MaxSubArray());
        problems.add(new MaxSubString());

        for (int i = 0; i < problems.size(); i++) {
            System.out.printf("#%d - %s%n", i + 1, problems.get(i).getName());
        }

        System.out.print("Select a problem: ");
        int idx = scanner.nextInt() - 1;
        problems.get(idx).execute();
        scanner.close();
    }
}