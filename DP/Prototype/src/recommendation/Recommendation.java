package recommendation;

import java.util.ArrayList;
import java.util.List;

class Recommendation implements Cloneable {
    private String targetAudience;
    private List<Book> books;

    public Recommendation(String targetAudience) {
        this.targetAudience = targetAudience;
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(int index) {
        if (index >= 0 && index < books.size()) {
            books.remove(index);
        }
    }

    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }

    public Recommendation clone() {
        Recommendation cloned = new Recommendation(this.targetAudience);

        // 🔑 Deep copy happens here
        for (Book book : books) {
            cloned.addBook(book.clone());
        }

        return cloned;
    }

    public void display() {
        System.out.println("Audience: " + targetAudience);
        for (int i = 0; i < books.size(); i++) {
            System.out.println((i + 1) + ". " + books.get(i));
        }
    }
}