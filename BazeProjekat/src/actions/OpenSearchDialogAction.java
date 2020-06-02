package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import gui.MainFrame;
import gui.SearchDialog;
import resource.implementation.Entity;

public class OpenSearchDialogAction extends AbstractAction {
	
	public OpenSearchDialogAction() {
		this("Search");
	}
	
	public OpenSearchDialogAction(String name) {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
		putValue(MNEMONIC_KEY, KeyEvent.VK_S);
		putValue(NAME, name);
		putValue(SHORT_DESCRIPTION, "Pretrazi trenutnu tabelu");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Entity entity = MainFrame.getInstance().getTablePane().getCurrentTable();
		if (entity != null) {
			SearchDialog dialog = new SearchDialog(entity);
		}
	}

}
