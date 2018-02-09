package studentCoursesBackup.myTree;

import studentCoursesBackup.utill.Operations;

import java.util.ArrayList;
import java.util.List;

public class Node implements SubjectI, ObserverI, Cloneable {
    private int bNumber;
    private List<String> courses = null;
    public Node left,right;
    private List<Node> observers = null; //list of observers

    public Node(int bNumberIn, String courseIn  ) {
        this.setbNumber(bNumberIn);
        this.courses = new ArrayList<String>();
        courses.add(courseIn);
        this.setLeft(null);
        this.setRight(null);
        this.observers = new ArrayList<Node>();
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void setObservers(List<Node> observers) {
        this.observers = observers;
    }

    public Node getLeft() {

        return left;
    }

    public Node getRight() {
        return right;
    }

    public List<Node> getObservers() {
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
    public Node clone(){
        Node copy = null;
        try {
            copy = (Node) super.clone();
            copy.setbNumber(this.getbNumber());
            copy.setLeft(this.getLeft());
            copy.setRight(this.getRight());
            copy.courses = new ArrayList<>(this.getCourses());
            copy.observers = new ArrayList<>(this.getObservers());
            return  copy;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return copy;
    }

    @Override
    public String toString() {
        return "Node{" +
                "bNumber=" + bNumber +
                ", courses=" + courses +
                '}';
    }

    @Override
    public void register(Node clone) {
        List<Node> prevObserverList = this.getObservers();
        prevObserverList.add(clone);
        this.setObservers(prevObserverList);
    }

    @Override
    public void unregister(Node clone) {
        List<Node> prevObserverList = this.getObservers();
        if(prevObserverList.indexOf(clone) >= 0){
            prevObserverList.remove(clone);
            this.setObservers(prevObserverList);
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
