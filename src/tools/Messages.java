package tools;

import java.util.ResourceBundle;

public class Messages {

    static ResourceBundle bundle = ResourceBundle.getBundle("tools.messages");

    public static String getString(String key) {
        return bundle.getString(key);
    }
}