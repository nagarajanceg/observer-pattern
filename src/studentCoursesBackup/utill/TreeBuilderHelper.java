package studentCoursesBackup.utill;

import java.io.BufferedReader;
import java.io.IOException;

public class TreeBuilderHelper {
    FileProcessor fp = null;
    public TreeBuilderHelper() {
         fp = new FileProcessor();
    }

    public void inputFileProcessor(String name, Results results, TreeBuilder tree){
        BufferedReader reader = null;
        try{
            String line ;
            reader = fp.readerDesc(name);
            while((line = fp.read(reader))!= null) {
                tree.buildTree(line);
            }
        }catch (Exception e){
            e.printStackTrace();
            results.writeToDisplay("Exception");
        }finally {
            try {
                reader.close();
            } catch (IOException e) {
                results.writeToDisplay("Buffered Reader close IOException");
                e.printStackTrace();

            }
        }
    }

    public void deleteFileProcessor(String name, Results results, TreeBuilder tree){
        BufferedReader reader = null;
        try{
            String line ;
            reader = fp.readerDesc(name);
            while((line = fp.read(reader))!= null) {
                tree.deleteNodeData(line);
            }
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                reader.close();
            } catch (IOException e) {
                results.writeToDisplay("Buffered Reader close IOException");
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return "TreeBuilderHelper{" +
                "fp=" + fp +
                '}';
    }
}
