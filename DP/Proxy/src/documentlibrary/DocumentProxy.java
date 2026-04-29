package documentlibrary;
import java.time.LocalDate;

public class DocumentProxy implements DocumentInterface {

    private Document document;
    private AccessControlService accessControl;

    public DocumentProxy(Document document) {
        this.document = document;
        this.accessControl = AccessControlService.getInstance();
    }

    public String getId() {
        return document.getId();
    }

    public LocalDate getCreationDate() {
        return document.getCreationDate();
    }

    public String getContent(User user) {
        if (accessControl.isAllowed(user.getUsername(), document.getId())) {
            return document.getContent(user);
        }
        throw new AccessDeniedException("Access denied for " + user.getUsername());
    }
}
