package mainpackage;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;


public class Driver {

    public static void main(String[] args) {
    	MessageManager manager = new MessageManager();
    	
        // Create the AdminPanel frame
        JAdminView adminView = new JAdminView(manager);

        // Make the AdminPanel frame visible
        adminView.setVisible(true);
    }
}

