### Bank

bank counters

- In the beginning, all bank counters are available.
- A counter becomes unavailable when it is serving a customer, and
- becomes available again after servicing a customer.

customer 

- A customer, upon arrival at the bank, goes to the first available counter.
- If no counter is available, the customer departs
- Otherwise, the customer is served by a counter.
- After being served for **service time**, the customer departs.


### `Event` class (do not edit)
- abstract class with a single field `time`
- `Event::toString` method returns the time as a string
- `Event::getTime` method returns the time

- **abstract method `simulate` needs to be overridden by its subclass to concretely define the action to be taken when this event occurs.**
  
- `Event::simulate` returns an array of Event instances. Simulating an event can lead to more events being created.

### `Simulation` class (do not edit)
- abstract class
- single method `getInitialEvents`, which returns an array of events to simulate. Each of these events may generate more events.

### `Simulator` class (do not edit)
```Java
Simulation sim = new SomeSimulation();   // initialize it with a Simulation instance
new Simulator(sim).run();     // call run
```

`Simulation::run` method:
- It gets the list of initial Event objects from the Simulation object;
- It then simulates the pool of events, one by one in the order of increasing time, by calling `Event::simulate`;
- If simulating an event results in one or more new events, the new events are added to the pool.
- Before each event is simulated, `Event::toString` is called and a message is printed
- The simulation stops running if there are no more events to simulate.

### `BankSimulation` class 





  


