package group1.cse3241.databaseinterface;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AlbumsCheckedForm extends JPanel implements ActionListener {
	private final JButton submit, back;
	private final PlaceholderTextField email;
    private final DatabaseDisplay display;
    
    public AlbumsCheckedForm(DatabaseDisplay display) {
    	super(new GridLayout(4, 1));
        this.display = display;
        
        JLabel PatronLabel = new JLabel("Enter Email of patron:");
        email = new PlaceholderTextField("Email");
        add(PatronLabel);
        add(email);
        
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
			String emailAddress = email.getText();
			System.out.println(emailAddress);
            display.changeView(new AlbumsCheckedDisplay(display, emailAddress));
        } else if (e.getSource().equals(back)) {
            display.changeView(new ReportsDisplay(display));
        }
	}

}
