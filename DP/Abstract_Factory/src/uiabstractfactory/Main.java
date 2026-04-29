package uiabstractfactory;

public class Main {
    public static void main(String[] args) {

        UIFactory factory = new AFactory();
        // UIFactory factory = new BFactory();

        Button button = factory.createButton("Click Me");
        TextField textField = factory.createTextField("Enter Name");
        Checkbox checkbox = factory.createCheckbox("Accept");

        button.display();
        textField.display();
        checkbox.display();

        System.out.println("\nAfter changing text:\n");

        button.setText("Submit");
        textField.setText("John");
        checkbox.setText("Subscribed");

        button.display();
        textField.display();
        checkbox.display();
    }
}

