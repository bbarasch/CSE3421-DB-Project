package group1.cse3241.databaseinterface;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.chrono.MinguoChronology;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EditArtistDisplay extends JPanel implements ActionListener {
	private final JButton submit, back;
	private final JTextField input;
    private final DatabaseDisplay display;
    private final String oldName;
    
	public EditArtistDisplay(DatabaseDisplay display, String artistName) {
		super(new GridLayout(4, 1));
		this.display = display;
		oldName = artistName;
		
		add(new JLabel("Edit name of " + artistName + ":"));
	    input = new JTextField(artistName);
	    add(input);
	    
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
			String newName = input.getText();
			System.out.println(newName + " " + oldName);
			String statSeq = "UPDATE CONTENT_CREATOR\r\n"
					+ "SET Name = \"" + newName +"\"\r\n"
					+ "WHERE person_id = (SELECT CONTENT_CREATOR.person_id FROM CONTENT_CREATOR WHERE CONTENT_CREATOR.Name = \""+ oldName +"\");";
			try {
				Main.conn.createStatement().executeUpdate(statSeq);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			display.changeView(new MainMenuDisplay(display));
		} else if (e.getSource().equals(back)) {
			display.changeView(new EditDisplay(display));
		} 
	}

}
