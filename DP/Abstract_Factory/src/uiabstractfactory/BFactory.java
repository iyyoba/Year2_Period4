package uiabstractfactory;

class BFactory extends UIFactory {
    public Button createButton(String text) {
        return new ButtonB(text);
    }

    public TextField createTextField(String text) {
        return new TextFieldB(text);
    }

    public Checkbox createCheckbox(String text) {
        return new CheckboxB(text);
    }
}