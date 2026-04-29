package documentlibrary;

import java.util.HashSet;
import java.util.Set;

public class AccessControlService {

    private static AccessControlService instance;

    private Set<String> permissions = new HashSet<>();

    private AccessControlService() {}

    public static AccessControlService getInstance() {
        if (instance == null) {
            instance = new AccessControlService();
        }
        return instance;
    }

    private String key(String user, String doc) {
        return user + "::" + doc;
    }

    public void grantAccess(String username, String documentId) {
        permissions.add(key(username, documentId));
    }

    public boolean isAllowed(String username, String documentId) {
        return permissions.contains(key(username, documentId));
    }
}
