package group1.cse3241.databaseinterface;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

public class PopActorDisplay extends JPanel implements ActionListener {
	private final DatabaseDisplay display;
	private final JButton back;
	
	public PopActorDisplay(DatabaseDisplay display) {
		super(new GridLayout(2, 1));
        this.display = display;
        
        String sqlStat = "SELECT actors.Actor_name, MAX(max_movie_count)\r\n"
        		+ "FROM (SELECT STARRING.Actor_name, COUNT(CHECKED_OUT.COPY_Id) as max_movie_count\r\n"
        		+ "FROM STARRING, COPY, CHECKED_OUT\r\n"
        		+ "WHERE STARRING.Movie_title = COPY.Media_title AND COPY.Id_copy = CHECKED_OUT.Copy_Id\r\n"
        		+ "GROUP BY STARRING.Actor_name) as actors;";
        JTable results = Main.sqlQuery(Main.conn, sqlStat);
        String actor = results.getValueAt(0, 0).toString();
        
        JLabel actorLabel = new JLabel("The most popular actor in the database is: " + actor + ".");
        add(actorLabel);
        
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
