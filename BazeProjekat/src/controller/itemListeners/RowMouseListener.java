package controller.itemListeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

import gui.EntityPanel;
import gui.MainFrame;
import gui.TableTabbedPane;
import resource.data.Row;
import resource.implementation.Entity;

public class RowMouseListener implements MouseListener {

	private JTable table;
	
	public RowMouseListener (JTable table) {
		// TODO Auto-generated constructor stub
		this.table = table;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed (MouseEvent e) {
		int rowAtPoint = table.rowAtPoint(e.getPoint());
		if (rowAtPoint != -1) {
			//table.changeSelection(rowAtPoint, colAtPoint, false, false);
			// refresh relations pane in mainFrame
			MainFrame mf = MainFrame.getInstance();
			
			TableTabbedPane tablePane = mf.getTablePane();
			Entity table = tablePane.getCurrentTable();
			EntityPanel tableWindow = tablePane.getTableWindow(table);
			
			if (tableWindow.getSelectedRows().length > 0) {
				Row row = tableWindow.getRow(tableWindow.getSelectedRows()[0]);
				mf.getRelationsPane().ShowRelatedRowsWith(row);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
