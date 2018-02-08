package studentCoursesBackup.driver;

import studentCoursesBackup.myTree.Node;
import studentCoursesBackup.utill.FileProcessor;
import studentCoursesBackup.utill.Results;
import studentCoursesBackup.utill.TreeBuilder;

import java.io.BufferedReader;
import java.io.IOException;

public class Driver {
    public void inputFileProcessor(String name, TreeBuilder tree){
        FileProcessor fp = new FileProcessor();
        BufferedReader reader = fp.readerDesc(name);
        try{
            String line ;
            while((line = fp.read(reader))!= null) {
                tree.buildTree(line);
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("exception");
        }finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteFileProcessor(String name, TreeBuilder tree){
        FileProcessor fp = new FileProcessor();
        BufferedReader reader = fp.readerDesc(name);
        try{
            String line ;
            while((line = fp.read(reader))!= null) {
                System.out.println(line);
                tree.deleteNodeData(line);
            }
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void constructResults(String name,  Node root){
        Results results = new Results(name);
        results.traverseTree(root);
        if(results.getTreeResults() != null){
            results.writeResults();
        }else{
            results.writeInFile("No results found");
            results.writeToDisplay("No results Found");
        }
        results.close();
    }

    public static void main(String[] args){
        TreeBuilder tree = new TreeBuilder();
        Driver driver = new Driver();
        driver.inputFileProcessor("input.txt", tree);
        driver.deleteFileProcessor("delete.txt",tree);
        driver.constructResults("output1.txt", tree.getRoot());
        driver.constructResults("output2.txt", tree.getRootBackup1());
        driver.constructResults("output3.txt", tree.getRootBackup2());
    }
}
