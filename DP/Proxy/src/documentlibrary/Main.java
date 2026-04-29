
package documentlibrary;
public class Main {
    public static void main(String[] args) {

        Library library = new Library();
        AccessControlService acs = AccessControlService.getInstance();

        User alice = new User("alice");
        User bob = new User("bob");

        library.addPublicDocument("doc1", "Public content");
        library.addProtectedDocument("doc2", "Secret content");

        acs.grantAccess("alice", "doc2");

        // Public access
        System.out.println(library.getDocument("doc1").getContent(alice));

        // Protected access
        System.out.println(library.getDocument("doc2").getContent(alice));

        try {
            System.out.println(library.getDocument("doc2").getContent(bob));
        } catch (AccessDeniedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Creation date: " +
                library.getDocument("doc2").getCreationDate());
    }
}