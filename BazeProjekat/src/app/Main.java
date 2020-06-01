package app;

import gui.MainFrame;

public class Main {

	public static void main(String[] args) {
		AppCore appCore = new AppCore();
        MainFrame mainFrame = MainFrame.getInstance();
        mainFrame.setAppCore(appCore);

        mainFrame.getAppCore().loadResource();
        //((MSSQLRepository)((DatabaseImplementation)mainFrame.getAppCore().getDatabase()).getRepository()).addRowTest();
	}
	
}
