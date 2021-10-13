package group1.cse3241.databaseinterface;

import group1.cse3241.databaseinterface.schema.Album;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlbumAdd extends JPanel implements ActionListener {
    private final JButton back;
    private final JTextField title, content_rating, length, year, record_label, music_genre;
    private final JComboBox<String> creator_name;
    private final JLabel warningMessage;
    private final DatabaseDisplay display;

    public AlbumAdd(DatabaseDisplay display) {
        setLayout(new GridLayout(9, 2));

        this.display = display;

        warningMessage = new JLabel("");
        warningMessage.setForeground(Color.RED);
        add(warningMessage);

        // Padding
        add(new JLabel());

        add(new JLabel("Album Title:"));
        title = new PlaceholderTextField("Album Title");
        add(title);

        add(new JLabel("Content Rating:"));
        content_rating = new PlaceholderTextField("Content Rating");
        add(content_rating);

        add(new JLabel("Album Length:"));
        length = new PlaceholderTextField("Album Length");
        add(length);

        add(new JLabel("Year released:"));
        year = new PlaceholderTextField("Year released");
        add(year);

        add(new JLabel("Record Label:"));
        record_label = new PlaceholderTextField("Record Label");
        add(record_label);

        add(new JLabel("Music Genre:"));
        music_genre = new PlaceholderTextField("Music Genre");
        add(music_genre);

        add(new JLabel("Artist:"));
        String[] creators = display.getCreatorMap().keySet().toArray(new String[0]);
        creator_name = new JComboBox<>(creators);
        add(creator_name);

        JButton submit = new JButton("Add Album");
        submit.addActionListener(this);
        add(submit);

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
        int year; // = this.year.getText();
        try {
            year = Integer.parseInt(this.year.getText());
        } catch (NumberFormatException err) {
            warningMessage.setText("Year must be an integer");
            display.pack();
            return;
        }

        String title = this.title.getText();
        if (display.getAlbumMap().containsKey(title)) {
            warningMessage.setText("An album with that title already exists!");
            return;
        }

        String content_rating = this.content_rating.getText();
        String length = this.length.getText();
        String record_label = this.record_label.getText();
        String music_genre = this.music_genre.getText();
        String creator_name = (String) this.creator_name.getSelectedItem();
        Album album = new Album(title, content_rating, length, year, creator_name, record_label, music_genre);
        display.getAlbumMap().put(title, album);
        display.changeView(new MainMenuDisplay(display));
    }
}
