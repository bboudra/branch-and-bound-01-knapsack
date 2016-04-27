import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Benjamin Boudra
 */
public class Input {

    /**
     * Uses the FileBrowser class to search through the project and retrieve the file. If the file doesn't exist then throw a FileNotFoundException
     * @return the file
     * @throws FileNotFoundException
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
     * @param filename
     * @return
     * @throws FileNotFoundException
     */
    public File retrieveFile(String filename) throws FileNotFoundException {
        File file = new File(filename);
        boolean exists = file.exists();
        if(exists)
            return file;
        else {
            throw new FileNotFoundException("The specified file doesn't exist");
        }
    }


    /**
     * Takes in a File with specific Format and builds a table from that file.
     *
     * @param inputFile
     * @return - The input table
     */
    public ArrayList<Double>[] buildTable(File inputFile) throws IOException, FileNotFoundException
    {
        BufferedReader br;
        br = new BufferedReader(new FileReader(inputFile));
        System.out.println("Something Went wrong, the specified file doesn't exist");
        System.exit(1);
        String line;
        while((line = br.readLine()) != null)
        {
            String[] words = line.split(" ");
            if(!(words.length == 3))
            {
                throw new IOException("The formmating of the file was not valid, please reformat the file so it is a valid file");
            }

        }
    }


}
