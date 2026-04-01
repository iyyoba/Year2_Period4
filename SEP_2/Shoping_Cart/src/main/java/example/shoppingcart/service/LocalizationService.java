package example.shoppingcart;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocalizationService {

    private static final String BUNDLE_PATH = "example.shoppingcart.i18n.messages";

    private ResourceBundle bundle;

    public LocalizationService() {
        // Default locale
        this.bundle = ResourceBundle.getBundle(BUNDLE_PATH, Locale.getDefault());
    }

    public LocalizationService(Locale locale) {
        this.bundle = ResourceBundle.getBundle(BUNDLE_PATH, locale);
    }

    public String get(String key) {
        return bundle.getString(key);
    }

    public void setLocale(Locale locale) {
        this.bundle = ResourceBundle.getBundle(BUNDLE_PATH, locale);
    }

    public Locale getLocale() {
        return bundle.getLocale();
    }
}
