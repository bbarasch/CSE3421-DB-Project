package group1.cse3241.databaseinterface;

import group1.cse3241.databaseinterface.schema.ContentCreator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class ArtistAdd extends JPanel implements ActionListener {
    private final JButton back;
    private final JTextField entry;
    private final DatabaseDisplay display;
    private String oldName = "";

    public ArtistAdd(DatabaseDisplay display, ContentCreator artist) {
        setLayout(new GridLayout(4, 1));

        this.display = display;

        add(new JLabel("Artist Name:"));
        entry = new PlaceholderTextField("Artist Name");
        if (artist != null) {
            entry.setText(artist.getName());
            oldName = artist.getName();
        }
        add(entry);

        JButton search = new JButton("Add artist");
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
        }
        String name = entry.getText();
        ContentCreator artist = new ContentCreator(name, new ContentCreator.ArtistType[]{ContentCreator.ArtistType.AUTHOR});
        Map<String, ContentCreator> map = display.getCreatorMap();
        if (oldName.length() != 0) {
            map.remove(oldName);
        }
        map.put(name, artist);
        display.changeView(new MainMenuDisplay(display));
    }
}