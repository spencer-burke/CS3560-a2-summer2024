package mainpackage;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class JUserView extends JFrame {
	static JFrame dialogFrame;
	
    public JUserView(User arg, MessageManager manager) {
    	dialogFrame = new JFrame("Dialog Frame");
        // Set the title of the UserView frame
        setTitle("User View");

        // Set the size of the UserView frame
        setSize(600, 400);

        // Set the default close operation
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Set the layout manager for the content pane
        setLayout(new BorderLayout());
        
        // Create the text area
        JTextArea userID = new JTextArea("User ID");
        JTextArea tweetMessage = new JTextArea("tweet message");
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(userID), new JScrollPane(tweetMessage));
        splitPane.setDividerLocation(300);
        
        // Create the list views
        DefaultListModel<String> currentFollowingModel = new DefaultListModel<>();
        JList<String> currentFollowing = new JList<>(currentFollowingModel);
        DefaultListModel<String> feedModel = new DefaultListModel<>();
        JList<String> feed = new JList<>(feedModel);
        
        for (String currMessage : arg.getFeed())
        	feedModel.addElement(currMessage);
        
        for (User currFollow : arg.getFollowing())
        	currentFollowingModel.addElement(currFollow.getID());
        
        // Add the text panel at the beginning so it can be updated
        JTextArea updateTextField = new JTextArea("");
        
        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel(new GridLayout(2, 4)); 
        for (int i = 1; i <= 2; i++) {
            JButton button = new JButton("Button " + i);
            if (i == 1) {
            	 // Add action listener to open UserView on button press
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // get the user id to follow
                    	String txtUserID = userID.getText();
                    	// make sure it's valid
                    	if (!manager.getUserList().contains(new User(txtUserID))) {
                    		JDialog dialog = new JDialog(dialogFrame, "Error Dialog");
                    		dialog.setSize(250, 250);
                    		dialog.add(new Label("Error User Does Not Exist"));
                    		dialog.setVisible(true);
                    	}
                    	else {
                    		// follow the user
                    		int userFollowIndex = manager.getUserList().indexOf(new User(txtUserID));
                    		System.out.printf("Going to follow %s\n", manager.getUserList().get(userFollowIndex).getID());
                    		
                    		// update the followed user
                    		manager.updateFollow(userFollowIndex, arg);
                    		User current = manager.getUserList().get(userFollowIndex);
                    		arg.follow(current);
                    		currentFollowingModel.addElement(current.getID());
                    	}

                    }
                });
                button.setText("Follow User");
            }
            if (i == 2) {
           	 // Add action listener to open UserView on button press
               button.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                	   // make message
                	   String message = tweetMessage.getText();
                	   message = arg.getID() + " : " + message; 
                	   arg.addMessage(message);
                	   manager.updateMessageCount();
                	   
                	   // update positivity
                	   String[] stringToParse = message.trim().split("\\s+");
                	   updatePositivity(manager, stringToParse);
                	   
                	   // go through followings, and update feed
                	   ArrayList<User> followers = arg.getFollowers();
                	   for (User curr : followers) {
                		   curr.addMessage(message);
                		   curr.update();
                	   }
                	   arg.update();
                	   updateTextField.setText("Update time: " + Long.toString(arg.getLastUpdateTime()));
                   }
               });
               button.setText("Post Tweet");
           }
           buttonPanel.add(button);
        }
        
        // Create the text area to display the creation time
        JTextArea creationTextField = new JTextArea("");
        buttonPanel.add(creationTextField);
        creationTextField.setText("Creation time: " + Long.toString(arg.getCreationTime()));
        
        // Create the text area to display the update time
        buttonPanel.add(updateTextField);
        updateTextField.setText("Update time: " + Long.toString(arg.getLastUpdateTime()));
        
        // Create a panel for the list views and set its layout
        JPanel listPanel = new JPanel(new GridLayout(1, 2));
        listPanel.add(new JScrollPane(currentFollowing));
        listPanel.add(new JScrollPane(feed));
        // Add components to the content pane
        add(buttonPanel, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
        add(listPanel, BorderLayout.SOUTH);

        // Make the UserView frame visible
        setVisible(true);
    }
    
    /**
     * Update the positivity of the message for manager
     * @param manager the manager object to keep track of users and groups
     * @param words the split list of words to parse
     */
    private void updatePositivity(MessageManager manager, String[] words) {
 	   for (String curr : words)
		   if (curr.equals("great") || curr.equals("cool")) {
			   manager.updatePositivity();
			   return;
		   }
    }
}