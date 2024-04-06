/**
 * Test 1.
 */
class Test1 {
  private static void print(String s) {
    System.out.println("\n---- " + s + " ----");
  }

  /**
   * Main method for Test1.
   *
   * @param args Ignored and unused command line arguments.
   */
  public static void main(String[] args) {

    CS2030STest i = new CS2030STest();

    print("Test: Get the name and email of a user");
    {
      UserAccount u = new UserAccount("Ah Beng", "ah.beng@hotmail.com");
      i.expectReturn(
          "UserAccount c = new UserAccount(\"Ah Beng\", \"ah.beng@hotmail.com\");\n"
          + "c.getName();",
          () -> u.getName(), "Ah Beng");
      i.expectReturn(
          "c.getEmail();",
          () -> u.getEmail(), "ah.beng@hotmail.com");
    }

    print("Test: Set the name of a user");
    {
      UserAccount u1 = new UserAccount("Ah Beng", "ah.beng@hotmail.com");
      UserAccount u2 = u1.setName("Chewie");
      i.expectReturn(
          "UserAccount u1 = new UserAccount(\"Ah Beng\", \"ah.beng@hotmail.com\");\n"
          + "UserAccount u2 = u1.setName(\"Chewie\")\n"
          + "u1.getName();",
          () -> u1.getName(), "Ah Beng");
      i.expectReturn(
          "u2.getName();",
          () -> u2.getName(), "Chewie");
    }

    print("Test: Set the email address of a user");
    {
      UserAccount u1 = new UserAccount("Ah Beng", "ah.beng@hotmail.com");
      UserAccount u2 = u1.setEmail("ahbeng@u.nus.edu");
      i.expectReturn(
          "UserAccount u1 = new UserAccount(\"Ah Beng\", \"ah.beng@hotmail.com\");\n"
          + "UserAccount u2 = u1.setEmail(\"ahbeng@u.nus.edu\")\n"
          + "u1.getEmail();",
          () -> u1.getEmail(), "ah.beng@hotmail.com");
      i.expectReturn(
          "u2.getEmail();",
          () -> u2.getEmail(), "ahbeng@u.nus.edu");
    }
  }
}
