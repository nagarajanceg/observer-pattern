package studentCoursesBackup.myTree;

public interface SubjectI {
    void register(Node o);
    void unregister(Node o);
    void myNotifyAll(String courseName, Enum operation);
}
