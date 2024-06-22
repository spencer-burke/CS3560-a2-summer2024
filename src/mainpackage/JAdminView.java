package mainpackage;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class JAdminView extends JFrame {
	static JFrame dialogFrame;
	// manager.getGroupList().get( (manager.getGroupList().size())-1 ).getID()
    public JAdminView(MessageManager manager) {
    	dialogFrame = new JFrame("Dialog Frame");
    	
    	// Make sure the manager has the root group already
    	manager.addGroup(new UserGroup("Root"));
    	
        // Set the title of the AdminPanel frame
        setTitle("Admin Panel");

        // Set the size of the AdminPanel frame
        setSize(950, 400);

        // Set the default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the layout manager for the content pane
        setLayout(new BorderLayout());
        
        // Create the tree view
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        DefaultTreeModel model = new DefaultTreeModel(root);
        JTree tree = new JTree(model);
        ArrayList<DefaultMutableTreeNode> treeList = new ArrayList<>();
        treeList.add(root);
        
        // Create two text areas
        JTextArea userID = new JTextArea("User Id");
        JTextArea groupID = new JTextArea("Group Id");

        // Create a panel for the buttons and set its layout
        JPanel buttonPanel = new JPanel(new GridLayout(1, 7));
        
        // Add 7 buttons to the button panel
        for (int i = 1; i <= 7; i++) {
            JButton button = new JButton("Button " + i);
            if (i == 1) {
                // Add action listener to open UserView on button press
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Open the UserView grid component view
                    	// get the user to open
                    	String txtUserID = userID.getText();
                    	// make sure it's valid
                    	if (!manager.getUserList().contains(new User(txtUserID))) {
                    		JDialog dialog = new JDialog(dialogFrame, "Error Dialog");
                    		dialog.setSize(250, 250);
                    		dialog.add(new Label("Error User Does Not Exist"));
                    		dialog.setVisible(true);
                    	}
                    	else {
                    		// open the view for that user
                    		JUserView newView = new JUserView(manager.getUser(txtUserID), manager);
                    		newView.setVisible(true);
                    	}
                    }
                });
                button.setText("Open User View");
            }
            if (i == 2) {
                // Add action listener to open UserView on button press
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // get the user id from the text field
                    	String txtUserID = userID.getText();
                    	// get group id if there is one
                    	String txtGroupID = groupID.getText();
                    	// check if userID is valid and groupID is valid
                    	if (manager.getUserList().contains(new User(txtUserID)) || !manager.getGroupList().contains(new UserGroup(txtGroupID))) {
                    		if (!manager.getGroupList().contains(new UserGroup(txtGroupID))) {
                        		JDialog dialog = new JDialog(dialogFrame, "Error Dialog");
                        		dialog.setSize(250, 250);
                        		dialog.add(new Label("Error Group Does Not Exist"));
                        		dialog.setVisible(true);
                    		}
                    		else {
                    			JDialog dialog = new JDialog(dialogFrame, "Error Dialog");
                    			dialog.setSize(250, 250);
                    			dialog.add(new Label("Error User Already Exists"));
                    			dialog.setVisible(true);
                    		}
                    	}
                    	else {
                    		User newUser = new User(txtUserID, txtGroupID);
                    		// add it to the manager
                    		manager.addUser(newUser);
                    		// add it to the tree
                    		// get index for group
                    		int groupIndex = 0;
                    		ArrayList<UserGroup> groups = manager.getGroupList();
                    		for (int i = 0; i < groups.size(); i++) {
                    			if (groups.get(i).getID().equals(txtGroupID))
                    				groupIndex = i;
                    		}
                    		System.out.printf("Group adding to %d\n", groupIndex);
                    		// create the node
                    		DefaultMutableTreeNode node = new DefaultMutableTreeNode(newUser.getID());
                    		// add to the tree and make it visible
                    		model.insertNodeInto(node, treeList.get(groupIndex), treeList.get(groupIndex).getChildCount());
                    		tree.scrollPathToVisible(new TreePath(node.getPath()));
                    	}
                    }
                });
                button.setText("Add User");
            }
            if (i == 3) {
                // Add action listener to open UserView on button press
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	// get the text from group id field
                    	String txtGroupID = groupID.getText();
                    	// make new object
                    	UserGroup newGroup = new UserGroup(txtGroupID);
                    	// make sure it's unique
                    	if (manager.getGroupList().contains(newGroup)) {
                    		JDialog dialog = new JDialog(dialogFrame, "Error Dialog");
                    		dialog.setSize(250, 250);
                    		dialog.add(new Label("Error Group Already Exists"));
                    		dialog.setVisible(true);
                    	}
                    	else {
                    		System.out.printf("adding %s\n", newGroup.getID());
                    		// add the group to the manager
                    		manager.addGroup(newGroup);
                    		// make new tree node
                    		DefaultMutableTreeNode node = new DefaultMutableTreeNode(newGroup.getID());
                    		// update the tree
                    		model.insertNodeInto(node, treeList.get(treeList.size()-1), treeList.get(treeList.size()-1).getChildCount());
                    		// update tree list
                    		treeList.add(node);
                    		tree.scrollPathToVisible(new TreePath(node.getPath()));
                    	}

                    }
                });
                button.setText("Add Group");
            }
            if (i == 4) {
                // Add action listener to open UserView on button press
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                		JDialog dialog = new JDialog(dialogFrame, "Show User Total");
                		dialog.setSize(250, 250);
                		dialog.add(new Label("" + manager.getUserList().size()));
                		dialog.setVisible(true);
                    }
                });
                button.setText("Show User Total");
            }
            if (i == 5) {
                // Add action listener to open UserView on button press
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                		JDialog dialog = new JDialog(dialogFrame, "Show Group Total");
                		dialog.setSize(250, 250);
                		dialog.add(new Label("" + manager.getGroupList().size()));
                		dialog.setVisible(true);
                    }
                });
                button.setText("Show Group Total");
            }
            if (i == 6) {
                // Add action listener to open UserView on button press
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                		JDialog dialog = new JDialog(dialogFrame, "Show Messages Total");
                		dialog.setSize(250, 250);
                		dialog.add(new Label("" + manager.getMessageCount()));
                		dialog.setVisible(true);
                    }
                });
                button.setText("Show Messages Total");
            }
            if (i == 7) {
                // Add action listener to open UserView on button press
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                		JDialog dialog = new JDialog(dialogFrame, "Show Messages Total");
                		dialog.setSize(250, 250);
                		dialog.add(new Label("" + ((double)manager.getPositivityCount()/manager.getMessageCount())));
                		dialog.setVisible(true);
                    }
                });
                button.setText("Show Positive Percentage");
            }
            buttonPanel.add(button);
        }

        // Create a split pane for the text areas
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(userID), new JScrollPane(groupID));
        splitPane.setDividerLocation(150);

        // Add components to the content pane
        add(buttonPanel, BorderLayout.NORTH);
        add(splitPane, BorderLayout.WEST);
        add(new JScrollPane(tree), BorderLayout.CENTER);
    }
    

}