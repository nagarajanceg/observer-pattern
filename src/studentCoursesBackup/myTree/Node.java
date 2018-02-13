package studentCoursesBackup.myTree;

import java.util.ArrayList;
import java.util.List;

public class Node implements SubjectI, ObserverI, Cloneable {
    private int bNumber;
    private List<String> courses = null;
    public Node left,right;
    private List<Node> observers = null; //list of observers

    /**
     *
     * @param bNumberIn - id for a student
     * @param courseIn - course for a student
     */
    //Constructor with parameter
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
    //Clone the node object
    @Override
    public Node clone(){
        Node copy = null;
        //Below block is referred with
        //http://javarevisited.blogspot.com/2015/01/java-clone-tutorial-part-2-overriding-with-mutable-field-example.html
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

    /**
     *
     * @param clone - node which need to register as a observer of the main node
     */
    //Register all back up nodes to the main node
    @Override
    public void register(Node clone) {
        List<Node> prevObserverList = this.getObservers();
        prevObserverList.add(clone);
        this.setObservers(prevObserverList);
    }

    /**
     *
     * @param clone - node which need to unregister as a observer of the main node
     */
    //unRegister all back up nodes to the main node
    @Override
    public void unregister(Node clone) {
        List<Node> prevObserverList = this.getObservers();
        if(prevObserverList.indexOf(clone) >= 0){
            prevObserverList.remove(clone);
            this.setObservers(prevObserverList);
        }
    }

    /**
     *
     * @param courseName - updated course name
     * @param operation - enum operator to represent the type of operation
     */
    //Trigger Notify to inform observers to update the changes made to the main tree
    @Override
    public void myNotifyAll(String courseName, Enum operation) {
        for (Node observer : this.getObservers()) {
            observer.update(courseName, operation);
        }
    }

    /**
     *
     * @param courseNameIn - Course to update the observer
     * @param operation - enum operator to represent the type of operation
     */
    // Update the back up nodes based on the enum type of operation
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
