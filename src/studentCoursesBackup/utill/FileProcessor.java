package studentCoursesBackup.utill;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.IOException;

public class FileProcessor {
    public BufferedReader readerDesc(String name) {
        FileInputStream fp;
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
            // FileInputStream need to close
        }
        return reader;
    }
    public String read(BufferedReader reader){
        String line=null;
        if(reader == null){
            return line;
        }
        try {

             line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    @Override
    public String toString() {
        return "FileProcessor{}";
    }
}
