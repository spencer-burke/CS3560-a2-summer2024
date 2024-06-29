package mainpackage;
import java.util.ArrayList;

public class UserGroup implements Comparable {
	private ArrayList<User> usersInGroup;
	private String groupID;
	private long creationTime;
	
	/**
	 * The constructor used to place a group in the root group
	 * @param arg the id of the group being created
	 */
	public UserGroup(String arg) {
		usersInGroup = new ArrayList<User>();
		groupID = arg;
		creationTime =  System.currentTimeMillis();
	}

	/**
	 * Add a user to this group
	 * @param arg the user to add
	 */
	public void addUserToGroup(User arg) { usersInGroup.add(arg); }

	/**
	 * Get the UserGroup id
	 * @return the UserGroup id
	 */
	public String getID() { return groupID; }
	
	/**
	 * Checks if id has a space in it
	 * @return true if there is a space in the id
	 */
	public boolean idHasSpace() {
		char curr;
		for (int i = 0; i < groupID.length(); i++) {
			curr = groupID.charAt(i);
			if (curr == ' ')
				return true;
		}
		return false;
	}
	
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		if (o != null && o instanceof UserGroup)
			return groupID.compareTo( ( (UserGroup) o ).groupID );
		return -1;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o != null && o instanceof UserGroup)
			return groupID.compareTo( ( (UserGroup)o ).groupID ) == 0;
		return false;
	}
}
