import java.util.Scanner;

class Ex0 {

    public static double estimatePi(int numOfPoints, int seed) {
        RandomPoint.setSeed(seed);
        long pointsInCircle = 0;

        for (int i = 0; i < numOfPoints; i++) {
            Point p = new RandomPoint(0, 1, 0, 1);
            double distance = Math.sqrt(Math.pow(p.getX() - 0.5, 2) + Math.  pow(p.getY() - 0.5, 2));
            if (distance <= 0.5) {
                pointsInCircle++;
            }
        }

        return 4 * ((double) pointsInCircle / numOfPoints);
    }     
         
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numOfPoints = sc.nextInt();
        int seed = sc.nextInt();
             
        double pi = estimatePi(numOfPoints, seed);

        System.out.println(pi);
        sc.close();
    }
}
~     
