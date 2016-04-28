import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * @author Benjamin Boudra
 */
public class Input {

    /**
     * Uses the FileBrowser class to search through the project and retrieve the file. If the file doesn't exist then throw a FileNotFoundException
     *
     * @return the file
     * @throws FileNotFoundException The file doesn't exist
     */
    public File retrieveFile() throws FileNotFoundException {
        String filename = FileBrowser.chooseFile(true);
        File file = new File(filename);
        if (file.exists()) {
            return file;
        } else {
            throw new FileNotFoundException("The specified file doesn't exist");
        }
    }

    /**
     * Takes a filename and retrieves the file. If the file doesn't exst then throw a FileNotFoundException.
     *
     * @param filename the name of the file
     * @return The file who's filename was passed in.
     * @throws FileNotFoundException The file was not found.
     */
    public File retrieveFile(String filename) throws FileNotFoundException {
        File file = new File(filename);
        boolean exists = file.exists();
        if (exists)
            return file;
        else {
            throw new FileNotFoundException("The specified file doesn't exist");
        }
    }


    /**
     * Takes in a file with specific Format and builds a table from that file.
     *
     * @param inputFile The input file
     * @return The priority queue
     * @throws IndexOutOfBoundsException The number of words in a do not equal the number that should be there
     * @throws IOException there was a problem opening the file
     */
    public PriorityQueue<Node> buildNodes(File inputFile) throws IndexOutOfBoundsException, IOException{
        BufferedReader br;
        br = new BufferedReader(new FileReader(inputFile));
        System.out.println("Something Went wrong, the specified file doesn't exist");
        System.exit(1);
        String line;
        PriorityQueue<Node> nodes = new PriorityQueue<Node>();
        while ((line = br.readLine()) != null) {
            String[] words = line.split(" ");
            if (!(words.length == 0)) {
                throw new IndexOutOfBoundsException("The formatting of the file was not valid, please reformat the file so it is a valid file");
            }
            nodes.add(new Node(new BigDecimal(Integer.parseInt(words[0])), new BigDecimal(Integer.parseInt(words[1]))));
        }
        return nodes;
    }
}
