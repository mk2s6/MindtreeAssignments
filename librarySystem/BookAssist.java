import java.util.ArrayList;
import java.util.*;

class Book {
    public int id;
    public String name;
    public String author;
    public double price;
    public int yOP;

    Book(int id, String name, String author, double price, int yOP) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.yOP = yOP;
    }

    public void printBookDetails() {
        System.out.printf("%-5d\t%-20s%-20s%5.2f\t%20d\n", id, name, author, price, yOP);
    }
}

class Library {
    private static ArrayList<Book> books = new ArrayList<Book>(3);
    private static int id;
    private Book newBook;

    Library() {
        id = books.size() + 1;
        newBook = new Book(id++, "Robinson Crusoe", "Daniel Defoe", 15.50, 1719);
        books.add(newBook);
        newBook = new Book(id++, "Heart of darkness", "Joseph Conrad", 12.80, 1902);
        books.add(newBook);
        newBook = new Book(id++, "Beach Music", "Pat Conroy", 9.50, 1996);
        books.add(newBook);
    }

    public void addBook(String name, String author, double price, int yOP) {
        id = books.size() + 1;

        newBook = new Book(id, name, author, price, yOP);
        books.add(newBook);
    }

    public void listOfBooks() {
        System.out.printf("%-5s\t%-20s%-20s%-8s\t%-20s\n", "id", "name", "author", "price", "Year of Publication");
        for (Book book : books) {
            book.printBookDetails();
        }
    }
}

class BookAssist {
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        Library library = new Library();
        String choice;
        while (true) {
            System.out.println("\n\n------------------Please choose any of the following ----------------\n\n");
            System.out.println("1. Add a book");
            System.out.println("2. Get list of books");
            System.out.println("3. Exit");
            System.out.println("-------------------------------------------------------------------\n");
            System.out.print("Enter Your choice to proceed : ");
            choice = scan.next();
            switch (choice) {
            case "1":
                System.out.println("\n------------Adding a book to the list of books------------\n");
                System.out.print("Enter the name of the book : ");
                scan.nextLine();
                String name = scan.nextLine();
                System.out.print("Enter the name of the author of the book : ");
                String author = scan.nextLine();
                System.out.print("Enter the price of the book of the book : ");
                double price = scan.nextDouble();
                System.out.print("Enter the year of publication (YYYY) of the book : ");
                int yOP = scan.nextInt();
                library.addBook(name, author, price, yOP);
                break;
            case "2":
                library.listOfBooks();
                break;
            case "3":
                return;
            default:
                System.out.println("\n------------------Please choose only from the given options------------\n");
                break;
            }
        }
    }
}