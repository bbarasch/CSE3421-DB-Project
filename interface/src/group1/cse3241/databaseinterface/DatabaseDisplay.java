package group1.cse3241.databaseinterface;

import javax.swing.*;

public class DatabaseDisplay extends JFrame {

    public DatabaseDisplay() {
        super("Database Interface");

        changeView(new MainMenuDisplay(this));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void changeView(JPanel view) {
        getContentPane().removeAll();
        view.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        add(view);

        pack();
        repaint();
    }
}
