package vendingmachine.utils;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.TextFieldSkin;

public class PasswordFieldSkin extends TextFieldSkin {
    /**
     * Creates a new TextFieldSkin instance for *
     */
    private final char ASTERISK = '\u002A';

    public PasswordFieldSkin(TextField control) {
        super(control);
    }

    @Override
    protected String maskText(String txt) {
        if (getSkinnable() instanceof PasswordField) {
            int n = txt.length();
            StringBuilder passwordBuilder = new StringBuilder(n);
            for (int i = 0; i < n; i++) {
                passwordBuilder.append(ASTERISK);
            }

            return passwordBuilder.toString();
        } else {
            return txt;
        }
    }


}