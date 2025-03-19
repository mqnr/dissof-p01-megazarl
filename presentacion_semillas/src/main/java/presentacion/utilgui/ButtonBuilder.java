/*
 * Este es un paquete de prueba. Puede ser ignorado sin problemas.
 */
package presentacion.utilgui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JButton;

public class ButtonBuilder implements Builder<JButton> {
    private String text;
    private Color background;
    private Font font;
    private Insets margin;
    private Dimension preferredSize;

    public ButtonBuilder() {
    }

    public ButtonBuilder withText(String text) {
        this.text = text;
        return this;
    }

    public ButtonBuilder withBackground(Color color) {
        this.background = color;
        return this;
    }

    public ButtonBuilder withFont(Font font) {
        this.font = font;
        return this;
    }

    public ButtonBuilder withEmptyMargin() {
        this.margin = new Insets(0, 0, 0, 0);
        return this;
    }

    public ButtonBuilder withPreferredSize(int width, int height) {
        this.preferredSize = new Dimension(width, height);
        return this;
    }

    public JButton build() {
        JButton button = (text != null) ? new JButton(text) : new JButton();

        if (background != null) {
            button.setBackground(background);
        }

        if (font != null) {
            button.setFont(font);
        }

        if (margin != null) {
            button.setMargin(margin);
        }

        if (preferredSize != null) {
            button.setPreferredSize(preferredSize);
        }

        return button;
    }
}
