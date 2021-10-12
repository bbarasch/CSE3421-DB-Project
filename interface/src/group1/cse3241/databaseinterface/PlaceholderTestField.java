package group1.cse3241.databaseinterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class PlaceholderTestField extends JTextField implements FocusListener {
    private final String placeholder;

    public PlaceholderTestField(String placeholder) {
        super(placeholder);
        this.placeholder = placeholder;
        setForeground(Color.GRAY);
        addFocusListener(this);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (getText().equals(placeholder)) {
            setText("");
            setForeground(Color.BLACK);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (getText().isEmpty()) {
            setForeground(Color.GRAY);
            setText(placeholder);
        }
    }
}
