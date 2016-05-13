import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by benbo on 5/3/2016.
 */
public class Driver {

    public static void main(String[] args) throws IOException {
        IO iO = new IO();
        File[] files = null;
        try {
            files = iO.retrieveFilesFromDirectory();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            System.out.println();
            System.out.println(e.getMessage());
            System.out.println();
            System.out.println(e.getReason());
        }

        for(File file: files)
        {
            StringBuilder sB = new StringBuilder();
            PriorityQueue<Node> nodes= iO.buildNodes(file,sB);
            BranchAndBound01KnapsackAlgorithm bAB01KA = new BranchAndBound01KnapsackAlgorithm();
            Knapsack bestItems = bAB01KA.setup(nodes,);

            String fileName = file.getName();
            String outputName = fileName + "Output";
            StringBuilder sB = new StringBuilder();
            sB.append("The results are: ");
            try {
                iO.storeFilesInOutputDirectory(outputName, sB.toString());
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            } catch (URISyntaxException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}
