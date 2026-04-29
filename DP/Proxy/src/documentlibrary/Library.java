package documentlibrary;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDate;

public class Library {

    private Map<String, DocumentInterface> documents = new HashMap<>();

    public void addPublicDocument(String id, String content) {
        Document doc = new Document(id, LocalDate.now(), content);
        documents.put(id, doc);
    }

    public void addProtectedDocument(String id, String content) {
        Document doc = new Document(id, LocalDate.now(), content);
        DocumentProxy proxy = new DocumentProxy(doc);
        documents.put(id, proxy);
    }

    public DocumentInterface getDocument(String id) {
        return documents.get(id);
    }
}