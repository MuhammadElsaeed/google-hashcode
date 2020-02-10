package practice2020;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author muhammad
 */
public class InputScanner {

    private static Solver solver;
    private static final String FILE_NAME ="e_also_big";

    public static void main(String[] args) throws FileNotFoundException, IOException {
        int max, pizzaCount;
        Scanner scanner = new Scanner(InputScanner.class.getResourceAsStream("input/"+
                FILE_NAME + ".in"));

        max = scanner.nextInt();
        pizzaCount = scanner.nextInt();

        solver = new Solver(FILE_NAME, max, pizzaCount);

        for (int i = 0; i < pizzaCount; i++) {
            int size = scanner.nextInt();
            Pizza pizza = new Pizza(i, size);
            solver.addPizza(pizza);
        }
        solver.solve();
    }
}
