package studentCoursesBackup.myTree;

import studentCoursesBackup.utill.Operations;

import java.util.ArrayList;
import java.util.List;

public class Node implements SubjectI, ObserverI,Cloneable {
    private int bNumber;
    private List<String> courses = null;
    public Node left,right;
    private ArrayList<Node> observers = null; //list of observers

    public Node(int bNumberIn, String courseIn  ) {
        this.setbNumber(bNumberIn);
        this.courses = new ArrayList<String>();
        courses.add(courseIn);
        this.setLeft(null);
        this.setRight(null);
        this.observers = new ArrayList<Node>();
    }

    public Node(Node cin){
        this.setbNumber(cin.getbNumber());
        this.courses = new ArrayList<String>();
        this.setLeft(cin.getLeft());
        this.setRight(cin.getRight());
        this.observers = new ArrayList<Node>();
        this.courses.addAll(cin.getCourses());
        this.observers.addAll(cin.getObservers());
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void setObservers(ArrayList<Node> observers) {
        this.observers = observers;
    }

    public Node getLeft() {

        return left;
    }

    public Node getRight() {
        return right;
    }

    public ArrayList<Node> getObservers() {
        return observers;
    }

    public int getbNumber() {
        return bNumber;
    }

    public void setbNumber(int bNumber) {
        this.bNumber = bNumber;
    }

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Node{" +
                "bNumber=" + bNumber +
                ", courses=" + courses +
                '}';
    }

    @Override
    public void register(Node o) {
        this.observers.add(o);
    }

    @Override
    public void unregister(Node o) {
        if(this.observers.indexOf(o) >= 0){
            this.observers.remove(o);
        }
    }

    @Override
    public void myNotifyAll(String courseName, String operation) {
        for(int i=0; i< this.observers.size(); i++){
            this.observers.get(i).update(courseName, operation);
        }
    }

    @Override
    public void update(String courseNameIn, String operation) {
        List<String> courseList = this.getCourses();
        if(Operations.UPDATE.name().equals(operation)){
            courseList.add(courseNameIn);
        }else{
            courseList.remove(courseNameIn);
        }
        this.setCourses(courseList);
    }
}
