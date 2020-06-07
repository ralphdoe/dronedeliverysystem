# Drone Delivery System.

## Description
This software lets the user design a route for deliveries with drones, it also makes reports of the trace and 
position of the addresses traveled by each one.   

## Design 
This system involves different actors and entities. 
1. **Owner:** is the main user, designs and organises the routes of the deliveries. 
2. **Drone:** the worker, is the one that execute deliveries.
3. **Order:** it refers to a client request. 
4. **Route:** a defined path to arrive to an address. 
5. **Delivery:** it joins a group of orders and orchestrate the process.

## Use cases
- [ ] Drone doing an order route.
- [ ] Drone doing a complete delivery. 
- [ ] System generating all reports.
- [ ] Testing and Improving performance. 
- [ ] Implementing Control of deliveries out of boundaries. 
- [ ] Managing concurrence in deliveries. 
- [ ] Implementing a Queue Manager to improve requests management. 
## Considerations
* Each use case will aggregate value to system and will have unit test cases. 
* Releases will have one or more use cases.
## Technical aspects
* Java 14 version.
* JUnit 4.13 for unit testing.
* Maven as Build Tool. 
 
