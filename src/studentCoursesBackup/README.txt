Basedir: studentCoursesBackup
To compile:
	ant -buildfile src/build.xml compile
To create Jar:
	ant -buildfile src/build.xml jar
To run the application:
	java -jar src/build/jar/observerPattern.jar Driver input.txt delete.txt output1.txt output2.txt output3.txt
To Clean:
	ant -buildfile src/build.xml clean

Short explanation of code flow

In Driver, Read the input.txt file line by line

Driver -> fileProcessor -> Return Reader
For every line Tree builder create a node and insert in main tree.
There are two other root references for the back up tree

In main tree insertion:
   First search the created node already available in the tree, if its available return previously created node
   and check the course name is also available if it's not update the course list.
For back up tree:
    Once the node for the main tree created , clone twice for backup and register as observer of the main node.
    Cloned node is inserted as the same way as the main tree.
    Clonable interface not suppose to use so cloning manually. Copy constructor is used to clone the nodes.