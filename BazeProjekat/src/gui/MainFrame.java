package gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import app.AppCore;
import observer.Notification;
import observer.Subscriber;
import observer.enums.NotificationCode;
import resource.implementation.InformationResource;

public class MainFrame extends JFrame implements Subscriber {
	
	private static MainFrame instance = null;
	
	private AppCore appCore;
	private JTable table;
	private JScrollPane scrollPane;
	private JPanel panel;
	
	public AppCore getAppCore() {
		return appCore;
	}
	
	public void setAppCore(AppCore appCore) {
		this.appCore = appCore;
		this.appCore.AddSubscriber(this);
		this.table.setModel(appCore.getTableModel());
	}
	
	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}
	
	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	private MainFrame() {
	}
	
	public static MainFrame getInstance() {
		if(instance == null) {
			instance = new MainFrame();
			instance.init();
		}
		return instance;
	}
	
	private void init() {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		table = new JTable();
        table.setPreferredScrollableViewportSize(new Dimension(500, 400));
        table.setFillsViewportHeight(true);
        
        this.add(new JScrollPane(table));
        
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
	}
	

	@Override
	public void Update(Notification notification) {
		if (notification.getCode() == NotificationCode.RESOURCE_LOADED){
            System.out.println((InformationResource)notification.getData());
        }
        else{
            table.setModel((TableModel) notification.getData());
        }
	}
}
