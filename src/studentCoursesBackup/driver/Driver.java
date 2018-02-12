package studentCoursesBackup.driver;

import studentCoursesBackup.myTree.Node;
import studentCoursesBackup.utill.Results;
import studentCoursesBackup.utill.TreeBuilder;
import studentCoursesBackup.utill.TreeBuilderHelper;


public class Driver {
    /**
     *
     * @param name -file name
     * @param results - instance used to write a results in file
     * @param root - root reference of the tree
     */
    public void constructResults(String name, Results results,  Node root){
        results.initialize(name);
        results.printNodes(root);
        if(results.getTreeResults().size() > 0){
            results.writeResults();
        }else{
            //results.writeInFile("No results found");
            results.writeToDisplay("No results Found");
        }
        results.close();
    }

    public static void main(String[] args){

        if (args.length != 5 || args[0].equals("${arg0}") || args[1].equals("${arg1}") || args[2].equals("${arg2}")
                || args[3].equals("${arg3}") || args[4].equals("${arg4}")) {

            System.err.println("Error: Incorrect number of arguments. Program accepts 5 arguments.");
            System.exit(0);
        }
        TreeBuilderHelper treeBuilderHelper = new TreeBuilderHelper();
        TreeBuilder tree = new TreeBuilder();
        Results results = new Results();
        Driver driver = new Driver();
        treeBuilderHelper.inputFileProcessor(args[0], results, tree);
        treeBuilderHelper.deleteFileProcessor(args[1], results, tree);
        driver.constructResults(args[2], results, tree.getRoot());
        driver.constructResults(args[3], results, tree.getRootBackup1());
        driver.constructResults(args[4], results, tree.getRootBackup2());
    }

    @Override
    public String toString() {
        return "Driver{}";
    }
}
