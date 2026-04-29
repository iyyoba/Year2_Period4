package documentlibrary;

import java.time.LocalDate;

public class Document implements DocumentInterface {

    private String id;
    private LocalDate creationDate;
    private String content;

    public Document(String id, LocalDate creationDate, String content) {
        this.id = id;
        this.creationDate = creationDate;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public String getContent(User user) {
        return content;
    }
}