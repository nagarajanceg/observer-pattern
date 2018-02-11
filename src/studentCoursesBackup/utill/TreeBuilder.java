package studentCoursesBackup.utill;

import studentCoursesBackup.myTree.Node;
import studentCoursesBackup.myTree.Operations;

import java.util.Collections;
import java.util.List;

public class TreeBuilder{
    // Three references for the main and backup trees.
    private Node root ;
    private Node rootBackup1, rootBackup2;

    public TreeBuilder() {
        root = null;
        rootBackup1 =null;
        rootBackup2 = null;
    }

    public Node getRoot() {
        return root;
    }

    public Node getRootBackup1() {
        return rootBackup1;
    }

    public Node getRootBackup2() {
        return rootBackup2;
    }
    // Insert routine to insert the nodes in the trees
    private  Node insert(Node node, Node currentNode){
        if(currentNode != null && node == null){
            return currentNode;
        }else{
            if(currentNode.getbNumber() < node.getbNumber()){
                node.left = insert(node.left, currentNode);
            }else if(currentNode.getbNumber() > node.getbNumber()){
                node.right = insert(node.right, currentNode);
            }
        }
        return node;
    }
    // Search the bNumber in the tree provided
    private Node searchNode(Node node, int bNumber){
        if(node == null){
            return node;
        }
        if(node.getbNumber() == bNumber){
            return node;
        }
        if(node.getbNumber() > bNumber){
            return searchNode(node.left, bNumber);
        }else if(node.getbNumber() < bNumber) {
            return searchNode(node.right, bNumber);
        }
        return null;
    }
    // Add the course list to the already created node
    private void addCourses(Node node, Node currentNode){
        List<String> newCourses =  node.getCourses();
        if(Collections.disjoint(currentNode.getCourses(), newCourses) == true){
            newCourses.addAll(currentNode.getCourses());
            node.setCourses(newCourses);
            node.myNotifyAll(currentNode.getCourses().get(0), Operations.UPDATE);
        }
    }
    // Node creation, cloning and register cloned nodes as listener.
    public void buildTree(String str){
        String[] data = str.split(":");
        Node node = new Node(Integer.valueOf(data[0]), data[1]);
        int bNumber = node.getbNumber();

        Node nodeExist =  this.searchNode(root, bNumber);

        Node backupClone1 = node.clone();
        Node backupClone2 = node.clone();
        //Register the backup notes as a listener to the main subject node
        node.register(backupClone1);
        node.register(backupClone2);

        if(nodeExist == null){
            //Insert in all the three nodes
            root = this.insert(root, node);
            rootBackup1 = this.insert(rootBackup1, backupClone1);
            rootBackup2 = this.insert(rootBackup2, backupClone2);
        }else{
            this.addCourses(nodeExist, node);
        }
    }
    // Search and delete the node in the main tree and notify the backup tree nodes
    public void deleteNodeData(String str){
        String[] data = str.split(":");
        int bNumber = Integer.valueOf(data[0]);
        String courseName = data[1];
        Node nodeExist =  this.searchNode(this.root, bNumber);
        if(nodeExist != null){
            List<String> courseList = nodeExist.getCourses();
            if(courseList.contains(courseName)){
                courseList.remove(courseName);
                nodeExist.myNotifyAll(courseName, Operations.DELETE);
                nodeExist.setCourses(courseList);
            }
        }
    }

    @Override
    public String toString() {
        return "TreeBuilder{" +
                "root=" + root +
                ", rootBackup1=" + rootBackup1 +
                ", rootBackup2=" + rootBackup2 +
                '}';
    }
}
