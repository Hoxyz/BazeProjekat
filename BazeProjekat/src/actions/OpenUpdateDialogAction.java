package actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JLabel;

import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import gui.EntityPanel;
import gui.MainFrame;
import gui.TableTabbedPane;
import gui.UpdateDialog;
import resource.data.Row;
import resource.implementation.Entity;

public class OpenUpdateDialogAction extends AbstractAction {

	public OpenUpdateDialogAction() {
		this("Update");
	}
	
	public OpenUpdateDialogAction(String name) {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));
		putValue(MNEMONIC_KEY, KeyEvent.VK_U);
		putValue(NAME, name);
		putValue(SHORT_DESCRIPTION, "Promeni selektovani red");
	}
	
	// Update Dialog will allow only a single row to be modified at a time
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			TableTabbedPane tabbedPane = MainFrame.getInstance().getTablePane();
			
			Entity entity = tabbedPane.getCurrentTable();
			EntityPanel tablePanel = tabbedPane.getTableWindow(entity);
			
			Row row = tablePanel.getRow(tablePanel.getSelectedRows()[0]);			
			@SuppressWarnings("unused")
			UpdateDialog dialog = new UpdateDialog(entity, row);
		}
		catch (Exception ex){
			JDialog errorDialog = new JDialog(MainFrame.getInstance(), "Greska", true);
        	errorDialog.add(new JLabel("Nemoguce azurirati selektovani red / red nije selektovan.", SwingConstants.CENTER));
        	errorDialog.setSize(480, 180);
        	errorDialog.setLocationRelativeTo(null);
        	errorDialog.setVisible(true);
			ex.printStackTrace();
		}
	}
}
