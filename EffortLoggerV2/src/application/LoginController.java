package application;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import application.UserDatabase;

public class LoginController {
	@FXML
	private TextField userField;
	@FXML
	private TextField passField;
	@FXML
	private Label incorrectLabel;
	@FXML
	private Label incorrectLabel2;
	
	
	@FXML
	void login(ActionEvent event) throws Exception {
		if(!(UserDatabase.authenticate(userField.getText(),passField.getText()))){
			incorrectLabel.visibleProperty().set(true);
		} else {
			Parent root = FXMLLoader.load(getClass().getResource("ApplicationScene.fxml"));
			Scene scene = new Scene(root);
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			stage.setTitle("EffortLogger V2");
			stage.setScene(scene);
			stage.setMaximized(true);
			stage.show();
		}
	}
	
	@FXML
	void register(ActionEvent event) throws Exception{
		if(UserDatabase.addUser(userField.getText(), passField.getText())) {login(event);}
		else {
			incorrectLabel2.visibleProperty().set(true);
		}
	}
}
