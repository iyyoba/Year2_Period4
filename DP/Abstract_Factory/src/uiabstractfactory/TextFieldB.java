package uiabstractfactory;

class TextFieldB extends TextField {
    public TextFieldB(String text) {
        super(text);
    }

    public void display() {
        System.out.println("|| " + text + " ||");
    }
}