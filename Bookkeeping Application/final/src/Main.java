
public class Main {
  public static void main(String[] args) throws Exception {

    BookkeepingMonthlyReport a = new BookkeepingMonthlyReport();
    a.addCategory("dog");
    a.addCategory("cat");

    Admin admin = new Admin("aaa", a);
    User user = new User("bbb", a);

    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {

        new UserMainGui(user).setVisible(true);
        new MainGui(admin, user).setVisible(true);
      }
    });
  }

}
