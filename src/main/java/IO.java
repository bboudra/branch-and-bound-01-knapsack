import java.io.*;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.PriorityQueue;

/**
 * @author Benjamin Boudra
 */
public class IO {

    public String filename;

    /**
     * Uses the FileBrowser class to search through the project and retrieve the file. If
     * the file doesn't exist then throw a FileNotFoundException
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
     * Checks to see if the <code>inputFiles</code> directory exists and contains files.
     * <ul>
     *     <li>If the the <code>inputFiles</code> directory exists and contains files, <code>retrieveFilesFromDirectory</code>
     *     will return the files contained in that directory.</li>
     *     <li>If the directory doesn't exist or is empty. The <code>retrieveFilesFromDirectory</code> will
     *     throw a <code>NullPointerException</code>.</li>
     * </ul>
     *
     * @return A list of files in that directory.
     * @throws NullPointerException The directory either doesn't exist or is empty.
     * @throws URISyntaxException Something is wrong with the URISyntax (I guess)
     */
    public File[] retrieveFilesFromDirectory() throws NullPointerException, URISyntaxException
    {
        URL url = ClassLoader.getSystemResource("inputFiles");
        File dir = new File(url.toURI());
        File[] files = null;
        if(dir.exists() && dir.isDirectory())
        {
            files = dir.listFiles();
            return files;
        }
        else
        {
            throw new NullPointerException("Either the directory doesn't exist or it is empty.");
        }
    }

    /**
     * Write the message passed into the parameters to a file with the filename specified in the parameters.
     *
     * @param filename the name of the file
     * @param message the message that should be written to the file.
     * @throws IOException an IOException occurred
     * @throws URISyntaxException a URISyntaxException occurred.
     */
    public void storeFilesInOutputDirectory(String filename, String message) throws IOException, URISyntaxException {
        URL url = ClassLoader.getSystemResource("outputFiles");
        File dir = new File(url.toURI());
        File file = new File(dir, filename);
        BufferedWriter bW = new BufferedWriter(new FileWriter(file));
        bW.write(message);
        bW.flush();
        bW.close();
    }


    /**
     * Takes a filename and retrieves the file. If the file doesn't exist then throw a FileNotFoundException.
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
     * Takes in a file with specific format and builds a Priority Queue from that file.
     *
     * @param inputFile The input file
     * @return The priority queue
     * @throws IndexOutOfBoundsException The number of words in a do not equal the number that should be there
     * @throws IOException               there was a problem opening the file
     */
    public PriorityQueue<Node> buildNodes(File inputFile, StringBuilder sB) throws IndexOutOfBoundsException, IOException {

        BufferedReader br;
        br = new BufferedReader(new FileReader(inputFile));
        String line;
        PriorityQueue<Node> nodes = new PriorityQueue<Node>();
        sB.append("The Capacity of knapsack is: ");
        line = br.readLine();
        sB.append(line + "\n");
        sB.append("Items are: \n");
        while ((line = br.readLine()) != null) {
            sB.append(line + "\n");
            String[] words = line.split(" ");
            if (!(words.length == 0)) {
                throw new IndexOutOfBoundsException("The formatting of the file was not valid, please reformat the file " +
                        "so it is a valid file");
            }
            nodes.add(new Node(new BigDecimal(Integer.parseInt(words[0])), new BigDecimal(Integer.parseInt(words[1]))));
        }
        return nodes;
    }
}
