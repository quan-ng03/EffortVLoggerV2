// Represents user and their login information
package LoginProcess;

public class User {
	private String username;
	private String password;
	
	// User constructor
	public User(String username, String password)
	{
		this.username = username;
		this.password = password;
	}
	
	// Simple toString Function
	public String toString()
	{
		return "Username: " + this.username + "\nPassword: " + this.password + "\n";
	}
	
	// Gets username
	public String getUsername()
	{
		return this.username;
	}
	
	// Gets password
	public String getPassword()
	{
		return this.password;
	}
}
