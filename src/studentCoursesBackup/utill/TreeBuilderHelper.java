package studentCoursesBackup.utill;

import java.io.BufferedReader;
import java.io.IOException;

public class TreeBuilderHelper {
    private FileProcessor fp = null;
    public TreeBuilderHelper() {
         fp = new FileProcessor();
    }

    /**
     *
     * @param name -Input file name
     * @param results - Instance used to write in display
     * @param tree - Instance to create and insert a node in a tree
     */
    public void inputFileProcessor(String name, Results results, TreeBuilder tree){
        BufferedReader reader = null;
        try{
            String line ;
            reader = fp.readerDesc(name);
            while((line = fp.readLine(reader))!= null) {
                tree.buildTree(line);
            }
        }catch (Exception e){
            e.printStackTrace();
            results.writeToDisplay("Exception");
        }finally {
            try {
                if (null != reader) {
                    reader.close();
                }
            } catch (IOException e) {
                results.writeToDisplay("Buffered Reader close IOException at closing input file");
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param name - Name of delete file
     * @param results - Instance used to write in display
     * @param tree - Instance to delete a node in a tree
     */
    public void deleteFileProcessor(String name, Results results, TreeBuilder tree){
        BufferedReader reader = null;
        try{
            String line ;
            reader = fp.readerDesc(name);
            while((line = fp.readLine(reader))!= null) {
                tree.deleteNodeData(line);
            }
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                results.writeToDisplay("Buffered Reader close IOException at closing output file");
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return "TreeBuilderHelper{}";
    }
}
