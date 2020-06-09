# Drone Delivery System.

## Description
This software lets the user design a route for deliveries with drones, it also makes reports of the trace and 
position of the addresses traveled by each one.   

## Design 
This system involves different actors and entities. 
1. **Owner:** is the main user, designs and organises the routes of the deliveries. 
2. **Drone:** the worker, is the one that execute deliveries.
3. **Order:** it refers to a client request.  
4. **Delivery:** it joins a group of orders and orchestrate the process.

## Use cases
- [x] Drone executing a move.
- [x] Drone delivering an order route.
- [x] Drone doing a complete delivery. 
- [x] System generating all reports.
- [x] Testing and Improving performance - General Improvement - Main Class.
- [x] Implementing Control of deliveries out of boundaries. (Message on exported file) 
## Future Work
- [ ] Customized Exceptions Handling. 
- [ ] Managing concurrence in deliveries. 
- [ ] Implementing a Queue Manager to improve requests management. 
## Considerations
* Each use case will aggregate value to system and will have unit test cases. 
* Releases will have one or more use cases
* Base Test Case has an error in Document:   
`AAAAIAA`    
`DDDAIAD`  
`AAIADAD`  
It is expecting:   
`(-2, 4) dirección Norte`    
`(-3, 3) dirección Sur`  
`(-4, 2) dirección Oriente`  
The correct result is (the Main Class has this case):  
`(-2,4) dirección Occidente`    
`(-1,3) dirección Sur`  
`(0,0) dirección Occidente`    

## Technical considerations
* Java 14 version.
* JUnit 4.13 for unit testing.
* Maven as Build Tool. 
* Log4J Used for Logging.
* Apache Commons for FileUtils.
* There is a Main Class if you want to run it and generate a sample output file. 
* Run `mvn test` to see test cases working.
* 100% of Test Coverage.
 
