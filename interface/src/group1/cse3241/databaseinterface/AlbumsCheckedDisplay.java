package group1.cse3241.databaseinterface;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

public class AlbumsCheckedDisplay extends JPanel implements ActionListener {
	private final DatabaseDisplay display;
	private final JButton back;
	
	public AlbumsCheckedDisplay(DatabaseDisplay display, String email) {
		super(new GridLayout(2, 1));
        this.display = display;
        
        String sqlStat = "SELECT PATRON.Fname, PATRON.Lname, PATRON.Email, COUNT(CHECKED_OUT.Card_Id)\r\n"
        		+ "FROM PATRON, CHECKED_OUT, LIBRARY_CARD, COPY\r\n"
        		+ "WHERE PATRON.Email = \"" + email + "\" AND PATRON.Email = LIBRARY_CARD.Email AND LIBRARY_CARD.Id_card = CHECKED_OUT.Card_Id AND CHECKED_OUT.Copy_Id = COPY.Id_copy AND COPY.Media_type = \"Album\" AND CHECKED_OUT.Returned_Date IS NULL\r\n"
        		+ "GROUP BY PATRON.Email;";
        JTable result = Main.sqlQuery(Main.conn, sqlStat);
        String name = result.getValueAt(0, 0).toString() + " " + result.getValueAt(0, 1).toString();
        String count = result.getValueAt(0, 3).toString();
        JLabel resultsLabel = new JLabel("Patron " + name + " has " + count + " albums checked out currently.");
        add(resultsLabel);
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
