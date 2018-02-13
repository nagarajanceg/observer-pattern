package studentCoursesBackup.utill;

import studentCoursesBackup.myTree.Node;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {
    private BufferedWriter writer = null;
    private FileOutputStream fp = null;
    private Map<Integer, List<String>> treeResults = null;

    public Map<Integer, List<String>> getTreeResults() {
        return treeResults;
    }

    public Results(){
        treeResults = new TreeMap<>();
    }

    /**
     *
     * @param fileName - output file name
     */
    public void initialize(String fileName) {
        this.treeResults.clear();
        try {
            fp = new FileOutputStream(fileName);
            writer =new BufferedWriter(new OutputStreamWriter(fp));
        } catch (IOException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }finally {

        }
    }

    /**
     *
     * @param node - node reference which need to traverse
     */
    public void printNodes(Node node){
        if(node != null){
            printNodes(node.left);
            this.treeResults.put(node.getbNumber(), node.getCourses());
            printNodes(node.right);
        }
    }

    public void writeResults(){
        String format = "";
        for(Map.Entry<Integer, List<String>> element: this.treeResults.entrySet()){
            format += element.getKey() +":";
            StringBuffer courses =new StringBuffer();
            for(String course: element.getValue()){
                courses.append(" "+course);
            }
            format += courses.toString()+"\n";
            this.writeInFile(format);
            format = "";
        }
    }

    /**
     *
     * @param content - content to write in a file
     */
    @Override
    public void writeInFile(String content) {
        try {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }

    }
    public void close(){
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }
    }

    @Override
    public String toString() {
        return "Results{" +
                "treeResults=" + treeResults +
                '}';
    }

    /**
     *
     * @param content - content to write in a display
     */
    @Override
    public void writeToDisplay(String content) {
        System.out.println(content);
    }
}
