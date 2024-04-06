/**
 * Test 2.
 */
class Test2 {
  private static void print(String s) {
    System.out.println("\n---- " + s + " ----");
  }

  /**
   * Main method for Test2.
   *
   * @param args Ignored and unused command line arguments.
   */
  public static void main(String[] args) {

    CS2030STest i = new CS2030STest();

    print("Test: Get name, price and ID of product");
    {
      Product p = new Product(5, "jPhone", 1000.0);
      i.expectReturn("Product p = new Product(5, \"jPhone\", 1000.0);\n"
          + "p.getName();",
          () -> p.getName(), "jPhone");
      i.expectReturn(
          "p.getPrice();",
          () -> p.getPrice(), 1000.0);
      i.expectReturn(
          "p.getProductId();",
          () -> p.getProductId(), 5);
    }

    print("Test: Set the price of the product");
    {
      Product p1 = new Product(5, "jPhone", 1000.0);
      Product p2 = p1.setPrice(1200.0);
      i.expectReturn(
          "Product p1 = new Product(5, \"jPhone\", 1000.0);\n"
          + "Product p2 = p1.setPrice(1200.0);\n" 
          + "p1.getPrice();",
          () -> p1.getPrice(), 1000.0);
      i.expectReturn(
          "p2.getPrice();",
          () -> p2.getPrice(), 1200.0);
    }

    print("Test: Set the name of the product");
    {
      Product p1 = new Product(5, "jPhone", 1000.0);
      Product p2 = p1.setName("jPhone+");
      i.expectReturn(
          "Product p1 = new Product(5, \"jPhone\", 1000.0);\n"
          + "Product p2 = p1.setName(\"jPhone+\");\n" 
          + "p1.getName();",
          () -> p1.getName(), "jPhone");
      i.expectReturn(
          "p2.getName();",
          () -> p2.getName(), "jPhone+");
    }

    print("Test: Set the ID of the product");
    {
      Product p1 = new Product(5, "jPhone", 1000.0);
      Product p2 = p1.setProductId(6);
      i.expectReturn(
          "Product p1 = new Product(5, \"jPhone\", 1000.0);\n"
          + "Product p2 = p1.setProductId(6);\n" 
          + "p1.getProductId();",
          () -> p1.getProductId(), 5);
      i.expectReturn(
          "p2.getProductId();",
          () -> p2.getProductId(), 6);
    }

    print("Test: Bundling of products");
    {
      Product p1 = new Product(5, "jPhone", 1000.0);
      Product p2 = new Product(10, "BT Speaker", 100.0); 
      Product p3 = p1.bundle(p2);
      i.expectReturn(
          "Product p1 = new Product(5, \"jPhone\", 1000.0);\n"
          + "Product p2 = new Product(10, \"BT Speaker\", 100.0);\n" 
          + "Product p3 = p1.bundle(p2);\n" 
          + "p3.getProductId();",
          () -> p3.getProductId(), 5);
      i.expectReturn(
          "p3.getName();",
          () -> p3.getName(), "jPhone BT Speaker (bundle)");
      i.expectReturn(
          "p3.getPrice();",
          () -> p3.getPrice(), 990.0);
    }

    print("Test: Swap prices of products");
    {
      Product p1 = new Product(5, "jPhone", 1000.0);
      Product p2 = new Product(10, "BT Speaker", 100.0); 
      Pair<Product, Product> pair = p1.swapPrice(p2);
      Product p3 = pair.getFirst();
      Product p4 = pair.getSecond();
      i.expectReturn(
          "Product p1 = new Product(5, \"jPhone\", 1000.0);\n"
          + "Product p2 = new Product(10, \"BT Speaker\", 100.0);\n" 
          + "Pair<Product,Product> pair = p1.swapPrice(p2);\n" 
          + "Product p3 = pair.getFirst();\n"
          + "Product p4 = pair.getSecond();\n"
          + "p1.getPrice();",
          () -> p1.getPrice(), 1000.0);

      i.expectReturn(
          "p2.getPrice();",
          () -> p2.getPrice(), 100.0);

      i.expectReturn(
          "p3.getPrice();",
          () -> p3.getPrice(), 100.0);

      i.expectReturn(
          "p4.getPrice();",
          () -> p4.getPrice(), 1000.0);
    }
  }
}
