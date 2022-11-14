package dam.ad.library.model;

public class Author {
    private String initials;
    private String name;
    private String nationality;

    public Author(String initials, String name, String nationality) {
        this.initials = initials;
        this.name = name;
        this.nationality = nationality;
    }

    public Author() {

    }

    @Override
    public String toString() {
        return "Author{" +
                "initials='" + initials + '\'' +
                ", name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                '}';
    }

    // SETTERS
    public void setInitials(String initials) {
        this.initials = initials;
    }
}
