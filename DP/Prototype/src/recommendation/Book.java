package recommendation;

class Book implements Cloneable {
    private String title;
    private String author;
    private String genre;
    private int publicationYear;

    public Book(String title, String author, String genre, int publicationYear) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publicationYear = publicationYear;
    }

    public Book clone() {
        return new Book(title, author, genre, publicationYear);
    }

    @Override
    public String toString() {
        return title + " by " + author + " (" + genre + ", " + publicationYear + ")";
    }
}