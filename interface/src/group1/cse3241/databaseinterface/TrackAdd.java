package group1.cse3241.databaseinterface;

import group1.cse3241.databaseinterface.schema.Track;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrackAdd extends JPanel implements ActionListener {
    private final JButton back;
    private final JTextField trackEntry, lengthEntry;
    private final JComboBox<String> albumEntry;
    private final DatabaseDisplay display;

    public TrackAdd(DatabaseDisplay display) {
        setLayout(new GridLayout(8, 1));

        this.display = display;

        add(new JLabel("Track Name:"));
        trackEntry = new PlaceholderTextField("Track Name");
        add(trackEntry);

        add(new JLabel("Track length:"));
        lengthEntry = new PlaceholderTextField("Track Length");
        add(lengthEntry);

        add(new JLabel("Album:"));
        String[] albums = display.getAlbumMap().keySet().toArray(new String[0]);
        albumEntry = new JComboBox<>(albums);
        add(albumEntry);

        JButton addButton = new JButton("Add Track");
        addButton.addActionListener(this);
        add(addButton);

        back = new JButton("Back");
        back.addActionListener(this);
        add(back);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(back)) {
            display.changeView(new MainMenuDisplay(display));
            return;
        }
        String length = lengthEntry.getText();
        String title = trackEntry.getText();
        String album = (String) albumEntry.getSelectedItem();
        Track track = new Track(title, length, album);
        display.getTrackMap().put(title, track);
        display.changeView(new MainMenuDisplay(display));
    }
}