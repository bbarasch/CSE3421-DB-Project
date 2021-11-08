package group1.cse3241.databaseinterface;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ArtistYearForm extends JPanel implements ActionListener {
	private final JButton submit, back;
	private final JTextField artist, year;
    private final DatabaseDisplay display;
    
	public ArtistYearForm(DatabaseDisplay display) {
        super(new GridLayout(6, 1));
        this.display = display;
        
        JLabel artistLabel = new JLabel("Enter name of artist:");
        artist = new JTextField(30);
        JLabel yearLabel = new JLabel("Enter year:");
        year = new JTextField(5);
        add(artistLabel);
        add(artist);
        add(yearLabel);
        add(year);
        
        submit = new JButton("Submit");
        submit.addActionListener(this);
        add(submit);
        
        back = new JButton("Back");
        back.addActionListener(this);
        add(back);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(submit)) {
			String artistName = artist.getText();
			int yearNum = Integer.parseInt(year.getText());
			System.out.println(artistName + " " + yearNum);
            display.changeView(new TrackBeforeYear(display, artistName, yearNum));
        } else if (e.getSource().equals(back)) {
            display.changeView(new ReportsDisplay(display));
        }
		
	}

}
