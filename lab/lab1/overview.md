Nouns (class names)
- Counter
- Customer
- Bank

Attributes: 
- Time (Events)
- Counters (Bank)
- Availability (Counters)

Verbs (methods)
- `Counter` serves method which accepts `customer`
- Events alter states & generate events
- Customer arrives & goes to counter & departs

Abstract class 
- Need details of event type or what simulation simulates (no concrete implementation is possible)
- `Events` (multiple kinds of events eg arrival, service start, service ends which can be child classes)
- `Simulation` (only one implementation - BankSimulation)

BankEvent 
- replace with other types of events eg arrival

Encapsulation 
- `customer` should not access info from `counter`
  


### Bank

HAS Counters

- In the beginning, all bank counters are available.
- A counter becomes unavailable when it is serving a customer, and
- becomes available again after servicing a customer.

HAS Customer 

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
- concrete implementation of a Simulation
- stores information about counters

- reading the inputs from the standard inputs,
- initialize the bank counters (represented with boolean available arrays)
- initialize the events corresponding to customer arrivals
- return the list of customer arrival events to the Simulator object when getInitialEvent is called.

- Each customer has an ID. The first customer has id 0, the next one is 1, and so on.
- Each counter has an ID, numbered from 0, 1, 2 and onwards.

### `BankEvent` class (replace with new classes)
- concrete implementation of Event
- stores and uses info about bank, customers & counters

- This class overrides the simulate method to simulate the customer and counter behavior.

A BankEvent instance can be tagged as either an arrival event, service-begin event, service-end event, or departure event.
- Arrival: the customer arrives. It finds the first available bank counter (scanning from ID upwards) and goes to the counter for service immediately. A service-begin event is generated. If no counter is available, it departs. A departure event is generated.
- Service-begin: the customer is being served. A service-end event scheduled at the time (current time + service time) is generated.
- Service-end: the customer is done being served and departs immediately. A departure event is generated.
- Departure: the customer departs.






  


