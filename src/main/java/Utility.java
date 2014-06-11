package diffutils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

public class Utility {
    public static List<String> fileToLines(String filename) {
        List<String> lines = new LinkedList<String>();
        String line = "";
        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            while ((line = in.readLine()) != null) {
                lines.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Not found: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
