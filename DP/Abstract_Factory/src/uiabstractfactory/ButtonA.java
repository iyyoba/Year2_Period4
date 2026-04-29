package uiabstractfactory;

class ButtonA extends Button {
    public ButtonA(String text) {
        super(text);
    }

    public void display() {
        System.out.println("[ " + text + " ]");
    }
}