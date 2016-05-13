import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by benbo on 4/29/2016.
 */
public class BranchAndBound01KnapsackAlgorithm {

    private PriorityQueue<Knapsack> combinations;

    public Knapsack setup(PriorityQueue<Node> itemsAvailable, BigDecimal knapsackSize)
    {
        combinations = new PriorityQueue<Knapsack>();
        Knapsack root = new Knapsack(itemsAvailable, knapsackSize);
        Knapsack bestItems = calculateBestItems();
        return bestItems;
   }

    public Knapsack calculateBestItems() {
        Knapsack root = combinations.poll();
        Knapsack bestItems;
        if (root.itemsAvailableIsEmpty()) {
            bestItems = root;
            return bestItems;
        } else {
            Knapsack rightChild;
            Knapsack leftChild;
            try {
                rightChild = root.deepCopy();
                rightChild.throwAwayItem();
                combinations.add(rightChild);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                System.out.println();
                e.printStackTrace();
                System.exit(1);
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
                System.out.println();
                e.printStackTrace();
                System.exit(1);
            }
            if (root.addItem()) {
                leftChild = root;
                combinations.add(leftChild);
            }
            bestItems = calculateBestItems();
            return bestItems;
        }
    }
}
