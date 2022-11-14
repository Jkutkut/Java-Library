package dam.ad.library.model;

public class Book {
    private String title;
    private Author author;
    private int pages;

    public Book(String title, Author author, int pages) {
        this.title = title;
        this.author = author;
        this.pages = pages;
    }

    public Book() {

    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public int getPages() {
        return pages;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", " + author +
                ", pages=" + pages +
                '}';
    }

    public void setTitle(String string) {
        this.title = string;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
