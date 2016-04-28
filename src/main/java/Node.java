import com.sun.glass.ui.Size;

import java.math.BigDecimal;
import java.util.Comparator;

/**
 * Created by benbo on 4/27/2016.
 */
public class Node implements Comparable<Node>{
    private final BigDecimal PROFIT;
    private final BigDecimal WEIGHT;
    private final BigDecimal PROFIT_PER_WEIGHT;


    /**
     * Builds A node object
     *
     * @param profit the profit that selling this knapsack item will yield
     * @param weight the weight of this item in the knapsack
     */
    public Node(BigDecimal profit,BigDecimal weight)
    {
        PROFIT = profit;
        WEIGHT = weight;
        PROFIT_PER_WEIGHT = weight.divide(profit, 2);
    }

    /**
     * getProfit retrieves the profit of a node object and returns it to the caller
     *
     * @return the profit
     */
    public BigDecimal getProfit()
    {
        return this.PROFIT;
    }

    /**
     * getWeight retieves the weight of a node object and returns it to the caller.
     *
     * @return the weight of the node object
     */
    public BigDecimal getWeight()
    {
        return this.WEIGHT;
    }

    /**
     * <code>getProfitPerWeight</code> retrieves the profit per weight of a node object and returns it to the caller.
     *
     * @return the profit per weight of a Node object
     */
    public BigDecimal getProfitPerWeight()
    {
        return this.PROFIT_PER_WEIGHT;
    }

    /**
     * Compares the profitPerWeight of a node object being passed in as a parameter against this one.
     *
     * @param n the node object this object is being
     * @return
     * Will return:
     * <ul>
     *     <li><code>1</code> if this node has a greater profit per weight than n.</li>
     *     <li><code>0</code> if this node has the same Profit per weight as n</li>
     *     <li><code>-1</code>-1 if n has a greater profit per weight than node 1</li>
     * </ul>
     */
    public int compareTo(Node n) {
        return this.getProfitPerWeight().compareTo(n.getProfitPerWeight());
    }
}
