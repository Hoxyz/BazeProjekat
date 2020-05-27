package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import app.AppCore;
import gui.tree.InformationResourceNode;
import gui.tree.InformationResourceTree;
import model.tree.InformationResourceModel;
import observer.Notification;
import observer.Subscriber;
import observer.enums.NotificationCode;
import resource.implementation.InformationResource;

public class MainFrame extends JFrame implements Subscriber {
	
	private static MainFrame instance = null;
	
	private AppCore appCore;
	private JTable table;
	private TableTabbedPane tablePane;
	private JPanel panel;
	
	private JScrollPane treeScrollPane;
	private JSplitPane splitPane;
	
	private InformationResourceModel treeModel;
	private InformationResourceTree irTree;
	
	public AppCore getAppCore() {
		return appCore;
	}
	
	public void setAppCore(AppCore appCore) {
		this.appCore = appCore;
		this.appCore.AddSubscriber(this);
		this.table.setModel(appCore.getTableModel());
	}
	
	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	private MainFrame() {
		super();
	}
	
	public void initializeTree() {
		initializeTree(new InformationResource("Test"));
	}
	
	public void initializeTree(InformationResource ir) {
		InformationResourceNode informationResourceNode = new InformationResourceNode(ir);

		irTree = new InformationResourceTree();

		treeModel = new InformationResourceModel(informationResourceNode);
		irTree.setModel(treeModel);
		
		// Create a scroll pane for the JTree
		treeScrollPane = new JScrollPane(irTree);
		treeScrollPane.setPreferredSize(new Dimension(260, 150));
		
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeScrollPane, tablePane);
		splitPane.setPreferredSize(new Dimension(800, 600));
		this.add(splitPane, BorderLayout.CENTER);
		
		pack();
		
		revalidate();
		repaint();
		
		this.setVisible(true);
		// NOTE: these GUI manipulations may not belong in this method
	}
	
	public static MainFrame getInstance() {
		if(instance == null) {
			instance = new MainFrame();
			instance.init();
			//instance.initializeTree();
		}
		return instance;
	}
	
	public TableTabbedPane getTablePane () {
		return tablePane;
	}
	
	private void init() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		table = new JTable();
        table.setPreferredScrollableViewportSize(new Dimension(500, 400));
        table.setFillsViewportHeight(true);
        
        tablePane = new TableTabbedPane();
	}
	
	@Override
	public void Update(Notification notification) {
		if (notification.getCode() == NotificationCode.RESOURCE_LOADED){
            System.out.println((InformationResource)notification.getData());
            initializeTree((InformationResource)notification.getData());
        }
        else{
            table.setModel((TableModel) notification.getData());
        }
	}
}
