package uiabstractfactory;

class ButtonB extends Button {
    public ButtonB(String text) {
        super(text);
    }

    public void display() {
        System.out.println("<< " + text + " >>");
    }
}