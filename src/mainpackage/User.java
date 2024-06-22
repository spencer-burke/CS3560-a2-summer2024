package mainpackage;

import java.util.ArrayList;

public class User implements Comparable {
	private String id;
	private String groupID;
	private ArrayList<User> followers;
	private ArrayList<User> followings;
	private ArrayList<String> feed;
	private ArrayList<String> personalMessages;
	
	/**
	 * Constructor for the User class
	 * @param id the id for the user
	 */
	public User(String id) {
		this.id = id;
		this.groupID = "Root";
		this.followers = new ArrayList<User>();
		this.followings = new ArrayList<User>();
		this.feed = new ArrayList<String>();
		this.personalMessages = new ArrayList<String>();
	}
	
	/**
	 * Constructor for User class with specific group
	 * @param id the id for the User
	 * @param groupID the group id for the user to placed into
	 */
	public User(String id, String groupID) {
		this.id = id;
		this.groupID = groupID;
		this.followers = new ArrayList<User>();
		this.followings = new ArrayList<User>();
		this.feed = new ArrayList<String>();
		this.personalMessages = new ArrayList<String>();
	}

	/**
	 * Add's a follower to the user
	 * @param arg the user following
	 */
	public void addFollower(User arg) { followers.add(arg); }

	/**
	 * Makes the user follow another user
	 * @param arg the user to follow
	 */
	public void follow(User arg) { followings.add(arg); }

	/**
	 * The message to add to the User
	 * @param arg
	 */
	public void addMessage(String arg) { feed.add(arg); }
	
	/**
	 * Returns the id of the user
	 * @return the id of the user
	 */
	public String getID() { return id; }
	
	/**
	 * Returns the feed of the user
	 * @return the feed
	 */
	public ArrayList<String> getFeed() { return feed; }
	
	/**
	 * Returns the followers
	 * @return the followers
	 */
	public ArrayList<User> getFollowers() { return followers; }
	
	/**
	 * Add the personal message to the personal messages
	 * @param arg the message to add
	 */
	public void addPersonalMessage(String arg) { personalMessages.add(arg); }
	
	/**
	 * Get the messages of the user
	 * @return the messages of the user
	 */
	public ArrayList<String> getMessages() { return personalMessages; }
	
	/**
	 * Get the users the user is following
	 * @return the users the user is following
	 */
	public ArrayList<User> getFollowing() { return followings; }
	
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		if (o != null && o instanceof User)
			return id.compareTo( ( (User) o ).id );
		return -1;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o != null && o instanceof User)
			return id.compareTo( ( (User)o ).id ) == 0;
		return false;
	}
	
}
