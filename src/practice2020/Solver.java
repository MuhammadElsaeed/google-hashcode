package practice2020;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;

/**
 *
 * @author muhammad
 */
public class Solver {

    private final String FILE_NAME;
    private final int MAX;
    private final int pizzaCount;

    private final ArrayList<Pizza> pizzas = new ArrayList<>();

    private final ArrayList<Pizza> selectedPizzas = new ArrayList<>();
    private int score = 0;

    private int sum = 0;
    private int avgNumberOfPizzas = 0;

    private Random random = new Random();

    public Solver(String FILE_NAME, int MAX, int pizzaCount) {
        this.FILE_NAME = FILE_NAME;
        this.MAX = MAX;
        this.pizzaCount = pizzaCount;
    }

    public void solve() throws IOException {
        int avg = sum / pizzaCount;
        avgNumberOfPizzas = MAX / avg;
        float pizzaPercetage = (float) avgNumberOfPizzas / pizzaCount;
        System.out.println(pizzaPercetage);
        TreeSet<Pizza> sortedPizzas = new TreeSet<>();
        sortedPizzas.addAll(pizzas);
//        Random random = new Random();
//        for (int i = 0; i < pizzas.size(); i++) {
//            if (random.nextFloat() <= pizzaPercetage - .05) {
//                sortedPizzas.add(pizzas.get(i));
//            }
//        }
        Iterator<Pizza> pizzaIterator = sortedPizzas.iterator();

        while (pizzaIterator.hasNext()) {
            Pizza pizza = pizzaIterator.next();
            if (score + pizza.getSize() <= MAX) {
                pizza.setSelected(true);
                selectedPizzas.add(pizza);
                score += pizza.getSize();
                pizzaIterator.remove();
            }
        }
        for (int i = 0; i < selectedPizzas.size(); i++) {
            selectAPizzaInsteadOf((i % 3) + 1, sortedPizzas);
        }
//        for (int i = 0; i < selectedPizzas.size(); i++) {
//            Pizza pizza = selectedPizzas.get(i);
//            Pizza higherPizza = sortedPizzas.higher(pizza);
//            while (higherPizza != null
//                    && score - pizza.getSize() + higherPizza.getSize() <= MAX) {
//                System.out.println("replacing " + pizza + " - " + higherPizza);
//                higherPizza.setSelected(true);
//                pizza.setSelected(false);
//
//                selectedPizzas.remove(i);
//                selectedPizzas.add(higherPizza);
//
//                sortedPizzas.remove(higherPizza);
//                sortedPizzas.add(pizza);
//
//                score -= pizza.getSize();
//                score += higherPizza.getSize();
//
//                higherPizza = sortedPizzas.higher(higherPizza);
//
//            }
//        }

        GenerateOutputFile();
    }

    private void selectAPizzaInsteadOf(int n, TreeSet<Pizza> sortedPizzas) {
        if (selectedPizzas.size() < n) {
            return;
        }
        ArrayList<Pizza> removedPizzas = new ArrayList<>();
        int prevScore = 0;
        for (int i = 0; i < n; i++) {
            Pizza pizza = selectedPizzas.get(
                    random.nextInt(selectedPizzas.size()));

            while (removedPizzas.contains(pizza)) {
                pizza = selectedPizzas.get(
                        random.nextInt(selectedPizzas.size()));
            }
            removedPizzas.add(pizza);
            prevScore += pizza.getSize();
        }

        Pizza imaginaryPizza = new Pizza(Integer.MAX_VALUE, prevScore);
        Pizza higherPizza = sortedPizzas.higher(imaginaryPizza);

        if (higherPizza != null
                && score - prevScore + higherPizza.getSize() <= MAX) {
            System.out.println(higherPizza);
            System.out.println(prevScore);

            higherPizza.setSelected(true);
            selectedPizzas.add(higherPizza);
            sortedPizzas.remove(higherPizza);

            for (int i = 0; i < removedPizzas.size(); i++) {
                Pizza pizza = removedPizzas.get(i);

                pizza.setSelected(false);
                sortedPizzas.add(pizza);
                selectedPizzas.remove(pizza);
                score -= pizza.getSize();
            }

            score += higherPizza.getSize();
        }
    }

    private void GenerateOutputFile() throws IOException {
        Collections.sort(selectedPizzas);
        File outputFile = new File("src/practice2020/output/", FILE_NAME
                + "-" + score + "-" + new Date() + ".out");
        outputFile.createNewFile();
        PrintStream printStream = new PrintStream(outputFile);
        printStream.println(selectedPizzas.size());
        selectedPizzas.forEach((pizza) -> {
            printStream.print(pizza.getId() + " ");
        });
    }

    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
        sum += pizza.getSize();
    }

}
