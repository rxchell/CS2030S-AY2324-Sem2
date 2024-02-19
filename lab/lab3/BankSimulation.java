import java.util.Scanner;

/**
 * This class implements a bank simulation.
 *
 * @author Rachel Tai Ke Jia 
 * @version CS2030S AY23/24 Semester 2
 */ 

class BankSimulation extends Simulation {

  /** 
   * The availability of counters in the bank. 
   */
  private Bank bank;

  /** 
   * The list of customer arrival events to populate
   * the simulation with.
   */
  private Event[] initEvents;
  private Queue<Customer> bankQueue;
  private Counter counter; 

  /** 
   * Constructor for a bank simulation. 
   *
   * @param sc A scanner to read the parameters from.  The first
   *           integer scanned is the number of customers; followed
   *           by the number of service counters.  Next is a 
   *           sequence of (arrival time, service time) pair, each
   *:          pair represents a customer.
   */ 

  public BankSimulation(Scanner sc) {
    initEvents = new Event[sc.nextInt()];

    int numOfCounters = sc.nextInt();
    int counterQueueLength = sc.nextInt();
    int bankQueueLength = sc.nextInt();

    Queue<Customer> bankQueue = new Queue<Customer>(bankQueueLength);
    bank = new Bank(numOfCounters, counterQueueLength, bankQueue);

    int id = 0;
    while (sc.hasNextDouble()) {
      double arrivalTime = sc.nextDouble();
      double serviceTime = sc.nextDouble();

      int task = sc.nextInt();
      int amount = sc.nextInt();
   
      Customer c = new Customer(id, arrivalTime, serviceTime, task, amount);
      
      initEvents[id] = new ArrivalEvent(arrivalTime, c, bank);
      
      id += 1;
    }
  }
 

  /**
   * Retrieve an array of events to populate the 
   * simulator with.
   *
   * @return An array of events for the simulator.
   */
  @Override
  public Event[] getInitialEvents() {
    return initEvents;
  }
}
