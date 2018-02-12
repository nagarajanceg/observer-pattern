package studentCoursesBackup.utill;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.IOException;

public class FileProcessor {
    /**
     *
     * @param name - file name
     * @return reader to given the file name
     */
    public BufferedReader readerDesc(String name) {
        FileInputStream fp = null;
        BufferedReader reader = null;
        try {
            fp = new FileInputStream(name);
            reader = new BufferedReader(new InputStreamReader(fp));
            return reader;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File Not found");
            System.exit(0);
        } finally {

        }
        return reader;
    }

    /**
     *
     * @param reader - BufferedReader for a file
     * @return a single line in the given reader
     */
    public String readLine(BufferedReader reader){
        String line=null;
        if(reader == null){
            return line;
        }
        try {

             line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
        return line;
    }

    @Override
    public String toString() {
        return "FileProcessor{}";
    }
}
