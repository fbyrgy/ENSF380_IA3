# Disaster Relief System
###### Created by: Deven Powell (UCID: 30173192)

This disaster relief system allows for the creation, storage, and access for various locations, disaster victims, and inquirers. It's main purpose is to provide a user-interface for relief workers to keep track of where individuals are housed, and to aid in the reunification of family members. 

### File Structure and Dependencies
All *.java files that are required for the program to run are included in the edu.ucalgary.oop package. For the test files to work, [Junit and Hamcrest-Core](https://github.com/junit-team/junit4?tab=readme-ov-file) will need to be installed <u>outside</u> of the edu folder. In order for the database access to work, [PostgreSQL](https://www.postgresql.org/download/) and the [JDBC Driver](https://jdbc.postgresql.org/download/) are required.

### Database Access
In PostgreSQL, login with user 'oop' and password 'ucalgary' (or enter the script "CREATE USER oop PASSWORD 'ucalgary';", if this user has not been created). The included file "project.sql" has minor changes from the one provided in D2L, namely that the "id" was removed from the INSERT statement as it caused issues with SERIAL. Copy the first three lines of the file into the psql shell, and execute the statement (if there is an error along the lines of "user does not have permission to create database, the script "ALTER USER oop CREATEDB;" executed as admin will fix it). Now, copy the rest of the file into the shell and execute (data in the tables may be changed). 

### Compilation
This program can be compiled and run in the command line using javac. The command will slightly differ depending on the operating system. The "main" method is located in `UserInterface.java`.
<b>Unix (Mac, Linux):</b>
1. `cd` to the directory in which the edu.ucalgary.oop package is located
2. To compile: `javac -cp ".:{path to JDBC driver}" edu/ucalgary/oop/UserInterface.java`
3. To run: `java -cp ".:{path to JDBC driver}" edu.ucalgary.oop.UserInterface`

<b>Windows (PowerShell, Command Prompt):</b>
1. `cd` to the directory in which the edu.ucalgary.oop package is located
2. To compile: `javac -cp ".;{path to JDBC driver}" edu/ucalgary/oop/UserInterface.java`
3. To run: `java -cp ".;{path to JDBC driver}" edu.ucalgary.oop.UserInterface`