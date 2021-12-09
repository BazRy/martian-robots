Prerequisites
-------------
Java 1.8 or above
Maven 3.5.0 or above
Both the above need to be present on the path.

How to build
------------
Navigate to the project's root using a command line.
From the project root, run `mvn clean install`

How to run
------------
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
