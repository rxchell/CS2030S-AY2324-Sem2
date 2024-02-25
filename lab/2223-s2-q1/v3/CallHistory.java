/**
 * CS2030S AY22/23 Sem 2 PE1
 * @author: STUDENT ID
 * */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Encapsulate a list of phone call history.
 */
class CallHistory {
  private int numOfCalls; // The number of calls in the list.
  private Array<Integer> typeArray; // Store the type of Call i.
  private Array<Boolean> isMissedArray; // Store whether Call i is a missed call.
  private Array<Integer> numberArray; // Store the phone number of Call i (if any).
  private Array<Integer> lengthArray; // Store the length of Call i.
  private Array<String> nameArray; // Store the name of the caller of Call i (if any).

  // The types of calls
  private static final int NO_CALLER_ID = 0;
  private static final int UNKNOWN_CALLER = 1;
  private static final int CONTACT = 2;

  /**
   * A constructor that reads in the list from the standard input.
   **/
  public CallHistory() {
    this(new Scanner(System.in));
  }

  /**
   * A constructor that reads in the list from the standard input.
   * @throws FileNotFoundException
   **/
  public CallHistory(String filename) throws FileNotFoundException {
    this(new Scanner(new File(filename)));
  }

  /**
   * A constructor that reads in the list using the given scanner.
   * @param sc The Scanner to read from.
   **/
  private CallHistory(Scanner sc) {
    this.loadCalls(sc);
  }

  /**
   * Load the calls from the given scanner.
   * @param sc The scanner to load from.
   **/
  private void loadCalls(Scanner sc) {
    this.numOfCalls = Integer.parseInt(sc.nextLine().trim());
    this.typeArray = new Array<Integer>(numOfCalls);
    this.isMissedArray = new Array<Boolean>(numOfCalls);
    this.numberArray = new Array<Integer>(numOfCalls);
    this.lengthArray = new Array<Integer>(numOfCalls);
    this.nameArray = new Array<String>(numOfCalls);

    int i = 0;
    while (sc.hasNext()) {
      String text = sc.nextLine().trim();
      String[] arguments = text.split(",");
      this.createCall(i, arguments);
      i = i + 1;
    }
  }

  /**
   * Create Call i with the given arguments.
   * @param i The index of the task.
   * @param args The arguments read from the input.
   * @return false if the input contains a wrong type; true otherwise.
   **/
  private void createCall(int i, String[] args) { 
    int type = Integer.parseInt(args[0]);
    this.typeArray.set(i, type);

    int length = Integer.parseInt(args[1]);
    this.lengthArray.set(i, length);

    if (length == -1) {
      this.isMissedArray.set(i, true);
    } else {
      this.isMissedArray.set(i, false);
    }

    if (type == CallHistory.NO_CALLER_ID) {
      // no further action

    } else if (type == CallHistory.UNKNOWN_CALLER) {
      int number = Integer.parseInt(args[2]);

      this.numberArray.set(i, number);

    } else if (type == CallHistory.CONTACT) {
      int number = Integer.parseInt(args[2]);
      String name = args[3];

      this.numberArray.set(i, number);
      this.nameArray.set(i, name);
    }
  }

  /**
   * Return a string representation of a call number.
   * @param i The index of the call.
   * @return The string representation of Call i.
   **/
  private String callNumberOf(int i) {
    int type = this.typeArray.get(i);
    if (type == CallHistory.NO_CALLER_ID) {
      return "No Caller ID";
    } else {
      int number = this.numberArray.get(i);
      return  "" + number;
    }
  }

  /**
   * Print the phone number of all calls.
   **/
  public void printNumbers() {
    for (int i = 0; i < this.numOfCalls; i++) {
      System.out.println(i + " " + this.callNumberOf(i));
    }
  }

  /**
   * Return a detailed string representation of a call number.
   * @param i The index of the call.
   * @return The string representation of Call i.
   **/
  private String callDetailsOf(int i) {
    String s = "";
    int type = this.typeArray.get(i);
    if (type == CallHistory.NO_CALLER_ID) {
      s = s + "No Caller ID";
    } else {
      int number = this.numberArray.get(i);
      s = s + number;
    }

    boolean isMissed = this.isMissedArray.get(i);
    if (isMissed) {
      s += " | [MISSED]";
    } else {
      int length = this.lengthArray.get(i);
      s += " | " + length + " minutes";
    }
    if (type == CallHistory.CONTACT) {
      String name = this.nameArray.get(i);
      s += " | " + name;
    }
    return s;
  }

  /**
   * Print the details of all calls.
   **/
  public void printAllCalls() {
    for (int i = 0; i < this.numOfCalls; i++) {
      System.out.println(i + " " + this.callDetailsOf(i));
    }
  }

  /**
   * Calculate the total time on call.
   * @return The total time on call in minutes.
   **/
  public int getMinutesOnCall() {
    int sum = 0;
    for (int i = 0; i < this.numOfCalls; i++) {
      boolean isMissed = this.isMissedArray.get(i);

      if (!isMissed) {
        int length = this.lengthArray.get(i);
        sum += length;
      }
    }
    return sum;
  }

  /**
   * Print all missed calls from known contacts.
   **/
  public void printMissedCalls() {
    for (int i = 0; i < this.numOfCalls; i++) {
      int type = this.typeArray.get(i);

      if (type == CallHistory.CONTACT) {
        boolean isMissed = this.isMissedArray.get(i);

        if (isMissed) {
          System.out.println(i + " " + callDetailsOf(i));
        }
      }
    }
  }

  /**
   * Make a returned call to Call i that lasts a given time.
   * @param i The index of the Call to return.
   * @param length The length of the call in minutes.
   **/
  public void callback(int i, int length) {
    int type = this.typeArray.get(i);

    if (type == CallHistory.NO_CALLER_ID) {
      System.out.println("Unable to call back: No Caller ID");
    } else {
      boolean isMissed = this.isMissedArray.get(i);

      if (!isMissed) {
        int currLength = this.lengthArray.get(i);
        lengthArray.set(i, currLength + length);
      } else {
        lengthArray.set(i, length);
        isMissedArray.set(i, false);
      }
    }
  }
}
