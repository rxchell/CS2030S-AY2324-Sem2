import java.util.HashMap;
import java.util.Map;

class Test3 {

  private static Product[] products;
  private static int[] quantities;
  private static UserAccount userAccount;

  private static void print(String s) {
    System.out.println("\n---- " + s + " ----");
  }

  private static ShoppingCart createShoppingCart() {
    products = new Product[] {
      new Product(5, "jPhone", 1000.0),
      new Product(10, "BT Speaker", 100.0),
      new Product(15, "Headphones", 20.0)
    };

    quantities = new int[] {1, 2, 1};
    userAccount = new UserAccount("Ah Beng", "ah.beng@hotmail.com");

    Map<Integer, Pair<Product, Integer>> cartContents = 
        new HashMap<Integer, Pair<Product, Integer>>();
    cartContents.put(5, new Pair<>(products[0], quantities[0]));
    cartContents.put(10, new Pair<>(products[1], quantities[1]));
    cartContents.put(15, new Pair<>(products[2], quantities[2]));
    ShoppingCart sc = new ShoppingCart(userAccount, cartContents);
    return sc;
  }

  public static void main(String[] args) {

    CS2030STest i = new CS2030STest();

    {
      ShoppingCart sc = createShoppingCart();
      print("Test: Shopping Cart Contents");
      System.out.println(sc.cartContents());
    }


    print("Test: Get Total");
    {
      ShoppingCart sc = createShoppingCart();
      i.expect(
          "ShoppingCart sc = createShoppingCart();\n"
          + "sc.getTotal();",
          () -> sc.getTotal(), 1220.0);
    }

    print("Test: Update Product Price");
    {
      ShoppingCart sc = createShoppingCart();
      sc.updateProductPrice(15, 50);

      ShoppingCart newSc = new ShoppingCart(userAccount, 
          Map.of(5, new Pair<>(products[0], 1), 
            10, new Pair<>(products[1], 2),
            15, new Pair<>(new Product(15, "Headphones", 50.0), 1)
            ));
      i.expect(
          "ShoppingCart sc = createShoppingCart();\n"
          + "sc.updateProductPrice(15, 100);",
          () -> sc.cartContents(), newSc.cartContents());
    }

    print("Test: Update Product Price (Incorrect product id)");
    {
      ShoppingCart sc = createShoppingCart();
      sc.updateProductPrice(11, 10000);
      i.expect(
          "ShoppingCart sc = createShoppingCart();\n"
          + "sc.updateProductPrice(11, 10000);",
          () -> sc, new ShoppingCart(userAccount, 
                                     Map.of(5, new Pair<>(products[0], 1), 
                                       10, new Pair<>(products[1], 2),
                                       15, new Pair<>(products[2], 1)
                                       )));
    }

    print("Test: Remove Product");
    {
      ShoppingCart sc = createShoppingCart();
      sc.removeProduct(10);
      i.expect(
          "ShoppingCart sc = createShoppingCart();\n"
          + "sc.removeProduct(10);",
          () -> sc, new ShoppingCart(userAccount, 
                                     Map.of(5, new Pair<>(products[0], 1), 
                                       15, new Pair<>(products[2], 1)
                                       )));
    }


    print("Test: Remove Product (Incorrect product id)");
    {
      ShoppingCart sc = createShoppingCart();
      sc.removeProduct(21);
      i.expect(
          "ShoppingCart sc = createShoppingCart();\n"
          + "sc.removeProduct(21);",
          () -> sc, new ShoppingCart(userAccount, 
                                     Map.of(5, new Pair<>(products[0], 1), 
                                       10, new Pair<>(products[1], 2),
                                       15, new Pair<>(products[2], 1)
                                       )));
    } 

    print("Test: Update Product Quantity");
    {
      ShoppingCart sc = createShoppingCart();
      sc.updateProductQuantity(5, 2);
      i.expect(
          "ShoppingCart sc = createShoppingCart();\n"
          + "sc.updateProductQuantity(5, 2);",
          () -> sc,  new ShoppingCart(userAccount, 
                                      Map.of(5, new Pair<>(products[0], 2), 
                                        10, new Pair<>(products[1], 2),
                                        15, new Pair<>(products[2], 1)
                                        )));
    }  

    print("Test: Update Product Quantity (Incorrect product id)");
    {
      ShoppingCart sc = createShoppingCart();
      sc.updateProductQuantity(55, 999);
      i.expect(
          "ShoppingCart sc = createShoppingCart();\n"
          + "updateProductQuantity(55, 999);",
          () -> sc, new ShoppingCart(userAccount, 
                                     Map.of(5, new Pair<>(products[0], 1), 
                                       10, new Pair<>(products[1], 2),
                                       15, new Pair<>(products[2], 1)
                                       )));
    }   


    print("Test: Bundle products");
    {
      ShoppingCart sc = createShoppingCart();
      sc.bundleProduct(5, 15);
      ShoppingCart newSc = new ShoppingCart(userAccount, 
          Map.of(10, new Pair<>(products[1], 2),
            5, new Pair<>(new Product(5, "jPhone Headphones (bundle)", 918.0), 1)
            ));
      i.expect(
          "ShoppingCart sc = createShoppingCart();\n"
          + "sc.bundleProduct(5, 15);",
          () -> sc.cartContents(), newSc.cartContents());
    }   

    print("Test: Bundle products (Incorrect product id)");
    {
      ShoppingCart sc = createShoppingCart();
      sc.bundleProduct(5, 12);
      i.expect(
          "ShoppingCart sc = createShoppingCart();\n"
          + "sc.bundleProduct(5, 12);",
          () -> sc, new ShoppingCart(userAccount, 
                                     Map.of(5, new Pair<>(products[0], 1), 
                                       10, new Pair<>(products[1], 2),
                                       15, new Pair<>(products[2], 1)
                                       )));
    }   

  }

}
