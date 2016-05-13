import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 *
 * @author Benjamin Boudra
 */
public class Knapsack implements Serializable, Comparable<Knapsack>{

    /** The list of Items that can be placed in the knapsack */
    public PriorityQueue<Node> itemsAvailable;
    /** the Items in the knapsack */
    public List<Node> items;
    /** the Max size of the Knapsack */
    private final BigDecimal KNAPSACK_SIZE;
    /** The profit the items in the knapsack will yield */
    private BigDecimal profit;
    /** The weight of the knapsack */
    private BigDecimal currentKnapsackWeight;

    /**
     * Takes in a priorityQueue of items and the size of a knapsack and builds the
     *
     * @param itemsAvailable The itemes that are still available to be consumed
     * @param knapsackSize   - the size of the knapsack
     */
    public Knapsack(PriorityQueue<Node> itemsAvailable, BigDecimal knapsackSize ) {
        this.itemsAvailable = itemsAvailable;
        this.KNAPSACK_SIZE = knapsackSize;
        this.profit = new BigDecimal("0");
        this.currentKnapsackWeight = new BigDecimal("0");
        this.items = new ArrayList<Node>();
    }


    /**
     * Gets the items in the knapsack and returns those Items
     *
     * @return
     */
    public List<Node> getItems()
    {
        return this.items;
    }
    /**
     * Adds an Item to the <code>item</code> list.
     * @return
     * <ul>
     *     <li><code>true</code>If addItem operation was successful</li>
     *     <li><code>false</code> If the Items available list is empty or adding an item would exceed the Knapsack's max weight</li>
     * </ul>
     */
    public boolean addItem()
    {
        if(!itemsAvailableIsEmpty())
        {
            if(!addItemWouldExceedKnapsackMaxWeight())
            {
                Node item = this.itemsAvailable.poll();
                this.items.add(item);
                this.currentKnapsackWeight= this.currentKnapsackWeight.add(item.getWeight());
                this.profit = this.profit.add(item.getProfit());
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    /**
     * Removes an Item from the Knapsack
     *
     * @return <ul>
     *     <li><code>true</code> if the remove item operation was successful</li>
     *     <li><code>false</code> if the items list is empty.</li>
     * </ul>
     */
    public boolean removeItem()
    {
        if(items.size()!= 0)
        {
            Node item = this.items.remove(items.size()-1);
            this.itemsAvailable.add(item);
            this.currentKnapsackWeight = this.currentKnapsackWeight.subtract(item.getWeight());
            this.profit = this.profit.subtract(item.getProfit());
            return true;
        } else {
            return false;
        }
    }

    public BigDecimal getPontentialProfit()
    {
        BigDecimal potentialProfit;
        if(this.addItem())
        {
            potentialProfit = getPontentialProfit();
            this.removeItem();
            return potentialProfit;
        }
        else
        {
            if(this.currentKnapsackWeight.compareTo(this.getKnapsackMaxWeight()) == -1)
            {
                BigDecimal weightLeftInBag = this.getKnapsackMaxWeight().subtract(this.currentKnapsackWeight);
                Node partialNode = this.itemsAvailable.peek();
                this.addPartial(partialNode,weightLeftInBag);
                potentialProfit = this.profit;
                removePartial();
                return potentialProfit;
            } else {
                return this.profit;
            }
        }
    }

    public void removePartial()
    {
        Node partial = items.remove(items.size()-1);
        this.currentKnapsackWeight = this.currentKnapsackWeight.subtract(partial.getWeight());
        this.profit = this.profit.subtract(partial.getProfit());
    }

    /**
     * Gets the Maximum weight of the knapsack and returns it to the caller.
     * @return The Max Weight of the knapsack.
     */
    public BigDecimal getKnapsackMaxWeight() {
        return this.KNAPSACK_SIZE;
    }


    /**
     * Dumps item with greatest ppw ratio from the Items available list.
     * @throws NullPointerException there are no items in the itemsAvailable list
     */
    public void throwAwayItem() throws NullPointerException {
        if (this.itemsAvailableIsEmpty()) {
            throw new NullPointerException("The items available list is empty");
        } else {
            itemsAvailable.poll();
        }
    }

    /**
     * Checks if adding an item to the list would exceed the max weight of the Knapsack.
     * @return <ul>
     *     <li><code>True</code> if an addition to the items would exceed the max weight of the Knapsack.</li>
     *     <li><code>False</code> if an addition to the items would not exceed the max weight of the Knapsack.</li>
     * </ul>
     */
    public boolean addItemWouldExceedKnapsackMaxWeight()
    {
        Node node = itemsAvailable.peek();
        BigDecimal weightLeftInBag =this.getKnapsackMaxWeight().subtract(this.currentKnapsackWeight);
        if(weightLeftInBag.subtract(node.getWeight()).compareTo(new BigDecimal("0")) == -1)
            return true;
        else
            return false;
    }

    /**
     * Checks if the items available list is empty and returns true or false accordingly.
     *
     * @return
     * <ul>
     *     <li><code>true</code> if the items available list is empty</li>
     *     <li><code>false</code> if the items available list is not empty</li>
     * </ul>
     */
    public boolean itemsAvailableIsEmpty() {
        return itemsAvailable.peek().equals(null) ;
    }

    /**
     * Creates a deep copy of the knapsack object and returns it to the caller
     *
     * @return the Knapsack
     * @throws IOException An I/O Error occurred.
     * @throws ClassNotFoundException The class being casted to was not found.
     */
    public Knapsack deepCopy() throws IOException, ClassNotFoundException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(this);
        out.flush();
        out.close();
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
        Knapsack k = (Knapsack) in.readObject();
        return k;
    }

    /**
     * Creates a Partial Node, used only in maximum size Calculation
     *
     * @param n the node partialNode is being created from.
     * @param weightLeftInBag the weight left in the bag.
     * @return <ul>
     *     <li><code>true</code> If the partial node was successfully added to the bag.</li>
     *     <li><code>false</code> If the partial Node was not successfully added to the bag.</li>
     * </ul>
     */
    public boolean addPartial(Node n, BigDecimal weightLeftInBag)
    {
        if(addItemWouldExceedKnapsackMaxWeight())
        {
            return false;
        } else {
            BigDecimal partialWeight = weightLeftInBag;
            BigDecimal partialProfit = n.getProfitPerWeight().multiply(partialWeight);
            Node partialNode = new Node(partialProfit, partialWeight);
            items.add(partialNode);
            this.currentKnapsackWeight.add(partialNode.getWeight());
            this.profit.add(partialNode.getProfit());
            return true;
        }
    }

    /**
     * Compares the profit of the current Knapsack with the profit of another knapsack.
     * @param k the Knapsack that is being compared against this knapsack
     * @return <ul>
     *     <li><code>-1</code> if Knapsack k's potentialProfit is greater than this Knapsack's</li>
     *     <li><code>0</code> if Knapsack k's potentialProfit and this Knapsack's are equal</li>
     *     <li><code>1</code> if Knapsack k's potentialProfit is less than this Knapsack's</li>
     * </ul>
     */
    public int compareTo(Knapsack k)
    {
        return this.getPontentialProfit().compareTo(k.getPontentialProfit());
    }
}
