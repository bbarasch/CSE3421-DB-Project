package group1.cse3241.databaseinterface;

import group1.cse3241.databaseinterface.schema.Movie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovieAdd extends JPanel implements ActionListener {
    private final JButton back;
    private final JTextField title, content_rating, length, year, movie_genre, movie_definition;
    private final JComboBox<String> creator_name;
    private final JList<String> starring;
    private final JLabel warningMessage;
    private final DatabaseDisplay display;

    public MovieAdd(DatabaseDisplay display) {
        setLayout(new GridLayout(11, 2));

        this.display = display;

        warningMessage = new JLabel("");
        warningMessage.setForeground(Color.RED);
        add(warningMessage);
        // Spacer
        add(new JLabel());

        add(new JLabel("Movie Title:"));
        title = new PlaceholderTextField("Movie Title");
        add(title);

        add(new JLabel("Content Rating:"));
        content_rating = new PlaceholderTextField("Content Rating");
        add(content_rating);

        add(new JLabel("Movie Length:"));
        length = new PlaceholderTextField("Movie Length");
        add(length);

        add(new JLabel("Year released:"));
        year = new PlaceholderTextField("Year released");
        add(year);

        add(new JLabel("Movie Genre:"));
        movie_genre = new PlaceholderTextField("Movie Genre");
        add(movie_genre);

        add(new JLabel("Movie Definition:"));
        movie_definition = new PlaceholderTextField("Definition");
        add(movie_definition);

        add(new JLabel("Director:"));
        String[] creators = display.getCreatorMap().keySet().toArray(new String[0]);
        creator_name = new JComboBox<>(creators);
        add(creator_name);

        add(new JLabel("Starring Actors:"));
        starring = new JList<>(creators);
        add(starring);

        JButton submit = new JButton("Add Movie");
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
        if (display.getMovieMap().containsKey(title)) {
            warningMessage.setText("A movie with that title already exists!");
            return;
        }

        String[] starring = this.starring.getSelectedValuesList().toArray(new String[0]);
        String movie_definition = this.movie_definition.getText();
        String content_rating = this.content_rating.getText();
        String length = this.length.getText();
        String movie_genre = this.movie_genre.getText();
        String creator_name = (String) this.creator_name.getSelectedItem();
        Movie movie = new Movie(title, content_rating, length, year, creator_name, movie_genre, movie_definition, starring);
        display.getMovieMap().put(title, movie);
        display.changeView(new MainMenuDisplay(display));
    }
}