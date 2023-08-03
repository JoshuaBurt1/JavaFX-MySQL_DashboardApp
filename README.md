# Connecting to MySQL with IntelliJ

1.	Download a test server ie. WampServer https://sourceforge.net/projects/wampserver/
2.	Download MySQL Workbench https://dev.mysql.com/downloads/workbench/ 
3.	Run WampServer
4.	In MySQL Workbench, connect to localhost
-copy: 1. database name ie. "jdbc:mysql://localhost:3308/gamedatabase"; 2. user ie. "root"; 3. password ie. "";
5.	requires Java Database Connectivity (JDBC); Java API that manages database connection
-	install library (platform independent): https://dev.mysql.com/downloads/connector/j/
-	start Project  right click root project folder  New  Directory name: “lib”  place downloaded .jar file in “lib” ie. mysql-connector-j-8.0.33
-	Add library to Project structure: File  Project Structure  Modules  Dependencies  + symbol  Select JAR  add .jar file that was added to lib  Apply  Okay
6.	Create the database & tables in MySQL Workbench  copy path, database, table, column details into Java code
