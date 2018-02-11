Basedir: studentCoursesBackup
Assuming you are in the directory containing this README:

## To clean:
ant -buildfile src/build.xml clean

-----------------------------------------------------------------------
## To compile:
ant -buildfile src/build.xml all

-----------------------------------------------------------------------
## To run by specifying arguments from command line
## We will use this to run your code
ant -buildfile src/build.xml run -Darg0="input.txt" -Darg1="delete.txt" -Darg2="output1.txt" -Darg3="output2.txt" -Darg4="output3.txt"

-----------------------------------------------------------------------
##To generate a javadoc from command line
ant -buildfile src/build.xml doc

-----------------------------------------------------------------------
"I have done this assignment completely on my own. I have not copied
 it, nor have I given my solution to anyone else. I understand that if
 I am involved in plagiarism or cheating I will have to sign an
 official form that I have cheated and that this form will be stored in
 my official university record. I also understand that I will receive a
 grade of 0 for the involved assignment for my first offense and that I
 will receive a grade of F for the course for any additional
 offense."
 [Date:02/10/2018]
------------------------------------------------------------------------
Provide justification for Data Structures used in this assignment in
term of Big O complexity

Search:
    Time Complexity:
        Average case with n nodes - O(log n)
        worst case with n nodes - O(n)
    Space Complexity:
        O(n)
Insert:
    Time Complexity:
        Average case with n nodes - O(log n)
        Worst case - O(n)
    Space Complexity:
        O(n)
Deletion:
    Time Complexity:
        Average case with n nodes - O(log n)
        Worst case - O(n)

Insertion in binary search tree is simple. I consider about the average case most of the other trees perform same O(log n)
when compare binary search tree.But in other trees insertion logic is bit of complex like rotation to balance tree or
maintaining additional property and check. Adding additional property consume more memory and balance a tree took time.
So BST have these advantages in average cases.

Observer pattern implementation:
For the very first time node insertion will be happen in all the three trees. Then the course list for a student with
bNumber already exist in the main tree will be updated and notify all the observers of that changed node. This updates
will be made in the corresponding back up tree nodes.
In the case of delete a course, the node is searched and find out in the main tree and removed the course from the list.
Then notify will be called on successful deletion to inform this change to the observers. Then the updated course list
propagated to the back up trees as well.
Observer pattern is implemented for updates and delete.

------------------------------------------------------------------------
Provide list of citations (urls, etc.)
ant to generate javadoc: https://stackoverflow.com/questions/1495982/how-to-generate-javadoc-with-ant-for-an-existing-project
override clone method: http://javarevisited.blogspot.com/2015/01/java-clone-tutorial-part-2-overriding-with-mutable-field-example.html
Binary search tree: https://en.wikipedia.org/wiki/Binary_search_tree
