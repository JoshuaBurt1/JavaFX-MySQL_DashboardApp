# Connecting to MySQL with IntelliJ

#WAMP
1. Download a test server ie. WampServer https://sourceforge.net/projects/wampserver/
2. Run WampServer

#SQL
1.	Download MySQL Workbench https://dev.mysql.com/downloads/workbench/
2. In MySQL Workbench, connect to localhost
- SQL Details: copy connection information to be inserted into Java code: 1. database ie. "localhost:3308/transactions"; 2. user ie. "root"; 3. password ie. "";
3. Create the database & tables in MySQL Workbench

#Within Java program (IntelliJ)
1.	requires Java Database Connectivity (JDBC); Java API that manages database connection 
-	install library (platform independent): https://dev.mysql.com/downloads/connector/j/
-	start Project  right click root project folder  New  Directory name: “lib”  place downloaded .jar file in “lib” ie. mysql-connector-j-8.0.33
-	Add library to Project structure: File  Project Structure  Modules  Dependencies  + symbol  Select JAR  add .jar file that was added to lib  Apply  Okay
2. Enter previously copied SQL Details into Java code as required: path, database, user, password table, column
3. Run project
