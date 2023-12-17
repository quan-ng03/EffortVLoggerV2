package application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;
import java.util.Base64;
//import java.util.List;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;

/* Represents the user database and the methods it uses to
 * validate and store users in the database as well as keeping
 * those users' information secure
 */

public class UserDatabase {
	private static ArrayList<User> users = new ArrayList<User>();
	private static int currentID;
	// Constructor for UserDatabase
	public static void initailizeUserDatabase() throws SQLException
	{
		currentID = 1;
		ResultSet rs = Main.statement.executeQuery("SELECT * FROM user_table");
		while (rs.next()){
			String current_user = rs.getString("username");
			int current_ID = rs.getInt("userID");
			System.out.println(current_user + " " + current_ID);
			User user = new User(current_user,current_ID);
			users.add(user);
			currentID += 1;
		}
		
	}
	
	// Tries to add a new user to the database
	public static boolean addUser(String username, String password) throws NoSuchAlgorithmException
	{
		// All users must have a username longer than 16 characters
		if(username.length() < 16)
		{
			return false;
		}
		
		// All users must have a password longer than 16 characters
		if(password.length() >= 16)
		{
			// Uses Regex that searches for a digit and a special character in a string
			Pattern digit = Pattern.compile("[0-9]");
			Pattern special = Pattern.compile("[\\[!@#$%^&*() _-{}|<>?\\~`,./\\]]");
			// Matches the string with the Regex
			Matcher hasDigit = digit.matcher(password);
			Matcher hasSpecial = special.matcher(password);
			// Uses find() to determine if the password contains a number and a special character
			boolean foundDigit = hasDigit.find();
			boolean foundSpecial = hasSpecial.find();
			System.out.println(foundDigit + " " + foundSpecial);
			if(foundDigit && foundSpecial)
			{
				// Checks if that user already exists in the database
				if(findUser(username) == false)
				{
					// Hashes password before storing it
					String hashedPassword = hashPassword(password);
					// Creates new user and adds it to the database
					User newUser = new User(username, currentID);
					System.out.println(newUser.toString());
					
					//System.out.println("Decrypted Password: " + decryptPassword(newUser.getPassword()));
					try
					{
						PreparedStatement ps = Main.connection.prepareStatement("INSERT INTO user_table (`userID`, `username`, `hash`) VALUES (?, ?, ?);");
						ps.setInt(1, currentID);
						ps.setString(2, username);
						ps.setString(3, hashedPassword);
						Boolean result = ps.execute();
						if(result) {users.add(newUser);currentID += 1;}
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					return true;
				}
			}
		}
		else
		{
			return false;
		}
		return false;
	}
	
	//Find User
	public static boolean findUser(String username) throws NoSuchAlgorithmException
	{
		for(int i = 0; i < users.size(); i++)
		{
			boolean foundUsername = users.get(i).getUsername().equals(username);
			if(foundUsername)
			{
				return true;
			}
		}
		return false;
	}
	// Find if there is a user with that username and password
	public static boolean authenticate(String username, String password) throws NoSuchAlgorithmException, SQLException
	{
		String hash = hashPassword(password);
		PreparedStatement ps = Main.connection.prepareStatement("SELECT * FROM user_table WHERE username = ? AND hash = ?");
		ps.setString(1, username);
		ps.setString(2, hash);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			Main.userID = rs.getInt("userID");
			return true;
		} else {return false;}
	}
	
	// Hashes password given
	public static String hashPassword(String password) throws NoSuchAlgorithmException
	{
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		return Base64.getEncoder().encodeToString(digest.digest(password.getBytes(StandardCharsets.UTF_8)));
	}
	
	// Compares Hashes
	private static boolean compareHash(String hash1, String hash2)
	{
		System.out.println(hash1 + ", " + hash2);
		return hash1.equals(hash2);
	}
}