package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import gui.MainFrame;
import gui.ReportDialog;
import resource.implementation.Entity;

public class OpenReportDialogAction extends AbstractAction {

	public OpenReportDialogAction() {
		this("Reports");
	}
	
	public OpenReportDialogAction(String name) {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
		putValue(MNEMONIC_KEY, KeyEvent.VK_R);
		putValue(NAME, name);
		putValue(SHORT_DESCRIPTION, "Izvestaji za trenutnu tabelu");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Entity entity = MainFrame.getInstance().getTablePane().getCurrentTable();
		if (entity != null) {
			ReportDialog dialog = new ReportDialog(entity);
		}
	}

}
