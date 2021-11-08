package group1.cse3241.databaseinterface;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TrackBeforeYear extends JPanel implements ActionListener {
	private final JButton back;
    private final DatabaseDisplay display;
    
	public TrackBeforeYear(DatabaseDisplay display, String artistName, int year) {
		super(new GridLayout(2, 1));
        this.display = display;
       
        String sqlStat = "SELECT TRACK.Title, TRACK.Album_title, MEDIA.Creator_name, MEDIA.Year\r\n"
        		+ "FROM TRACK, MEDIA\r\n"
        		+ "WHERE MEDIA.Media_type = \"Album\" AND TRACK.Album_title = MEDIA.Title AND MEDIA.Creator_name = \"" + artistName + "\" AND MEDIA.Year < " + year + ";";
        
        JTable results = Main.sqlQuery(Main.conn, sqlStat);
        add(new JScrollPane(results));
       
        back = new JButton("Back");
        back.addActionListener(this);
        add(back);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(back)) {
            display.changeView(new ReportsDisplay(display));
        }
	}

}
