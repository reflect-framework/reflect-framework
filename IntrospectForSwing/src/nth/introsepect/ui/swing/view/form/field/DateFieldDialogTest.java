package nth.introsepect.ui.swing.view.form.field;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
//FIXME remove this class when no longer needed!
public class DateFieldDialogTest {
		public static void main(String[] args) {
			JLabel label = new JLabel("Selected Date:");
			final JTextField text = new JTextField(20);
			JButton b = new JButton("popup");
			JPanel p = new JPanel();
			p.add(label);
			p.add(text);
			p.add(b);
			final JFrame f = new JFrame();
			f.getContentPane().add(p);
			f.pack();
			f.setVisible(true);
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					text.setText(new DatePickDialog(f).setPickedDate());
				}
			});
		}

	
}
