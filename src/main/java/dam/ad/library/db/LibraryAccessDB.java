package dam.ad.library.db;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Constraint;
import com.db4o.query.Query;
import dam.ad.library.model.Author;
import dam.ad.library.model.Book;

import java.util.ArrayList;

public class LibraryAccessDB {
    private static final String PATH_DB = "db/library.yap";
    ObjectContainer db;

    public LibraryAccessDB() {
        db = Db4oEmbedded.openFile(
            Db4oEmbedded.newConfiguration(),
            PATH_DB
        );
    }

    public void close() {
        db.close();
    }

    public void store(Object o) {
        db.store(o);
    }

    // GET
    public ArrayList<Book> getBooks() {
        Book wanted = new Book();
        ArrayList<Book> books = new ArrayList<>();

        ObjectSet<Book> result = db.queryByExample(wanted);
        books.addAll(result);
        return books;
    }

    public ArrayList<Book> getLargeBooks(int minPageSize) {
        ArrayList<Book> books = new ArrayList<>();

        Query query = db.query();
        query.constrain(Book.class);
        Constraint c = query.descend("pages").constrain(minPageSize).greater();

        ObjectSet<Book> result = query.execute();
        books.addAll(result);
        return books;
    }

    public Author getAuthor(String initials) {
        Author wanted = new Author();
        wanted.setInitials(initials);

        ObjectSet<Author> result = db.queryByExample(wanted);
        return (result.size() > 0) ? result.get(0) : null;
    }

    public Book getBook(String string) {
        Book wanted = new Book();
        wanted.setTitle(string);

        ObjectSet<Book> result = db.queryByExample(wanted);
        return (result.size() > 0) ? result.get(0) : null;
    }

    public void setPages(Book book, int pages) {
        db.delete(book);
        book.setPages(pages);
        db.store(book);
    }
}
