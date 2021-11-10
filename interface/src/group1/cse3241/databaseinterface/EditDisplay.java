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
        String getArtists = "SELECT CREATOR_TYPE.Creator_name FROM CREATOR_TYPE WHERE CREATOR_TYPE.Type = \"Musician\";";
        JTable artistTable = Main.sqlQuery(Main.conn, getArtists);
        int row = artistTable.getRowCount();
        String[] artistArr = new String[row];
        for(int i = 0; i < row; i++) {
        	artistArr[i] = artistTable.getValueAt(i, 0).toString();
        }
        System.out.println(artistArr.toString());
        add(new JLabel("Artist to Edit:"));
        artistEntry = new JComboBox<>(artistArr);
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
            display.changeView(new EditArtistDisplay(display, artistName));
        } else if (e.getSource().equals(back)) {
            display.changeView(new MainMenuDisplay(display));
        }
    }
}
