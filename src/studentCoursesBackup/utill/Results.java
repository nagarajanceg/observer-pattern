package studentCoursesBackup.utill;

import studentCoursesBackup.myTree.Node;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {
    private BufferedWriter writer = null;
    private FileOutputStream fp = null;
    private Map<Integer, List<String>> treeResults = null;

    public Map<Integer, List<String>> getTreeResults() {
        return treeResults;
    }
    public Results(){
        treeResults = new HashMap<>();
    }
    public void initialize(String fileName) {
        this.treeResults.clear();
        try {
            fp = new FileOutputStream(fileName);
            writer =new BufferedWriter(new OutputStreamWriter(fp));
        } catch (IOException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }
    private void inorder(Node node){
        if(node != null){
            inorder(node.left);
//            this.writeInFile(nod);
            this.treeResults.put(node.getbNumber(), node.getCourses());
            inorder(node.right);
        }
    }

    public void traverseTree(Node node){
        this.inorder(node);
    }

    public void writeResults(){
        String format = "";
        for(Map.Entry<Integer, List<String>> element: this.treeResults.entrySet()){
            format += element.getKey() +":";
            String courses = "";
            for(String course: element.getValue()){
                courses += " "+course;
            }
            format += courses+"\n";
            this.writeInFile(format);
            format = "";
        }
    }

    @Override
    public void writeInFile(String content) {
        try {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void close(){
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Results{" +
                "treeResults=" + treeResults +
                '}';
    }

    @Override
    public void writeToDisplay(String content) {
        System.out.println(content);
    }
}
