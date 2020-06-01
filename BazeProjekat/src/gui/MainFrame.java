package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import actions.ActionManager;
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
	
	private ActionManager actionManager;
	
	private JTable table;
	private TableTabbedPane tablePane;
	private RelationsTabbedPane relationsPane;
	private JScrollPane treeScrollPane;
	
	private JButton buttonAddRow;
	private JButton buttonRemoveRows;
	private JButton buttonUpdateRow;
	
	private JPanel topTablePanel;
	private JPanel bottomTablePanel;
	private JSplitPane splitPane;
	
	private InformationResourceModel treeModel;
	private InformationResourceTree irTree;
	
	public AppCore getAppCore() {
		return appCore;
	}
	
	public ActionManager getActionManager() {
		return actionManager;
	}
	
	public void setAppCore(AppCore appCore) {
		this.appCore = appCore;
		this.appCore.AddSubscriber(this);
	}

	private MainFrame() {
		super();

		actionManager = new ActionManager();
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setSize((int)(screenWidth / 1.5), (int)(screenHeight / 1.5));
		setTitle("DocuMint");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
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
		treeScrollPane.setMinimumSize(new Dimension(200, 150));
		
		buttonAddRow = new JButton(actionManager.getOpenAddDialogAction());
		buttonAddRow.setAlignmentX(CENTER_ALIGNMENT);
		buttonAddRow.setAlignmentY(CENTER_ALIGNMENT);
		
		buttonUpdateRow = new JButton(actionManager.getOpenUpdateDialogAction());
		buttonUpdateRow.setAlignmentX(CENTER_ALIGNMENT);
		buttonUpdateRow.setAlignmentY(CENTER_ALIGNMENT);
		
		buttonRemoveRows = new JButton(actionManager.getRemoveSelectedRowsAction());
		buttonRemoveRows.setAlignmentX(CENTER_ALIGNMENT);
		buttonRemoveRows.setAlignmentY(CENTER_ALIGNMENT);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		buttonPanel.add(buttonAddRow);
		buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		buttonPanel.add(buttonUpdateRow);
		buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		buttonPanel.add(buttonRemoveRows);
		
		topTablePanel = new JPanel();
		topTablePanel.setLayout(new BoxLayout(topTablePanel, BoxLayout.PAGE_AXIS));
		topTablePanel.add(tablePane);
		topTablePanel.add(Box.createRigidArea(new Dimension(0, 5)));
		topTablePanel.add(buttonPanel);
		
		bottomTablePanel = new JPanel();
		bottomTablePanel.setLayout(new BoxLayout(bottomTablePanel, BoxLayout.PAGE_AXIS));
		bottomTablePanel.setPreferredSize(new Dimension(500, 400));
		bottomTablePanel.add(relationsPane);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
		rightPanel.add(topTablePanel);
		rightPanel.add(bottomTablePanel);
		
		
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeScrollPane, rightPanel);
		splitPane.setPreferredSize(new Dimension(800, 600));
		
		
		this.add(splitPane, BorderLayout.CENTER);
		pack();
		
		revalidate();
		repaint();
		this.setLocationRelativeTo(null);
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
	
	public RelationsTabbedPane getRelationsPane() {
		return relationsPane;
	}
	
	private void init() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		table = new JTable();
        table.setPreferredScrollableViewportSize(new Dimension(500, 400));
        table.setFillsViewportHeight(true);
        
        tablePane = new TableTabbedPane();
        relationsPane = new RelationsTabbedPane();
	}
	
	@Override
	public void Update(Notification notification) {
		if (notification.getCode() == NotificationCode.RESOURCE_LOADED){
            System.out.println((InformationResource)notification.getData());
            initializeTree((InformationResource)notification.getData());
        }
        else {
            table.setModel((TableModel) notification.getData());
        }
	}
}
