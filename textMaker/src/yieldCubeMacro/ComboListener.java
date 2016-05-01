package yieldCubeMacro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class ComboListener implements ActionListener{

	private JTextField textField;

	public ComboListener(JTextField textField) {
		this.textField = textField;
	}

	public void actionPerformed(ActionEvent e) {
		JComboBox comboBox = (JComboBox) e.getSource();

		// Set enabled based on button text (you can use whatever text you
		// prefer)
		if (comboBox.getText().equals("Enable")) {
			textField.setVisible(true);
		} else {
			textField.setVisible(false);
		}
	}
}