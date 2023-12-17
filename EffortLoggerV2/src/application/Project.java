package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

public class Project {
	TextField name;
	TextField[] lcs = new TextField[26];
	ObservableList<LifeCycleStep> lcsList = FXCollections.observableArrayList();
	
	public Project() {
		name = new TextField("");
		name.setPromptText("");
		for (int i = 1; i <= 25; i++) {
			lcs[i] = new TextField("");
			lcs[i].setPromptText("");
		}
	}
	
	public ObservableList<Activity> getEffortLogs() throws SQLException {
		Statement stmt = Main.connection.createStatement();
    	ResultSet result = stmt.executeQuery("SELECT * FROM effort_log_table WHERE userID = " + Main.userID + " AND project = '" + this.name.getText() + "';");
    	ObservableList<Activity> output = FXCollections.observableArrayList();
    	int i = 0;
    	while(result.next()) {
    		Activity tempActivity = new Activity(name.toString(), result.getString("LifeCycleStep"), result.getString("EffortCategory"), result.getString("Other"), result.getString("Start"), result.getString("End"), result.getString("Date"));
    		output.add(tempActivity);
    		i += 1;
    	}
    	return output;
	}
	
	@Override
	public String toString() {
		return name.getText();
	}
}
