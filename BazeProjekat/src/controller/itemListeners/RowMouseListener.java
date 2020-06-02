package controller.itemListeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

import gui.MainFrame;

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
		int colAtPoint = table.columnAtPoint(e.getPoint());
		if(rowAtPoint != -1) {
			table.changeSelection(rowAtPoint, colAtPoint, false, false);
			// refresh relations pane in mainFrame
			MainFrame mf = MainFrame.getInstance();
			mf.getRelationsPane().ShowRelatedRowsWith(mf.getTablePane().getTableWindow(mf.getTablePane().getCurrentTable()).getRow(rowAtPoint));
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
