package application;
	
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application 
{
	public static Connection connection = null;
	public static int userID = 0;
	public static int currentEffortLogID = 0;
	public static int currentDefectLogID = 0;
	public static int currentUserStoryID = 0;
	public static Statement statement = null;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	// Start method open the login scene
	@Override
	public void start(Stage loginStage) throws Exception {
		connect();
		UserDatabase.initailizeUserDatabase();
		Parent root = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
		Scene loginScene = new Scene(root);
		loginStage.setTitle("EffortLogger V2");
		loginStage.setScene(loginScene);
		loginStage.show();
	}
	
	@Override
	public void stop() throws SQLException {
		statement.close();
		connection.close();
	}
	
	public void connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://68.96.62.115:3306/effortloggerv2","client","cse360@th64");
		System.out.println("Connected");
		statement = connection.createStatement();
	}
}
