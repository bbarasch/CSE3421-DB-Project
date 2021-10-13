package group1.cse3241.databaseinterface;

import group1.cse3241.databaseinterface.schema.ContentCreator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditDisplay extends JPanel implements ActionListener {
    private final JComboBox<String> artistEntry;
    private final JButton submit, back;
    private final DatabaseDisplay display;

    public EditDisplay(DatabaseDisplay display) {
        super(new GridLayout(4, 1));

        this.display = display;

        add(new JLabel("Artist to Edit:"));
        String[] artists = display.getCreatorMap().keySet().toArray(new String[0]);
        artistEntry = new JComboBox<>(artists);
        add(artistEntry);

        submit = new JButton("Edit");
        submit.addActionListener(this);
        add(submit);

        back = new JButton("Back");
        back.addActionListener(this);
        add(back);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(submit)) {
            String artistName = (String) artistEntry.getSelectedItem();
            ContentCreator artist = display.getCreatorMap().get(artistName);
            display.changeView(new ArtistAdd(display, artist));
        } else if (e.getSource().equals(back)) {
            display.changeView(new MainMenuDisplay(display));
        }
    }
}
