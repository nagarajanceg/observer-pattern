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

    private void setLeft(Node left) {
        this.left = left;
    }

    private void setRight(Node right) {
        this.right = right;
    }

    private void setObservers(List<Node> observers) {
        this.observers = observers;
    }

    private Node getLeft() { return left; }

    private Node getRight() {
        return right;
    }

    private List<Node> getObservers() {
        return observers;
    }

    public int getbNumber() {
        return bNumber;
    }

    private void setbNumber(int bNumber) {
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
    public void myNotifyAll(String courseName, Enum operation) {
        for (Node observer : this.getObservers()) {
            observer.update(courseName, operation);
        }
    }

    @Override
    public void update(String courseNameIn, Enum operation) {
        List<String> courseList = this.getCourses();
        if(Operations.UPDATE.equals(operation)){
            courseList.add(courseNameIn);
        }else{
            courseList.remove(courseNameIn);
        }
        this.setCourses(courseList);
    }
}
