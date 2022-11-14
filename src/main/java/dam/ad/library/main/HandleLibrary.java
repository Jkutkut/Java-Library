package dam.ad.library.main;

import dam.ad.library.db.LibraryAccessDB;
import dam.ad.library.model.Author;
import dam.ad.library.model.Book;
import jkutkut.SuperScanner;

import java.util.ArrayList;

public class HandleLibrary {
    private static final int MIN_PAGE_SIZE = 300;
    private static final String[] CMDS = {"IL", "ML", "CT", "CL", "S"};
    private static final String ASK_CMD = "Qué quieres hacer? [" + String.join(", ", CMDS) + "]: ";
    private static boolean running;

    public static void main(String[] args) {
        SuperScanner sc = new SuperScanner(System.in);
        running = true;
        LibraryAccessDB db = new LibraryAccessDB();

        System.out.println("Welcome to the library!");
        while (running) {
            switch (sc.getString(ASK_CMD).trim().toUpperCase()) {
                case "IL" -> addBook(sc, db);
                case "ML" -> modBook(sc, db);
                case "CT" -> getBooks(sc, db);
                case "CL" -> getLargeBooks(sc, db);
                case "S" -> exit(sc, db);
                default -> System.out.println("Comando no reconocido");
            }
        }

        db.close();
        sc.close();
        System.out.println("Gracias por usar la biblioteca!");
    }

    public static void addBook(SuperScanner sc, LibraryAccessDB db) {
        System.out.println("Introduce los datos del libro:");
        Book book = new Book(
            sc.getString("  Título: "),
            askAuthor(sc, db),
            sc.getNatural("  Páginas: ")
        );

        db.store(book);
    }

    private static Author askAuthor(SuperScanner sc, LibraryAccessDB db) {
        System.out.println("Introduce los datos del autor:");

        String initials = sc.getString("  Iniciales del autor: ");
        Author author = db.getAuthor(initials);
        if (author != null) {
            System.out.println("Autor encontrado: " + author);
            if (sc.getIntInRange("¿Quieres usarlo? [Sí: 0, No: 1] ", 0, 1) == 0)
                return author;
        }
        System.out.println("Introduce los datos del autor:");
        return new Author(
            initials,
            sc.getString("  Nombre: "),
            sc.getString("  Nacionalidad: ")
        );
    }

    private static void getBooks(SuperScanner sc, LibraryAccessDB db) {
        System.out.println("Consultar libros:");
        ArrayList<Book> books = db.getBooks();
        printBooks(books);
    }

    private static void getLargeBooks(SuperScanner sc, LibraryAccessDB db) {
        System.out.println("Consultar libros largos");
        ArrayList<Book> books = db.getLargeBooks(MIN_PAGE_SIZE);
        printBooks(books);
    }

    private static void modBook(SuperScanner sc, LibraryAccessDB db) {
        System.out.println("Modificar libro");
        Book book = db.getBook(sc.getString("  Título: "));
        if (book == null) {
            System.out.println("Libro no encontrado");
            return;
        }
        int pages = sc.getNatural("  Nuevo número de páginas: ");
        db.setPages(book, pages);
    }

    private static void exit(SuperScanner sc, LibraryAccessDB db) {
        System.out.println("Saliendo...");
        running = false;
    }

    // Print methods
    private static void printBooks(ArrayList<Book> books) {
        if (books.isEmpty()) {
            System.out.println("No se han encontrado libros.");
        } else {
            System.out.println("Libros en la biblioteca:");
            for (Book b : books) {
                System.out.println(b);
            }
        }
    }
}
