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
