Bank
- queue, length m

```Java
if counter.getisavailable = null; 
customer.queue

else 
counter.serve
customer goes to first available counter. service begins. 


if queue.isavailable = null
customer departs. 
```

https://nus-cs2030s.github.io/2324-s2/exercises/ex2-task.html

- joined queue should be printed immediately after arrive. timing of join queue and arrive should be the same

#FEEDBACK 
- arrival and serviceend event call bank to processservice. no need queue as parameter
- bank: processArrival, processEnterQueue, processDeparture, processServiceEnds, processNextCustomerFromQueue,
- ONLY BANK HAS ACCESS TO QUEUE

- Abstract class: Task
- concrete class: Withdrawal and Deposit
```Java
Class Withdrawal task extends Task {
  @override
  public Stringtostring() {
    return "withdrawal";
  }
}
```
- `@override`

- cos u need to read from scanner the integer right, and the int is either 0 or 1
- so in bank simulation, u can do if the int is 0, then instantiate an instance of Withdrawal (or Deposit, depending on the ex description), then pass that Withdrawal instance to the Customer constructor
- if u do in Customer constructor, u can pass the int to the constructor, then check if the int is 0, then create a new instance of Withdrawal and assign it to a field


