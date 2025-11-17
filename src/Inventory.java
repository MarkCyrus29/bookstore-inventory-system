import java.util.Queue;
import java.util.Scanner;
import java.util.LinkedList;

public class Inventory {
  private static LinkedList<Book> inventory = new LinkedList<>();
  private static Queue<String> orderQueue = new LinkedList<>();
  private static Scanner sc = new Scanner(System.in);

  public static void main(String[] args) {
    System.out.println("Welcome to the Bookstore Inventory Management System!");

    boolean isRunning = true;
    while (isRunning){
      System.out.println("\nPlease choose an option:");
      System.out.println("" +
          "1. Add a new book\n" +
          "2. Display all books\n" +
          "3. Sort books by title\n" +
          "4. Search for a book by title\n" +
          "5. Add a customer order to the queue\n" +
          "6. Process the next customer order\n" +
          "7. Exit");

      System.out.print("\nEnter your choice: ");
      String choice = sc.nextLine();
      switch (choice){
        case "1": addBook(sc); break;
        case "2": displayAllBooks(); break;
        case "3": sortBooksByTitle(); break;
        case "4": searchBookByTitle(sc); break;
        case "5": addOrderToQueue(sc); break;
        case "6": processNextOrder(); break;
        case "7":
          System.out.println("Thank you for using the Bookstore Inventory Management System!");
          isRunning = false;
          sc.close();
          break;
        default:
          System.out.println("Input a number from 1 to 7");
      }
    }

  }

  //add book method
  private static void addBook(Scanner sc){
    System.out.print("Enter the title of the book: ");
    String title = sc.nextLine();
    System.out.print("Enter the author of the book: ");
    String author = sc.nextLine();
    System.out.print("Enter the isbn of the book: ");
    String isbn = sc.nextLine();
    System.out.print("Enter the price of the book: ");
    double price = sc.nextDouble();
    sc.nextLine();

    Book book = new Book(title, author, isbn, price);
    inventory.offer(book);
    System.out.println("Book added successfully!");
  }

  //display all books method
  private static void displayAllBooks(){
    if (inventory.isEmpty()){
      System.out.println("Inventory is Empty! (add some books first :p)");
      return;
    }
    System.out.println("--- All Books in Inventory ---");
    for (int i = 0; i < inventory.size(); i++) {
      System.out.println(inventory.get(i).toString());
    }
    System.out.println("\n-----------------------------");
  }

  //sort books by title method
  private static void sortBooksByTitle() {
    if (inventory.size() <= 1) {
      System.out.println("Not enough books to sort.");
      return;
    }

    System.out.println("Sorting books by title...");
    int n = inventory.size();
    boolean swapped;
    // bubble sort
    for (int i = 0; i < n - 1; i++) {
      swapped = false;
      for (int j = 0; j < n - 1 - i; j++) {
        String title1 = inventory.get(j).getTitle();
        String title2 = inventory.get(j + 1).getTitle();
        if (title1.compareToIgnoreCase(title2) > 0) {
          Book temp = inventory.get(j);
          inventory.set(j, inventory.get(j + 1));
          inventory.set(j + 1, temp);
          swapped = true;
        }
      }
      if (!swapped) break;
    }
    System.out.println("Books sorted successfully!");
  }

  //search book by title
  private static void searchBookByTitle(Scanner sc){
    if (inventory.isEmpty()) {
      System.out.println("Inventory is empty. No books to search.");
      return;
    }
    System.out.print("Enter the title of the book to search for: ");
    String titleKey = sc.nextLine().trim();;
    boolean found = true;

    for (Book book : inventory) {
      if (book.getTitle().equalsIgnoreCase(titleKey)) {
        System.out.println("Book found:");
        System.out.println(book.toString());
        return;
      } else {
        found = false;
      }
    }
    if (!found) {
      System.out.println("Book not found.");
    }
  }

  //add order to the orderQueue
  private static void addOrderToQueue(Scanner sc){
    System.out.print("Enter the title of the book to order: ");
    String title = sc.nextLine();
    orderQueue.offer(title);
    System.out.println("Order for \""+ title +"\" has been added to the queue.");
  }

  // process next order
  private static void processNextOrder(){
    if (orderQueue.isEmpty()){
      System.out.println("Order Queue is Empty.");
    } else {
      System.out.println("Processing next order...");
      System.out.println("Processed order for: " + orderQueue.peek());
      orderQueue.poll();
    }
  }
}
