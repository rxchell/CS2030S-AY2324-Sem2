// STATIC method to call on a CLASS
class RandomPoint extends Point {
    private static final long DEFAULT_SEED = 1; // set default seed 
    private static Random rng = new Random(DEFAULT_SEED);

    public RandomPoint(double minX, double maxX, double minY, double maxY)   {
      super(rng.nextDouble() * (maxX - minX) + minX, rng.nextDouble() *      (maxY - minY) + minY);
    }

    public static void setSeed(long seed) {
        rng.setSeed(seed);
    }

    private static double generateRandom(double min, double max) {
        return rng.nextDouble() * (max - min) + min;
    }
}


//
Java provides a class java.util.Random that encapsulates a pseudo-random number generator. We can create a random number generator with a seed of 1:

Random rng = new Random(1);

We can then call rng.nextDouble() repeatedly to generate random numbers between 0 and 1.



If we reinitialized rng again with another random generator, with a different seed,

rng = new Random(2);
Calling rng.nextDouble() produces a different sequence. But now, let's say that you reinitialized rng with the seed of 1 again:

rng = new Random(1);
rng will produce the same sequence as when the seed was 1.

    
Using a fixed seed is important for testing since the execution of the program will be deterministic, even when random numbers are involved.

RandomPoint is a subclass of Point that represents a randomly generated point. The random number generator that generates a random point has a default seed of 1. There is a public method setSeed() that we can use to update the seed. Here is how it can be used:

    
To generate a new point,

Point p = new RandomPoint(minX, maxX, minY, maxY); 
minX, minY, maxX, maxY represent the minimum and maximum possible x and y values respectively, for each randomly generated point.

    
To set the random seed,
RandomPoint.setSeed(10);
