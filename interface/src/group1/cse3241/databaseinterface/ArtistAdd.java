package group1.cse3241.databaseinterface;

import group1.cse3241.databaseinterface.schema.ContentCreator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class ArtistAdd extends JPanel implements ActionListener {
    private final JButton back, search;
    private final JTextField entry;
    private final DatabaseDisplay display;

    public ArtistAdd(DatabaseDisplay display, ContentCreator artist) {
        setLayout(new GridLayout(4, 1));

        this.display = display;

        add(new JLabel("Artist Name:"));
        entry = new PlaceholderTextField("Artist Name");
        add(entry);

        search = new JButton("Add artist");
        search.addActionListener(this);
        add(search);

        back = new JButton("Back");
        back.addActionListener(this);
        add(back);
    }

    public ArtistAdd(DatabaseDisplay display) {
        this(display, null);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(back)) {
            display.changeView(new MainMenuDisplay(display));
            return;
        } else if (e.getSource().equals(search)) {
        	String name = entry.getText();
        	String checkDatabase = "SELECT CONTENT_CREATOR.Name FROM CONTENT_CREATOR WHERE CONTENT_CREATOR.Name = \"" + name + "\";";
        	JTable result = Main.sqlQuery(Main.conn, checkDatabase);
        	System.out.println(result.getRowCount());
        	if (result.getRowCount() == 0) {
        		String addArtist = "INSERT INTO CONTENT_CREATOR (Name) VALUES(\"" + name + "\")";
        		String addType = "INSERT INTO CREATOR_TYPE (Type, Creator_name) VALUES(\"Musician\", \""+ name + "\")";
        		Main.sqlQuery(Main.conn, addArtist);
        		Main.sqlQuery(Main.conn, addType);
        	}
        	display.changeView(new MainMenuDisplay(display));
        }
    }
}