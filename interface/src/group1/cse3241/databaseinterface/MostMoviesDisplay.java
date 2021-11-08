package group1.cse3241.databaseinterface;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

public class MostMoviesDisplay extends JPanel implements ActionListener {
	private final DatabaseDisplay display;
	private final JButton back;
	
	public MostMoviesDisplay (DatabaseDisplay display) {
		super(new GridLayout(3, 1));
        this.display = display;
        
        String sqlStat = "SELECT PATRON.Fname, PATRON.Lname, PATRON.Email, COUNT(CHECKED_OUT.Card_Id) as \"count\"\r\n"
        		+ "FROM PATRON, CHECKED_OUT, LIBRARY_CARD, COPY\r\n"
        		+ "WHERE PATRON.Email = LIBRARY_CARD.Email AND LIBRARY_CARD.Id_card = CHECKED_OUT.Card_Id AND CHECKED_OUT.Copy_Id = COPY.Id_copy AND COPY.Media_type = \"Movie\"\r\n"
        		+ "GROUP BY PATRON.Email\r\n"
        		+ "ORDER BY COUNT(CHECKED_OUT.Card_Id) DESC\r\n"
        		+ "LIMIT 1;";
        JTable result = Main.sqlQuery(Main.conn, sqlStat);
        String name = result.getValueAt(0, 0).toString() + " " + result.getValueAt(0, 1).toString();
        String email = result.getValueAt(0, 2).toString();
        String count = result.getValueAt(0, 3).toString();
        
        JLabel patronLabel = new JLabel("The patron with most checked out movies is: " + name + " (" + email + ").");
        JLabel countLabel = new JLabel("They have " + count + " movies checked out ever.");
        add(patronLabel);
        add(countLabel);
        
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
