package yieldCubeMacro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class ComBoxListener implements ActionListener {

	List<JComboBox> cb;
	List<JTextField> txt;
	int sigmaItemCount;

	public ComBoxListener(List<JComboBox> cb, List<JTextField> txt, int sigmaItemCount) {
		super();
		this.cb = cb;
		this.txt = txt;
		this.sigmaItemCount = sigmaItemCount;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		txt.get(sigmaItemCount - 1).setVisible(
				(cb.get(sigmaItemCount - 1).getSelectedItem().toString().equalsIgnoreCase("Other")) ? true : false);
	}

}
