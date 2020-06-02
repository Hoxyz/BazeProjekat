package gui;

import gui.MainFrame;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import actions.ActionManager;

public class MainMenuBar extends JMenuBar {

	// MainFrame is an argument here because the singleton is not constructed 
	// at the moment we need the constructor for MainMenuBar
	public MainMenuBar(MainFrame mainFrame) {
		ActionManager actionManager = mainFrame.getActionManager();
		
		// Analyze Menu
		JMenu analyzeMenu = new JMenu("Analyze");
		analyzeMenu.setMnemonic(KeyEvent.VK_A);
		
		// Analyze -> Search
		analyzeMenu.add(actionManager.getOpenSearchDialogAction());
		
		// Analyze -> Reports
		analyzeMenu.add(actionManager.getOpenReportDialogAction());
		
		add(analyzeMenu);
		
		// editMenu.addSeparator();
	}

}
