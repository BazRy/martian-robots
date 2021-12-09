Prerequisites
-------------
Java 1.8 or above
Maven 3.5.0 or above
Both the above need to be present on the path.

How to build
------------
Navigate to the project's root using a command line.
From the project root, run `mvn clean install`
The following should appear:

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------

How to run tests
----------------
Navigate to the project's root using a command line.
From the project root, run `mvn test`
The following should appear:
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.lme.martian.robot.RobotTest
[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.074 s - in com.lme.martian.robot.RobotTest
[INFO] Running com.lme.martian.grid.GridTest
[INFO] Tests run: 14, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.035 s - in com.lme.martian.grid.GridTest
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 19, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------

How to run application
-----------------------
From a command line,  navigate to the project root
run `java -jar target/martian-robots-0.0.1-SNAPSHOT.jar MartianRobotsApplication`

The following prompts will appear in order:

1) Please Enter Upper Right Grid X & Y Coordinates (separated by a space).  Enter `end` to halt programme
sample input - `5 3`

2) Please Enter Robot Start Position - X & Y Coordinates & NSEW Orientation (separated by a space)
sample input -  `1 1 E`

3) Please Enter Robot Instructions (no spaces),  acceptable values are: [L R F]
sample input - 	`RFRFRFRF`

Sample out from the above - 1 1 E

Prompts 2 & 3 will recursively appear to initialise and instruct new Robots,  to terminate type `end` and hit return
