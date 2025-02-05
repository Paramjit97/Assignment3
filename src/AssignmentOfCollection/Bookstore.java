package AssignmentOfCollection;

import java.util.*;

public class Bookstore {
    private static List<Book> availableBooks = new ArrayList<>();
    private static List<Book> purchasedBooks = new LinkedList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        availableBooks.add(new Book("b12", "abc", "author1", 20.34, 7));
        availableBooks.add(new Book("b13", "def", "author2", 90.04, 8));
        availableBooks.add(new Book("b14", "ghi", "author3", 24.34, 10));
        availableBooks.add(new Book("b15", "jkl", "author4", 29.34, 6));

        System.out.println("Enter the book details to add:");

        System.out.print("Enter Book ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter Book Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter Author Name: ");
        String author = scanner.nextLine();

        System.out.print("Enter Book Price: ");
        double price = scanner.nextDouble();

        System.out.print("Enter Book Quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Book book = new Book(id, title, author, price, quantity);

        addBook(book);
        System.out.println("Enter the bookid you want to remove:");
        String bookid=scanner.next();
        remove(bookid);
        viewBooks();
        try {
            System.out.println("Enter the id of the book you want to purchase");

            String bookidPurchase = scanner.next();
            System.out.println("How many books you want to purchase:");
            int bookQuantity = scanner.nextInt();
            purchaseBooks(bookidPurchase, bookQuantity);
        }
        catch (BookstoreException e){
            System.out.println("Error message:"+e.getMessage());
        }

        //search book call
        try{
            System.out.println("Enter the book title");
            String bookTitle= scanner.next();
            searchBook(bookTitle);
        }
        catch (BookstoreException e){
            System.out.println("Error:"+e.getMessage());
        }

        getInventoryValue();
        listPurchasedBooks();
        System.out.println("The unique author list is:" + uniqueAuthor());
        booksTitle();
    }

    //add books
    public static void addBook(Book book) {
        availableBooks.add(book);
        System.out.println("Book added successfully!");
        System.out.println(availableBooks);
    }
    //remove books
    public static void remove(String bookid) {
        ListIterator<Book> bookRemove = availableBooks.listIterator();
        while (bookRemove.hasNext()) {
           Book book=bookRemove.next();
                if(book.getBookId().equalsIgnoreCase(bookid)){
                    bookRemove.remove();
                    System.out.println("Book is removed !");
                    break;
                }

        }
        System.out.println(availableBooks);
    }

    //viewbook
    public static void viewBooks(){
            System.out.println("Total number of books:"+availableBooks);

    }
    //purchasebooks
    public static void purchaseBooks(String bookid, int quantity) throws BookstoreException {
        boolean bookAvailable = false;
        ListIterator<Book> bookPurchase = availableBooks.listIterator();

        while (bookPurchase.hasNext()) {
            Book book = bookPurchase.next();

            if (bookid.equalsIgnoreCase(book.getBookId())) {

                if (book.getQuantity() >= quantity) {
                    System.out.println("It is available to purchase");
                    purchasedBooks.add(book);

                    book.setQuantity(book.getQuantity() - quantity);

                    System.out.println("Book purchased successfully");


                    if (book.getQuantity() == 0) {
                        bookPurchase.remove();
                    }

                    bookAvailable = true;
                    break;
                } else {
                    throw new BookstoreException("Book is unavailable");
                }
            }
        }

        if (!bookAvailable) {
            System.out.println("Sorry, The book is not available.");
        }

        viewBooks();
    }
    //search books

    public static void searchBook(String title)throws  BookstoreException{
        boolean bookFound=false;
 for (Book book:availableBooks) {
     if (title.equalsIgnoreCase(book.getTitle())) {
         bookFound = true;
         System.out.println(book);
     }
 }
     if(!bookFound){
         throw new BookstoreException("No such book available ,sorry");
     }
    }

    //getInventoryValue
    public static void getInventoryValue(){
        double totalInventory=0;
        for(Book book:availableBooks){
            totalInventory+= book.getQuantity()*book.getPrice();
        }
        System.out.println("Total inventory is:"+totalInventory);
    }

    //purchaes list
    public static void listPurchasedBooks(){
        System.out.println("List of Purchased Books:"+purchasedBooks);
    }

    //5.Set Collections
    public static Set<String> uniqueAuthor() {
        Set<String> uniqueAuthor = new HashSet<>();
        for(Book book:availableBooks) {
            uniqueAuthor.add(book.getAuthor());
        }
        return uniqueAuthor;
    }

    //treeSet books ordered by their title
    public static void booksTitle(){
        Set<String> bookTitle = new TreeSet<>();
        for(Book book:availableBooks){
            bookTitle.add((book.getTitle())+" "+book.getAuthor()+" "+book.getBookId());

        }
        System.out.println(bookTitle);

    }
}
