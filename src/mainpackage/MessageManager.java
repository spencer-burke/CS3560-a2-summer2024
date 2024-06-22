package mainpackage;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class MessageManager {
	private ArrayList<User> totalUsers;
	private ArrayList<UserGroup> totalGroups;
	private static int COUNT = 0;
	private int positivityCount;
	private int messageCount;
	
	/**
	 * Constructor for the MessageManager class
	 */
	public MessageManager() {
		if (COUNT == 0)
			COUNT++;
		if (COUNT == 1 ) {
			totalUsers = new ArrayList<User>();
			totalGroups = new ArrayList<UserGroup>();
			positivityCount = 0;
			messageCount = 0;
		}
		else {
			System.out.println("[ERROR]: only 1 allowed");
		}
	}
	
	/**
	 * Add a group to the total groups
	 * @param arg the group to add
	 */
	public void addGroup(UserGroup arg) { totalGroups.add(arg); }
	
	/**
	 * Add a user to the total users
	 * @param arg the user to add
	 */
	public void addUser(User arg) { totalUsers.add(arg); }
	
	/**
	 * Get a list of the Users
	 * @return a copy of the list of users
	 */
	public ArrayList<User> getUserList() { return (ArrayList<User>) totalUsers.clone(); }
	
	/**
	 * Get a list of User groups
	 * @return a copy of the list of user groups
	 */
	public ArrayList<UserGroup> getGroupList() { return (ArrayList<UserGroup>) totalGroups.clone(); }
	
	/**
	 * Update the followers
	 * @param index  position of the follower to update
	 * @param follower the follower to follow
	 */
	public void updateFollow(int index, User follower) { totalUsers.get(index).addFollower(follower); }
	
	/**
	 * get a user to pass into a view
	 * @return the user to pass into the view
	 */
	public User getUser(String id) {
		int index = totalUsers.indexOf(new User(id));
		return totalUsers.get(index);
	}
	
	/**
	 * Update the positivity counter
	 */
	public void updatePositivity() { positivityCount++; }
	
	/**
	 * Update the message count
	 */
	public void updateMessageCount() { messageCount++; }
	
	/**
	 * Get the count of messages
	 * @return the message count
	 */
	public int getMessageCount() { return messageCount; }
	
	/**
	 * Get the positivity count
	 * @return the positivity count
	 */
	public int getPositivityCount() { return positivityCount; }
}
