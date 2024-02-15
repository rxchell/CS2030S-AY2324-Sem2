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
class withdrawal task extends task{
  @override
  public Stringtostring() {
    return "withdrawal";
  }
}
```
- `@override`


