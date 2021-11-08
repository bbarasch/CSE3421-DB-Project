package group1.cse3241.databaseinterface;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

public class PopArtistDisplay extends JPanel implements ActionListener {
	private final DatabaseDisplay display;
	private final JButton back;
	
	public PopArtistDisplay(DatabaseDisplay display) {
		super(new GridLayout(2, 1));
        this.display = display;
        
        String sqlStat = "SELECT creators.Creator_name, MAX(max_listening) as \"Time (Minutes)\"\n"
        		+ "FROM (SELECT MEDIA.Creator_name, (COUNT(CHECKED_OUT.Copy_Id) * MEDIA.Length) as max_listening\n"
        		+ "     FROM MEDIA, COPY, CHECKED_OUT\n"
        		+ "     WHERE MEDIA.Title = COPY.Media_title AND COPY.Id_copy = CHECKED_OUT.Copy_Id AND MEDIA.Media_type = \"Album\"\n"
        		+ "     GROUP BY MEDIA.Creator_name) as creators;";
        JTable results = Main.sqlQuery(Main.conn, sqlStat);
        String artist = results.getValueAt(0, 0).toString();
        
        JLabel artistLabel = new JLabel("The most listened to artist in the database is: " + artist + ".");
        add(artistLabel);
        
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
