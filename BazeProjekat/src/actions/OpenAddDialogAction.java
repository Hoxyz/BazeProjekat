package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import gui.AddDialog;
import gui.MainFrame;
import resource.implementation.Entity;

public class OpenAddDialogAction extends AbstractAction {

	public OpenAddDialogAction() {
		this("Add");
	}
	
	public OpenAddDialogAction(String name) {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		putValue(MNEMONIC_KEY, KeyEvent.VK_F);
		putValue(NAME, name);
		putValue(SHORT_DESCRIPTION, "Dodaj novi red");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int index = MainFrame.getInstance().getTablePane().getSelectedIndex();
		Entity table = MainFrame.getInstance().getTablePane().getCurrentTable();
		System.out.println(table);
		AddDialog dialog = new AddDialog(table);
	}
}
