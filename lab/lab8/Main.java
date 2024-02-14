import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
/**
 * This program finds different ways one can travel by bus (with a bit 
 * of walking) from one bus stop to another.
 *
 * @author: Lee Zheng Jing (Lab 08K)
 * @version: CS2030S AY22/23 Semester 2, Lab 8
 */

public class Main {
  /**
   * The program read a sequence of (id, search string) from either
   * standard input or a file.  If an invalid filename is given, the 
   * program would quit with an error message.
   * @param args Command line arguments
   */
  public static void main(String[] args) {
    Instant start = Instant.now();
    try {
      ArrayList<CompletableFuture<?>> arrayLst = new ArrayList<>();
      Scanner sc = createScanner(args);
      while (sc.hasNext()) {
        BusStop srcId = new BusStop(sc.next());
        String searchString = sc.next();
        CompletableFuture<String> next = BusSg.findBusServicesBetween(srcId, searchString)
            .thenComposeAsync(x -> x.description()
              .thenApplyAsync(y -> {
                System.out.println(y);
                return y;
              }));
        //System.out.println(BusSg.findBusServicesBetween(srcId, searchString).description());
        arrayLst.add(next);
      }
      CompletableFuture<?>[] array = new CompletableFuture<?>[0];
      CompletableFuture.allOf(arrayLst.toArray(array)).join();
      sc.close();
    } catch (FileNotFoundException exception) {
      System.err.println("Unable to open file " + args[0] + " "
          + exception);
    }
    Instant stop = Instant.now();
    System.out.printf("Took %,dms\n", Duration.between(start, stop).toMillis());
  }

  /**
   * Create and return a scanner. If a command line argument is given,
   * treat the argument as a file and open a scanner on the file. Else,
   * create a scanner that reads from standard input.
   *
   * @param args The arguments provided for simulation.
   * @return A scanner.
   * @throws FileNotFoundException Throws if filename given is not valid.
   */
  private static Scanner createScanner(String[] args) throws FileNotFoundException {
    // Read from stdin if no filename is given, otherwise read from the
    // given file.
    if (args.length == 0) {
      // If there is no argument, read from standard input.
      return new Scanner(System.in);
    } 
    // Else read from file
    FileReader fileReader = new FileReader(args[0]);
    return new Scanner(fileReader);
  }
}
