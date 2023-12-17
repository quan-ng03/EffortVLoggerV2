package application;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;

import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import application.UserDatabase;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.EventObject;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ApplicationController 
{
	private ObservableList<EffortLog> effortLogList = FXCollections.observableArrayList();
    private ObservableList<DefectLog> defectLogList = FXCollections.observableArrayList();
    private ObservableList<Project> projectList = FXCollections.observableArrayList();
    private ObservableList<LifeCycleStep> lifeCycleStepList = FXCollections.observableArrayList();
    private ObservableList<String> effortCategoryList = FXCollections.observableArrayList();
    private ObservableList<String> planList = FXCollections.observableArrayList();
    private ObservableList<String> deliverableList = FXCollections.observableArrayList();
    private ObservableList<String> interruptionList = FXCollections.observableArrayList();
    private ObservableList<String> defectCategoryList = FXCollections.observableArrayList();
    private ObservableList<LifeCycleStep> lifeCycleStepComboList = FXCollections.observableArrayList();

    private Project[] projectArray = new Project[11];
    private LifeCycleStep[] lifeCycleStepArray = new LifeCycleStep[51];
    private TextField[] planArray = new TextField[11];
    private TextField[] deliverableArray = new TextField[11];
    private TextField[] interruptionArray = new TextField[11];
    private TextField[] defectCategoryArray = new TextField[16];
	
    private Activity newActivity = null;
	private Timeline timeline;
	
	@FXML
    private Label ecClockLabel;

    @FXML
    private ComboBox<String> ecEffortCategoryCombo;

    @FXML
    private Label ecEffortCategoryLabel;

    @FXML
    private ComboBox<String> ecEtcCombo;

    @FXML
    private Label ecEtcLabel;

    @FXML
    private ComboBox<LifeCycleStep> ecLifeCycleStepCombo;

    @FXML
    private Label ecOtherLabel;

    @FXML
    private TextField ecOtherTextField;

    @FXML
    private Button ecProceedToDefectConsoleButton;

    @FXML
    private Button ecProceedToEffortLogEditorButton;

    @FXML
    private ComboBox<Project> ecProjectCombo;

    @FXML
    private Rectangle ecRectangle;

    @FXML
    private Button ecStartAnActivityButton;

    @FXML
    private Button ecStopThisActivityButton;
    
    //Effort Log Editor Objects
    @FXML
    private ComboBox<Project> eeProjectCombo;
    
    @FXML
    private ComboBox<Activity> eeLogCombo;
    
    @FXML
    private ComboBox<LifeCycleStep> eeLifeCycleStepCombo;
    
    @FXML
    private ComboBox<String> eeEtcCombo;
    
    @FXML
    private ComboBox<String> eeEffortCategoryCombo;
    
    @FXML
    private Label eeEtcLabel;
    
    @FXML
    private Label eeOtherLabel;
    
    @FXML
    private TextField eeOtherTextField;
	
	// Definitions objects
    
	@FXML
    private GridPane dDefectCategoryGrid;

    @FXML
    private GridPane dDeliverableGrid;

    @FXML
    private GridPane dEffortCategoryGrid;

    @FXML
    private GridPane dInterruptionGrid;

    @FXML
    private GridPane dLifeCycleStepGrid;

    @FXML
    private GridPane dPlanGrid;

    @FXML
    private GridPane dProjectGrid;

    @FXML
    private GridPane dDefectCategoryDeletionGrid;

    @FXML
    private GridPane dDeliverableDeletionGrid;

    @FXML
    private GridPane dInterruptionDeletionGrid;

    @FXML
    private GridPane dLifeCycleStepDeletionGrid;

    @FXML
    private GridPane dPlanDeletionGrid;

    @FXML
    private GridPane dProjectDeletionGrid;
	
	@FXML
	private TextField a_extra_tfield;
	
	@FXML
	private ComboBox a_defect_project_name_combo;
	
	@FXML
	private Button createNewDefectButton;
	
	@FXML
	private TextField defectName;
	
	@FXML
	private TextArea defectSymptoms;
	
	@FXML
	private ListView stepsWhenInjected;
	
	@FXML
	private ListView stepsWhenRemoved;
	
	@FXML
	private ListView defectCategory;
	
	@FXML
	private ComboBox defectFix;
	
	@FXML
	private Button deleteCurrentDefect;
	
	@FXML
	void startActivity(ActionEvent event) {
		newActivity = new Activity(this.ecProjectCombo.getValue().toString(), this.ecLifeCycleStepCombo.getValue().toString(), (String)this.ecEffortCategoryCombo.getValue(), this.ecOtherTextField.getText());
		newActivity.startActivity();
		DecimalFormat formatter = new DecimalFormat("00");
		KeyFrame kf = new KeyFrame(Duration.millis(1000), e -> {
			ecClockLabel.setText(newActivity.getDurationFromNow().toHours() + ":" + formatter.format(newActivity.getDurationFromNow().toMinutes() % 60) + ":" + formatter.format(newActivity.getDurationFromNow().toSeconds() % 60));
		});
		timeline = new Timeline(kf);
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
		
		this.ecStartAnActivityButton.setDisable(true);
		this.ecEffortCategoryCombo.setDisable(true);
		this.ecOtherTextField.setDisable(true);
		this.ecLifeCycleStepCombo.setDisable(true);
		this.ecProjectCombo.setDisable(true);
		this.ecEtcCombo.setDisable(true);
	}
	
	@FXML
	void endActivity(ActionEvent event) throws SQLException {
		if(newActivity == null) {System.out.println("rough");}
		newActivity.endActivity();
		sendActivityToDatabase(newActivity);
		newActivity = null;
		timeline.stop();
		ecClockLabel.setText("Clock");
		
		this.ecStartAnActivityButton.setDisable(false);
		this.ecEffortCategoryCombo.setDisable(false);
		this.ecOtherTextField.setDisable(false);
		this.ecLifeCycleStepCombo.setDisable(false);
		this.ecProjectCombo.setDisable(false);
		this.ecEtcCombo.setDisable(false);
		
	}
	
	
	private void sendActivityToDatabase(Activity sentActivity) throws SQLException {
		String[] debrief = this.newActivity.getDebrief();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd").withZone(ZoneId.systemDefault());
		PreparedStatement ps = Main.connection.prepareStatement("INSERT INTO effort_log_table (`userID`, `logID`, `project`, `LifeCycleStep`, `EffortCategory`, `Other`, `Start`, `End`, `Date`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");
		ps.setInt(1, Main.userID);
		ps.setInt(2, 0);
		ps.setString(3, debrief[0]);
		ps.setString(4, debrief[1]);
		ps.setString(5, debrief[2]);
		ps.setString(6, debrief[3]);
		ps.setString(7, this.newActivity.getStart().toString().substring(11,19));
		ps.setString(8, this.newActivity.getEnd().toString().substring(11,19));
		ps.setString(9, formatter.format(this.newActivity.getStart()));
		Boolean result = ps.execute();
		//Main.currentLogID += 1; FOR FUTURE IMPLEMENTATION WITH EFFORTLOGDATABASE
		return;
	}
	
	@FXML
	void createDefectLog(ActionEvent event) throws SQLException {
		DefectLog newDefectLog = new DefectLog(0, this.defectName.getText(), this.defectSymptoms.getText(), 1, 1, 1, 1, 1, (String)this.a_defect_project_name_combo.getValue());
		PreparedStatement ps = Main.connection.prepareStatement("INSERT INTO defect_log_table (`index`, `name`, `detail`, `injected`, `removed`, `category`, `status`, `fix`, `project`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");
		ps.setInt(1, newDefectLog.getIndex());
		ps.setString(2, newDefectLog.getName());
		ps.setString(3, newDefectLog.getDetail());
		ps.setInt(4, newDefectLog.getInjected());
		ps.setInt(5, newDefectLog.getRemoved());
		ps.setInt(6, newDefectLog.getCategory());
		ps.setInt(7, newDefectLog.getStatus());
		ps.setInt(8, newDefectLog.getFix());
		ps.setString(9, newDefectLog.getProject());
		Boolean result = ps.execute();
		System.out.println("Was INSERT successful?: " + result);
		return;
	}
	
	@FXML
	void clearEffortEntry(ActionEvent event) throws SQLException {}
	
	@FXML
	void deleteEffortEntry(ActionEvent event) throws SQLException {}
	
	@FXML
	void splitEffortEntry(ActionEvent event) throws SQLException {}
    
	@FXML
	void updateEffortEntry(ActionEvent event) throws SQLException {}

  //  ObservableList<String> list = FXCollections.observableArrayList();
    private boolean isValidNum(String input, int minValue, int maxValue, int maxLength) {
    	if (input.length() > maxLength) {
    		return false;
    	}
    	for (int i = 0; i < input.length(); i++) {
			if (!Character.isDigit(input.charAt(i))) {
				return false;
			}
		}
		if (Integer.parseInt(input) < minValue || Integer.parseInt(input) > maxValue) {
			return false;
		}
    	return true;
    }
    
    private void largeTextFieldError(TextField tf, String errorMessage) {
    	tf.setText(errorMessage);
    	tf.setStyle("-fx-text-fill: red;");
		tf.positionCaret(tf.getText().length());
		tf.setOnMouseClicked(e -> {
			tf.setText(tf.getPromptText());
    		tf.setStyle("-fx-text-fill: black;");
    		tf.setOnMouseClicked(null);
    		tf.setOnKeyPressed(null);
        });
		tf.setOnKeyPressed(e -> {
			if (e.getCode() != KeyCode.ENTER) {
				tf.setText(tf.getPromptText());
				tf.setStyle("-fx-text-fill: black;");
			}
    		tf.setOnMouseClicked(null);
    		tf.setOnKeyPressed(null);
		});
    }
    
    private void smallTextFieldError(TextField tf, String errorMessage) {
    	tf.setText(errorMessage);
    	tf.setStyle("-fx-text-fill: red;");
    	tf.setOnMouseClicked(e -> {
			tf.setText(tf.getPromptText());
    		tf.setStyle("-fx-text-fill: black;");
    		tf.setOnMouseClicked(null);
    		tf.setOnKeyPressed(null);
        });
		tf.setOnKeyPressed(e -> {
			if (e.getCode() != KeyCode.LEFT && e.getCode() != KeyCode.RIGHT && e.getCode() != KeyCode.ENTER) {
				tf.setText(tf.getPromptText());
				tf.setStyle("-fx-text-fill: black;");
				tf.setOnMouseClicked(null);
				tf.setOnKeyPressed(null);
			}
		});
    }
    
    @FXML
    private void initialize() throws SQLException {
    	
    	
    	initializeDefinitions();
    	
    	ecProjectCombo.setItems(projectList);
    	eeProjectCombo.setItems(projectList);
    	ecEffortCategoryCombo.setItems(effortCategoryList);
    	eeEffortCategoryCombo.setItems(effortCategoryList);
    	ecEtcLabel.setText("");
    	eeEtcLabel.setText("");
    	ecOtherLabel.setVisible(false);
    	eeOtherLabel.setVisible(false);
    	ecOtherTextField.setVisible(false);
    	eeOtherTextField.setVisible(false);
    	
    	ecProjectCombo.setOnAction(e -> {
    		ecLifeCycleStepCombo.setItems(null);
    		ecEffortCategoryCombo.valueProperty().set(null);
    		ecEtcCombo.setItems(null);
    		ecEtcLabel.setText("");
    		
    		ecLifeCycleStepCombo.setItems(ecProjectCombo.getValue().lcsList);
    	});
    	eeProjectCombo.setOnAction(e -> {
    		eeLifeCycleStepCombo.setItems(null);
    		eeEffortCategoryCombo.valueProperty().set(null);
    		eeEtcCombo.setItems(null);
    		eeEtcLabel.setText("");
    		
    		eeLifeCycleStepCombo.setItems(eeProjectCombo.getValue().lcsList);
    		try {
				eeLogCombo.setItems(eeProjectCombo.getValue().getEffortLogs());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
    	});
    	
    	ecLifeCycleStepCombo.setOnAction(e -> {
    		if (ecProjectCombo.getValue().lcsList.isEmpty()) {
    			return;
    		}
    		if (ecLifeCycleStepCombo.getValue().EC.getText().equals("")) {
    			return;
    		}
    		ecEffortCategoryCombo.getSelectionModel().select(Integer.parseInt(ecLifeCycleStepCombo.getValue().EC.getText()));
    		if (ecLifeCycleStepCombo.getValue().D.getText().equals("")) {
    			return;
    		}
    		if (ecEffortCategoryCombo.getValue() == "Plans") {
    			ecEtcCombo.setValue(planArray[Integer.parseInt(ecLifeCycleStepCombo.getValue().D.getText())].getText());
    		}
    		else if (ecEffortCategoryCombo.getValue() == "Deliverables") {
    			ecEtcCombo.setValue(deliverableArray[Integer.parseInt(ecLifeCycleStepCombo.getValue().D.getText())].getText());
    		}
    		else if (ecEffortCategoryCombo.getValue() == "Interruptions") {
    			ecEtcCombo.setValue(interruptionArray[Integer.parseInt(ecLifeCycleStepCombo.getValue().D.getText())].getText());
    		}
    	});
    	eeLifeCycleStepCombo.setOnAction(e -> {
    		if (eeProjectCombo.getValue().lcsList.isEmpty()) {return;} 
    		if (ecLifeCycleStepCombo.getValue().EC.getText().equals("")) {return;}
    		
    		ecEffortCategoryCombo.getSelectionModel().select(Integer.parseInt(ecLifeCycleStepCombo.getValue().EC.getText()));
    		
    		if (ecLifeCycleStepCombo.getValue().D.getText().equals("")) {return;}
    		switch(ecEffortCategoryCombo.getValue()) {
    			case("Plans"):
    				ecEtcCombo.setValue(planArray[Integer.parseInt(ecLifeCycleStepCombo.getValue().D.getText())].getText());
    				break;
    			case("Deliverables"):
    				ecEtcCombo.setValue(planArray[Integer.parseInt(ecLifeCycleStepCombo.getValue().D.getText())].getText());
    				break;
    			case("Interruptions"):
    				ecEtcCombo.setValue(interruptionArray[Integer.parseInt(ecLifeCycleStepCombo.getValue().D.getText())].getText());
    				break;
    		}
    	});
    	
    	ecEffortCategoryCombo.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
    		if (newValue == null) {
    			ecEtcLabel.setText("");
    			ecOtherLabel.setVisible(false);
    	    	ecOtherTextField.setVisible(false);
    		}
    		else if (newValue.equals("Plans")) {
    			ecEtcLabel.setText("Plan");
    			ecEtcCombo.setItems(planList);
    			ecOtherLabel.setVisible(false);
    	    	ecOtherTextField.setVisible(false);
    		}
    		else if (newValue.equals("Deliverables")) {
    			ecEtcLabel.setText("Deliverable");
    			ecEtcCombo.setItems(deliverableList);
    			ecOtherLabel.setVisible(false);
    	    	ecOtherTextField.setVisible(false);
    		}
    		else if (newValue.equals("Interruptions")) {
    			ecEtcLabel.setText("Interruption");
    			ecEtcCombo.setItems(interruptionList);
    			ecOtherLabel.setVisible(false);
    	    	ecOtherTextField.setVisible(false);
    		}
    		else if (newValue.equals("Defects")) {
    			ecEtcLabel.setText("Defect");
    			ecEtcCombo.setItems(null);
    			ecOtherLabel.setVisible(false);
    	    	ecOtherTextField.setVisible(false);
    		}
    		else if (newValue.equals("Other")) {
    			ecEtcLabel.setText("");
    			ecEtcCombo.setItems(null);
    			ecOtherLabel.setVisible(true);
    	    	ecOtherTextField.setVisible(true);
    		}
    	});
    	eeEffortCategoryCombo.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
    		if(newValue == null) {
    			eeEtcLabel.setText("");
				eeOtherLabel.setVisible(false);
				eeOtherTextField.setVisible(false);
    		}
    		switch(newValue) {
    			case("Plans"):
    				eeEtcLabel.setText("Plan");
    				eeEtcCombo.setItems(planList);
    				eeOtherLabel.setVisible(false);
    				eeOtherTextField.setVisible(false);
    				break;
    			case("Deliverables"):
    				eeEtcLabel.setText("Deliverable");
    				eeEtcCombo.setItems(deliverableList);
    				eeOtherLabel.setVisible(false);
    				eeOtherTextField.setVisible(false);
    				break;
    			case("Interruptions"):
    				eeEtcLabel.setText("Interruption");
    				eeEtcCombo.setItems(interruptionList);
    				eeOtherLabel.setVisible(false);
    				eeOtherTextField.setVisible(false);
    				break;
    			case("Defects"):
    				eeEtcLabel.setText("Defect");
    				eeEtcCombo.setItems(null);
    				eeOtherLabel.setVisible(false);
    				eeOtherTextField.setVisible(false);
    				break;
    			case("Other"):
    				eeEtcLabel.setText("");
    				eeEtcCombo.setItems(null);
    				eeOtherLabel.setVisible(true);
    				eeOtherTextField.setVisible(true);
    				break;
    		}
    	});
    	
    	ecEtcCombo.setOnAction(e -> {
    		if (ecEtcCombo.getValue() == null) {
    			ecEtcLabel.setText("");
    			ecOtherLabel.setVisible(false);
    	    	ecOtherTextField.setVisible(false);
    		}
    		else if (ecEtcCombo.getValue().equals("Other")) {
    			ecOtherLabel.setVisible(true);
    	    	ecOtherTextField.setVisible(true);
    		}
    		else {
    			ecOtherLabel.setVisible(false);
    	    	ecOtherTextField.setVisible(false);
    		}
    	});
    	eeEtcCombo.setOnAction(e -> {
    		if (eeEtcCombo.getValue() == null) {
    			eeEtcLabel.setText("");
    			eeOtherLabel.setVisible(false);
    	    	eeOtherTextField.setVisible(false);
    		}
    		else if (eeEtcCombo.getValue().equals("Other")) {
    			eeOtherLabel.setVisible(true);
    	    	eeOtherTextField.setVisible(true);
    		}
    		else {
    			eeOtherLabel.setVisible(false);
    	    	eeOtherTextField.setVisible(false);
    		}
    	});
    	
    }
    
    private void initializeDefinitions() throws SQLException {
    	//
    	// Definitions Controller
    	//
    	
    	Statement stmt;
    	ResultSet result;
    	
    	// Initialize array that stores project table textfields
    	for (int i = 1; i <= 10; i++) {
    		projectArray[i] = new Project();
    	}
    	
    	stmt = Main.connection.createStatement();
    	result = stmt.executeQuery("SELECT * FROM project_table WHERE userID = " + Main.userID + ";");
    	while(result.next()) {
    		int index = result.getInt("index");
    		projectArray[index].name.setText(result.getString("name"));
    		projectArray[index].name.setPromptText(result.getString("name"));
    		projectArray[index].name.setEditable(false);
    		for (int i = 1; i < 26; i++) {
				projectArray[index].lcs[i].setText(result.getString("lcs" + i));
				projectArray[index].lcs[i].setPromptText(result.getString("lcs" + i));
			}
    	}
    	result.close();
    	stmt.close();
    	
    	for (int k = 1; k <= 10; k++) {
			if (!projectArray[k].name.getText().equals("")) {
				projectList.add(projectArray[k]);
			}
		}
    	
		for (int i = 1; i <= 10; i++) {
			for (int j = 0; j < 26; j++) {
				
				TextField tf;
				
				if (j == 0) {
					
					tf = projectArray[i].name;
					
					tf.setOnKeyReleased(a -> {
						if (tf.getStyle().equals("-fx-text-fill: red;")) {
							tf.setText(tf.getPromptText());
		            		tf.setStyle("-fx-text-fill: black;");
							return;
						}
						if (a.getCode().equals(KeyCode.ENTER)) {
		            	int row = GridPane.getRowIndex(tf); 
		            	
	            		if (tf.getText().isEmpty() || tf.getText().length() > 100) {
							largeTextFieldError(tf, "Please enter a project name that is between 1 and 100 characters.");
							return;
						}
						if (!projectList.isEmpty()) {
	            			for (int k = 0; k < projectList.size(); k++) {
	            				if (tf.getText().equals(projectList.get(k).name.getText())) {
									largeTextFieldError(tf, "That life cycle step name already exists.");
									return;
								}
	            			}
	            		}
						
						tf.setText(tf.getText());
						tf.setPromptText(tf.getText());
						try {
							PreparedStatement pstmt = Main.connection.prepareStatement("INSERT INTO project_table (userID,`index`,name) VALUES (?,?,?);");
							pstmt.setInt(1, Main.userID);
							pstmt.setInt(2, row);
							pstmt.setString(3, tf.getText());
							pstmt.executeUpdate();
							pstmt.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
	
						projectList.clear();
						for (int k = 1; k <= 10; k++) {
							if (!projectArray[k].name.getText().equals("")) {
								projectList.add(projectArray[k]);
							}
						}
						
						tf.setEditable(false);
						}
					});
					
				} 
				else {
					
					tf = projectArray[i].lcs[j];

					tf.setOnKeyReleased(a -> {
						if (a.getCode().equals(KeyCode.ENTER)) {
							if (tf.getStyle().equals("-fx-text-fill: red;")) {
								tf.setText(tf.getPromptText());
			            		tf.setStyle("-fx-text-fill: black;");
			            		tf.setOnMouseClicked(null);
			    				tf.setOnKeyPressed(null);
								return;
							}
			            	int column = GridPane.getColumnIndex(tf);
			            	int row = GridPane.getRowIndex(tf); 
			            	
			            	if (projectArray[row].name.getText().equals("")) {
			            		smallTextFieldError(tf, "Please enter a project name before specifying life cycle steps.");
								return;
			            	}
			            	
			            	if (lifeCycleStepList.isEmpty()) {
			            		smallTextFieldError(tf, "Please specify life cycle steps in list 2.");
	    						return;
			            	}
			            	
			            	if (tf.getText().isEmpty()) {
			            		if (tf.getPromptText().equals("")) {
			            			smallTextFieldError(tf, "Please enter the number of an existing life cycle step.");
		    						return;
			            		}	
			            		
			            		tf.setText("");
	            				tf.setPromptText("");
	            				
			            		try {
									PreparedStatement pstmt = Main.connection.prepareStatement("UPDATE project_table SET lcs" + column + " = ? WHERE userID = ? AND `index` = ?;");
									pstmt.setString(1, tf.getText());
									pstmt.setInt(2, Main.userID);
									pstmt.setInt(3, row);
									pstmt.executeUpdate();
									pstmt.close();
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
			            		
			            		projectArray[row].lcsList.clear();
			            		for (int k = 1; k <= 25; k++) {
			            			if (!projectArray[row].lcs[k].getText().equals("")) {
			            				projectArray[row].lcsList.add(lifeCycleStepArray[Integer.parseInt(projectArray[row].lcs[k].getText())]);
			            			}
			            		}
			            		
			            		return;
			            		
			            	}
			            	
			            	if (!isValidNum(tf.getText(), 1, 50, 2)) {
			            		smallTextFieldError(tf, "Please enter the number of an existing life cycle step.");
	    						return;
	            			}
							if (lifeCycleStepArray[Integer.parseInt(tf.getText())].name.getText().equals("")) {
								smallTextFieldError(tf, "Please enter the number of an existing life cycle step.");
	    						return;
							}
			
								for (int k = 1; k <= 25; k++) {
									if (String.valueOf(Integer.parseInt(tf.getText())).equals(projectArray[row].lcs[k].getPromptText())) {
										smallTextFieldError(tf, "That life cycle step already exists in this project.");
										return;
									}
								}
							
			            	
				            tf.setText(String.valueOf(Integer.parseInt(tf.getText())));
				            tf.setPromptText(tf.getText());
				            
		            		try {
								PreparedStatement pstmt = Main.connection.prepareStatement("UPDATE project_table SET lcs" + column + " = ? WHERE userID = ? AND `index` = ?;");
								pstmt.setString(1, tf.getText());
								pstmt.setInt(2, Main.userID);
								pstmt.setInt(3, row);
								pstmt.executeUpdate();
								pstmt.close();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
		            		
		            		projectArray[row].lcsList.clear();
		            		for (int k = 1; k <= 25; k++) {
		            			if (!projectArray[row].lcs[k].getText().equals("")) {
		            				projectArray[row].lcsList.add(lifeCycleStepArray[Integer.parseInt(projectArray[row].lcs[k].getText())]);
		            			}
		            		}
						}
					});
				
				}
				tf.focusedProperty().addListener((observableValue, oldValue, newValue) -> {
	            	if (!newValue) {
	            		tf.setText(tf.getPromptText());
	            		tf.setStyle("-fx-text-fill: black;");
	            		tf.setOnMouseClicked(null);
	            		tf.setOnKeyPressed(null);
	            	}
	            });
				
				tf.setMinHeight(28);
				dProjectGrid.add(tf, j, i);
			}
			
			Button b = new Button();
			b.setStyle("-fx-text-fill: red;");
			b.setText("Delete");
			b.setOpacity(0);

			b.setOnMouseEntered(e -> {
				Button b1 = (Button) e.getSource();
				int row = GridPane.getRowIndex(b1);
				if (!projectArray[row].name.getText().equals("")) {
					b1.setOpacity(1);
				}
			});
			
			b.setOnMouseExited(e -> {
				Button b1 = (Button) e.getSource();
				b1.setOpacity(0);
			});
			
			b.setOnAction(e -> {
				Button b1 = (Button) e.getSource();
				int row = GridPane.getRowIndex(b1);
				if (!projectArray[row].name.getText().equals("")) {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setHeaderText("Are you sure you want to delete this project and all of its associated logs?");
					
					if (alert.showAndWait().get() == ButtonType.OK) {
						String project = projectArray[row].name.getText();
						
						try {
							PreparedStatement pstmt;
							
							pstmt = Main.connection.prepareStatement("DELETE FROM project_table WHERE userID = ? AND name = ?;");
							pstmt.setInt(1, Main.userID);
							pstmt.setString(2, project);
							pstmt.executeUpdate();
							
							pstmt = Main.connection.prepareStatement("DELETE FROM effort_log_table WHERE userID = ? AND project = ?;");
							pstmt.setInt(1, Main.userID);
							pstmt.setString(2, project);
							pstmt.executeUpdate();
							
							pstmt = Main.connection.prepareStatement("DELETE FROM defect_log_table WHERE userID = ? AND project = ?;");
							pstmt.setInt(1, Main.userID);
							pstmt.setString(2, project);
							pstmt.executeUpdate();
							
							pstmt.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
						projectArray[row].name.setText("");
						projectArray[row].name.setPromptText("");
						for (int k = 1; k <= 25; k++) {
							projectArray[row].lcs[k].setText("");
							projectArray[row].lcs[k].setPromptText("");
						}
						projectArray[row].name.setEditable(true);
						
						projectList.clear();
						for (int k = 1; k <= 10; k++) {
							if (!projectArray[k].name.getText().equals("")) {
								projectList.add(projectArray[k]);
							}
						}
						
						projectArray[row].lcsList.clear();
						
						
					} 
						
				
				}
			});
			
			b.setMinHeight(28);
			dProjectDeletionGrid.add(b, 1, i);
			
		}
			
		for (int i = 1; i <= 50; i++) {
    		lifeCycleStepArray[i] = new LifeCycleStep();
    	}
		
		stmt = Main.connection.createStatement();
    	result = stmt.executeQuery("SELECT * FROM life_cycle_step_table WHERE userID = " + Main.userID + ";");
    	while(result.next()) {
    		int index = result.getInt("index");
    		lifeCycleStepArray[index].name.setText(result.getString("name"));
    		lifeCycleStepArray[index].name.setPromptText(result.getString("name"));
    		lifeCycleStepArray[index].name.setEditable(false);
    		lifeCycleStepArray[index].EC.setText(result.getString("EC"));
    		lifeCycleStepArray[index].EC.setPromptText(result.getString("EC"));
    		lifeCycleStepArray[index].D.setText(result.getString("D"));
    		lifeCycleStepArray[index].D.setPromptText(result.getString("D"));
    		if (lifeCycleStepArray[index].EC.getText().equals("4") || lifeCycleStepArray[index].EC.getText().equals("5")) {
        		lifeCycleStepArray[index].D.setEditable(false);
        	}
    	}
    	result.close();
    	stmt.close();
    	
    	for (int i = 1; i <= 50; i++) {
			if (!lifeCycleStepArray[i].name.getText().equals("")) {
				lifeCycleStepList.add(lifeCycleStepArray[i]);
			}
		}
    	
    	for (int i = 1; i <= 10; i++) {
    		for (int j = 1; j <= 25; j++) {
    			if (!projectArray[i].lcs[j].getText().equals("")) {
    				projectArray[i].lcsList.add(lifeCycleStepArray[Integer.parseInt(projectArray[i].lcs[j].getText())]);
    			}
    		}
    	}

    	for (int i = 1; i <= 50; i++) {
			for (int j = 0; j < 3; j++) {
				TextField tf;
				
				if (j == 0) {
					
					tf = lifeCycleStepArray[i].name;
					
					tf.setOnKeyReleased(a -> {
						if (tf.getStyle().equals("-fx-text-fill: red;")) {
							tf.setText(tf.getPromptText());
		            		tf.setStyle("-fx-text-fill: black;");
							return;
						}
						if (a.getCode().equals(KeyCode.ENTER)) {
						int row = GridPane.getRowIndex(tf); 
		            	
	            		if (tf.getText().isEmpty() || tf.getText().length() > 100) {
							largeTextFieldError(tf, "Please enter a life cycle step name that is between 1 and 100 characters.");
							return;
						}
	            		
	            		if (!lifeCycleStepList.isEmpty()) {
	            			for (int k = 0; k < lifeCycleStepList.size(); k++) {
	            				if (tf.getText().equals(lifeCycleStepList.get(k).name.getText())) {
									largeTextFieldError(tf, "That life cycle step name already exists.");
									return;
								}
	            			}
	            		}
						
						tf.setText(tf.getText());
						tf.setPromptText(tf.getText());
						try {
							PreparedStatement pstmt = Main.connection.prepareStatement("INSERT INTO life_cycle_step_table (userID,`index`,name) VALUES (?,?,?);");
							pstmt.setInt(1, Main.userID);
							pstmt.setInt(2, row);
							pstmt.setString(3, tf.getText());
							pstmt.executeUpdate();
							pstmt.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
						
						lifeCycleStepList.clear();
						for (int k = 1; k <= 50; k++) {
							if (!lifeCycleStepArray[k].name.getText().equals("")) {
								lifeCycleStepList.add(lifeCycleStepArray[k]);
							}
						}
						
						tf.setEditable(false);
						}
					});
				}
				else if (j == 1) {
					
					tf = lifeCycleStepArray[i].EC;
					
					tf.setOnKeyReleased(a -> {
						if (a.getCode().equals(KeyCode.ENTER)) {
							if (tf.getStyle().equals("-fx-text-fill: red;")) {
								tf.setText(tf.getPromptText());
			            		tf.setStyle("-fx-text-fill: black;");
			            		tf.setOnMouseClicked(null);
			    				tf.setOnKeyPressed(null);
								return;
							}
		            	int row = GridPane.getRowIndex(tf); 
		            	
		            	if (lifeCycleStepArray[row].name.getText().equals("")) {
		            		smallTextFieldError(tf, "Please enter a life cycle step name.");
							return;
		            	}
		            	
		            	if (tf.getText().isEmpty()) {
		            		if (tf.getPromptText().equals("")) {
		            			smallTextFieldError(tf, "Please enter the number of an existing effort category.");
	    						return;
		            		}	
		            		
		            		tf.setText("");
            				tf.setPromptText("");
            				
            				lifeCycleStepArray[row].D.setText("");
            				lifeCycleStepArray[row].D.setPromptText("");
            				
		            		try {
		            			PreparedStatement pstmt;
		            			
								pstmt = Main.connection.prepareStatement("UPDATE life_cycle_step_table SET EC = ? WHERE userID = ? AND `index` = ?;");
								pstmt.setString(1, "");
								pstmt.setInt(2, Main.userID);
								pstmt.setInt(3, row);
								pstmt.executeUpdate();
								
								pstmt = Main.connection.prepareStatement("UPDATE life_cycle_step_table SET D = ? WHERE userID = ? AND `index` = ?;");
								pstmt.setString(1, "");
								pstmt.setInt(2, Main.userID);
								pstmt.setInt(3, row);
								pstmt.executeUpdate();
								
								pstmt.close();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
		            		return;
		            	}
		            	
		            	if (!isValidNum(tf.getText(), 1, 5, 2)) {
		            		smallTextFieldError(tf, "Please enter the number of an existing effort category.");
    						return;
            			}
		            	
		            	tf.setText(String.valueOf(Integer.parseInt(tf.getText())));
		            	tf.setPromptText(tf.getText());
		            	
		            	lifeCycleStepArray[row].D.setText("");
        				lifeCycleStepArray[row].D.setPromptText("");
		            	
		            	try {
	            			PreparedStatement pstmt;
	            			
							pstmt = Main.connection.prepareStatement("UPDATE life_cycle_step_table SET EC = ? WHERE userID = ? AND `index` = ?;");
							pstmt.setString(1, tf.getText());
							pstmt.setInt(2, Main.userID);
							pstmt.setInt(3, row);
							pstmt.executeUpdate();
							
							pstmt = Main.connection.prepareStatement("UPDATE life_cycle_step_table SET D = ? WHERE userID = ? AND `index` = ?;");
							pstmt.setString(1, "");
							pstmt.setInt(2, Main.userID);
							pstmt.setInt(3, row);
							pstmt.executeUpdate();
							
							pstmt.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
		            	
		            	if (tf.getText().equals("4") || tf.getText().equals("5")) {
		            		lifeCycleStepArray[row].D.setEditable(false);
		            	}
		            	else {
		            		lifeCycleStepArray[row].D.setEditable(true);
		            	}
						}
					});
				}
				else {
					
					tf = lifeCycleStepArray[i].D;
					
					tf.setOnKeyReleased(a -> {
						if (a.getCode().equals(KeyCode.ENTER)) {
							if (tf.getStyle().equals("-fx-text-fill: red;")) {
								tf.setText(tf.getPromptText());
			            		tf.setStyle("-fx-text-fill: black;");
			            		tf.setOnMouseClicked(null);
			    				tf.setOnKeyPressed(null);
								return;
							}
						
			            	int row = GridPane.getRowIndex(tf); 
							
							if (lifeCycleStepArray[row].name.getText().equals("")) {
								smallTextFieldError(tf, "Please enter a life cycle step name.");
								return;
			            	}
							
							if (lifeCycleStepArray[row].EC.getText().equals("")) {
								smallTextFieldError(tf, "Please specify an effort category.");
								return;
			            	}
							
							if (tf.getText().isEmpty()) {
			            		if (tf.getPromptText().equals("")) {
			            			if (lifeCycleStepArray[row].name.getText().equals("1")) {
			            				smallTextFieldError(tf, "Please enter the number of an existing plan.");
			    						return;
			            			}
			            			if (lifeCycleStepArray[row].name.getText().equals("2")) {
			            				smallTextFieldError(tf, "Please enter the number of an existing deliverable.");
			    						return;
			            			}
			            			if (lifeCycleStepArray[row].name.getText().equals("3")) {
			            				smallTextFieldError(tf, "Please enter the number of an existing interruption.");
			    						return;
			            			}
			            		}	
			            		
			            		tf.setText("");
	            				tf.setPromptText("");
	            				
			            		try {
			            			PreparedStatement pstmt;
									
									pstmt = Main.connection.prepareStatement("UPDATE life_cycle_step_table SET D = ? WHERE userID = ? AND `index` = ?;");
									pstmt.setString(1, "");
									pstmt.setInt(2, Main.userID);
									pstmt.setInt(3, row);
									pstmt.executeUpdate();
									
									pstmt.close();
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
			            		return;
			            	}
			            	
							if (lifeCycleStepArray[row].EC.getText().equals("1")) {
								if (!isValidNum(tf.getText(), 1, 10, 2) || planArray[Integer.parseInt(tf.getText())].getText().equals("")) {
									smallTextFieldError(tf, "Please enter the number of an existing plan.");
									return;
								}
	            			}
							if (lifeCycleStepArray[row].EC.getText().equals("2")) {
								if (!isValidNum(tf.getText(), 1, 10, 2) || deliverableArray[Integer.parseInt(tf.getText())].getText().equals("")) {
									smallTextFieldError(tf, "Please enter the number of an existing deliverable.");
									return;
								}
	            			}
							if (lifeCycleStepArray[row].EC.getText().equals("3")) {
								if (!isValidNum(tf.getText(), 1, 10, 2) || interruptionArray[Integer.parseInt(tf.getText())].getText().equals("")) {
									smallTextFieldError(tf, "Please enter the number of an existing interruption.");
									return;
								}
	            			}
	            			
			            	tf.setText(String.valueOf(Integer.parseInt(tf.getText())));
			            	tf.setPromptText(tf.getText());
			            	
			            	try {
		            			PreparedStatement pstmt;
								
								pstmt = Main.connection.prepareStatement("UPDATE life_cycle_step_table SET D = ? WHERE userID = ? AND `index` = ?;");
								pstmt.setString(1, tf.getText());
								pstmt.setInt(2, Main.userID);
								pstmt.setInt(3, row);
								pstmt.executeUpdate();
								
								pstmt.close();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
					});
				}
				tf.focusedProperty().addListener((observableValue, oldValue, newValue) -> {
	            	if (!newValue) {
	            		tf.setText(tf.getPromptText());
	            		tf.setStyle("-fx-text-fill: black;");
	            		tf.setOnMouseClicked(null);
	            		tf.setOnKeyPressed(null);
	            	}
	            });
				tf.setMinHeight(28);
				dLifeCycleStepGrid.add(tf, j, i);
			}
			
			Button b = new Button();
			b.setMinHeight(28);
			b.setStyle("-fx-text-fill: red;");
			b.setText("Delete");
			b.setOpacity(0);

			b.setOnMouseEntered(e -> {
				Button b1 = (Button) e.getSource();
				int row = GridPane.getRowIndex(b1);
				if (!lifeCycleStepArray[row].name.getText().equals("")) {
					b1.setOpacity(1);
				}
			});
			
			b.setOnMouseExited(e -> {
				Button b1 = (Button) e.getSource();
				b1.setOpacity(0);
			});
			
			b.setOnAction(e -> {
				Button b1 = (Button) e.getSource();
				int row = GridPane.getRowIndex(b1);
				if (!lifeCycleStepArray[row].name.getText().equals("")) {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setHeaderText("Are you sure you want to delete this life cycle step?");
					
					if (alert.showAndWait().get() == ButtonType.OK) {
						String lifeCycleStep = lifeCycleStepArray[row].name.getText();
						
						try {
							PreparedStatement pstmt;
							
							pstmt = Main.connection.prepareStatement("DELETE FROM life_cycle_step_table WHERE userID = ? AND name = ?;");
							pstmt.setInt(1, Main.userID);
							pstmt.setString(2, lifeCycleStep);
							pstmt.executeUpdate();
							
							pstmt = Main.connection.prepareStatement("DELETE FROM life_cycle_step_table WHERE userID = ? AND name = ?;");
							pstmt.setInt(1, Main.userID);
							pstmt.setString(2, lifeCycleStep);
							pstmt.executeUpdate();
							
							pstmt.close();
							
							for (int j = 1; j <= 10; j++) {
								for (int k = 1; k <= 25; k++) {
									if (projectArray[j].lcs[k].getText().equals(String.valueOf(row))) {
										projectArray[j].lcs[k].setText("");
										projectArray[j].lcs[k].setPromptText("");
										
										pstmt = Main.connection.prepareStatement("UPDATE project_table SET lcs" + k + " = ? WHERE userID = ? AND `index` = ?;");
										pstmt.setString(1, "");
										pstmt.setInt(2, Main.userID);
										pstmt.setInt(3, j);
										pstmt.executeUpdate();
										pstmt.close();
									}
								}
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
						lifeCycleStepArray[row].name.setText("");
						lifeCycleStepArray[row].name.setPromptText("");
						lifeCycleStepArray[row].name.setEditable(true);
						lifeCycleStepArray[row].EC.setText("");
						lifeCycleStepArray[row].EC.setPromptText("");
						lifeCycleStepArray[row].D.setText("");
						lifeCycleStepArray[row].D.setPromptText("");
						
						lifeCycleStepList.clear();
						for (int k = 1; k <= 50; k++) {
							if (!lifeCycleStepArray[k].name.getText().equals("")) {
								lifeCycleStepList.add(lifeCycleStepArray[k]);
							}
						}
						
						projectArray[row].lcsList.clear();
	            		for (int k = 1; k <= 25; k++) {
	            			if (!projectArray[row].lcs[k].getText().equals("")) {
	            				projectArray[row].lcsList.add(lifeCycleStepArray[Integer.parseInt(projectArray[row].lcs[k].getText())]);
	            			}
	            		}
					} 
				}
			});
			
			dLifeCycleStepDeletionGrid.add(b, 1, i);
    	}
    	
    	effortCategoryList.add("Plans");
    	effortCategoryList.add("Deliverables");
    	effortCategoryList.add("Interruptions");
    	effortCategoryList.add("Defects");
    	effortCategoryList.add("Other");
    	
    	for (int i = 1; i <= 5; i++) {
			TextField tf = new TextField(effortCategoryList.get(i - 1));
			tf.setEditable(false);
			tf.setMinHeight(28);
            dEffortCategoryGrid.add(tf, 0, i);
		}
    	
    	stmt = Main.connection.createStatement();
    	result = stmt.executeQuery("SELECT * FROM plan_table WHERE userID = " + Main.userID + ";");
    	for (int i = 1; i <= 9; i++) {
    		planArray[i] = new TextField("");
    		planArray[i].setPromptText("");
    	}
    	planArray[10] = new TextField("Other");
    	planArray[10].setPromptText("Other");
    	planArray[10].setEditable(false);
    	
    	// Initialize observable lists
    	while(result.next()) {
    		int index = result.getInt("index");
    		planArray[index].setText(result.getString("name"));
    		planArray[index].setPromptText(result.getString("name"));
    		planArray[index].setEditable(false);
    	}
    	result.close();
    	stmt.close();
    	
    	for (int k = 1; k <= 10; k++) {
			if (!planArray[k].getText().equals("")) {
				planList.add(planArray[k].getText());
			}
		}
    	
    	for (int i = 1; i <= 10; i++) {
			TextField tf;
			
			tf = planArray[i];
			
			
			tf.setOnKeyReleased(a -> {
				if (tf.getStyle().equals("-fx-text-fill: red;")) {
					tf.setText(tf.getPromptText());
            		tf.setStyle("-fx-text-fill: black;");
					return;
				}
				if (a.getCode().equals(KeyCode.ENTER)) {
					int row = GridPane.getRowIndex(tf); 
	            	
            		if (tf.getText().isEmpty() || tf.getText().length() > 100) {
						largeTextFieldError(tf, "Please enter a plan name that is between 1 and 100 characters.");
						return;
					}
       
        			for (int k = 0; k < planList.size(); k++) {
        				if (tf.getText().equals(planList.get(k))) {
							largeTextFieldError(tf, "That plan name already exists.");
							return;
						}
        			}
     
            		tf.setText(tf.getText());
					tf.setPromptText(tf.getText());

					try {
						PreparedStatement pstmt = Main.connection.prepareStatement("INSERT INTO plan_table (userID,`index`,name) VALUES (?,?,?);");
						pstmt.setInt(1, Main.userID);
						pstmt.setInt(2, row);
						pstmt.setString(3, tf.getText());
						pstmt.executeUpdate();
						pstmt.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					planList.clear();
					for (int k = 1; k <= 10; k++) {
						if (!planArray[k].getText().equals("") && row != 10) {
							planList.add(planArray[k].getText());
						}
					}
					
					tf.setEditable(false);	
				}
            });
            
            tf.focusedProperty().addListener((observableValue, oldValue, newValue) -> {
            	if (!newValue) {
            		tf.setText(tf.getPromptText());
            		tf.setStyle("-fx-text-fill: black;");
            		tf.setOnMouseClicked(null);
            		tf.setOnKeyPressed(null);
            	}
            });
            
            tf.setMinHeight(28);
            dPlanGrid.add(tf, 0, i);
            
            Button b = new Button();
			b.setMinHeight(28);
			b.setStyle("-fx-text-fill: red;");
			b.setText("Delete");
			b.setOpacity(0);

			b.setOnMouseEntered(e -> {
				Button b1 = (Button) e.getSource();
				int row = GridPane.getRowIndex(b1);
				if (!planArray[row].getText().equals("") && row != 10) {
					b1.setOpacity(1);
				}
			});
			
			b.setOnMouseExited(e -> {
				Button b1 = (Button) e.getSource();
				b1.setOpacity(0);
			});
			
			b.setOnAction(e -> {
				Button b1 = (Button) e.getSource();
				int row = GridPane.getRowIndex(b1);
				if (!planArray[row].getText().equals("")) {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setHeaderText("Are you sure you want to delete this plan?");
					
					if (alert.showAndWait().get() == ButtonType.OK) {
						String plan = planArray[row].getText();
						
						try {
							PreparedStatement pstmt;
							
							pstmt = Main.connection.prepareStatement("DELETE FROM plan_table WHERE userID = ? AND name = ?;");
							pstmt.setInt(1, Main.userID);
							pstmt.setString(2, plan);
							pstmt.executeUpdate();
							
							pstmt.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
						planArray[row].setText("");
						planArray[row].setPromptText("");
						planArray[row].setEditable(true);
						
						planList.clear();
						for (int k = 1; k <= 10; k++) {
							if (!planArray[k].getText().equals("")) {
								planList.add(planArray[k].getText());
							}
						}
					} 
				}
			});
			
			dPlanDeletionGrid.add(b, 1, i);
		}
    	
    	for (int i = 1; i <= 9; i++) {
    		deliverableArray[i] = new TextField("");
    		deliverableArray[i].setPromptText("");
    	}
    	deliverableArray[10] = new TextField("Other");
    	deliverableArray[10].setPromptText("Other");
    	deliverableArray[10].setEditable(false);
    	
    	stmt = Main.connection.createStatement();
    	result = stmt.executeQuery("SELECT * FROM deliverable_table WHERE userID = " + Main.userID + ";");
    	
    	while(result.next()) {
    		int index = result.getInt("index");
    		deliverableArray[index].setText(result.getString("name"));
    		deliverableArray[index].setPromptText(result.getString("name"));	
    		deliverableArray[index].setEditable(false);
    	}
    	result.close();
    	stmt.close();
    	
    	for (int k = 1; k <= 10; k++) {
			if (!deliverableArray[k].getText().equals("")) {
				deliverableList.add(deliverableArray[k].getText());
			}
		}
    	
    	for (int i = 1; i <= 10; i++) {
			TextField tf;
			
			tf = deliverableArray[i];
			
			tf.setOnKeyReleased(a -> {
				if (tf.getStyle().equals("-fx-text-fill: red;")) {
					tf.setText(tf.getPromptText());
            		tf.setStyle("-fx-text-fill: black;");
					return;
				}
				if (a.getCode().equals(KeyCode.ENTER)) {
					int row = GridPane.getRowIndex(tf); 
	            	
            		if (tf.getText().isEmpty() || tf.getText().length() > 100) {
						largeTextFieldError(tf, "Please enter a deliverable name that is between 1 and 100 characters.");
						return;
					}

        			for (int k = 0; k < deliverableList.size(); k++) {
        				if (tf.getText().equals(deliverableList.get(k))) {
							largeTextFieldError(tf, "That deliverable name already exists.");
							return;
						}
        			}
 
            		tf.setText(tf.getText());
					tf.setPromptText(tf.getText());

					try {
						PreparedStatement pstmt = Main.connection.prepareStatement("INSERT INTO deliverable_table (userID,`index`,name) VALUES (?,?,?);");
						pstmt.setInt(1, Main.userID);
						pstmt.setInt(2, row);
						pstmt.setString(3, tf.getText());
						pstmt.executeUpdate();
						pstmt.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					deliverableList.clear();
					for (int k = 1; k <= 10; k++) {
						if (!deliverableArray[k].getText().equals("")) {
							deliverableList.add(deliverableArray[k].getText());
						}
					}
					
					tf.setEditable(false);	
				}
            });
            
            tf.focusedProperty().addListener((observableValue, oldValue, newValue) -> {
            	if (!newValue) {
            		tf.setText(tf.getPromptText());
            		tf.setStyle("-fx-text-fill: black;");
            		tf.setOnMouseClicked(null);
            		tf.setOnKeyPressed(null);
            	}
            });
            
            
            tf.setMinHeight(28);
            dDeliverableGrid.add(tf, 0, i);
            
            Button b = new Button();
			b.setMinHeight(28);
			b.setStyle("-fx-text-fill: red;");
			b.setText("Delete");
			b.setOpacity(0);

			b.setOnMouseEntered(e -> {
				Button b1 = (Button) e.getSource();
				int row = GridPane.getRowIndex(b1);
				if (!deliverableArray[row].getText().equals("") && row != 10) {
					b1.setOpacity(1);
				}
			});
			
			b.setOnMouseExited(e -> {
				Button b1 = (Button) e.getSource();
				b1.setOpacity(0);
			});
			
			b.setOnAction(e -> {
				Button b1 = (Button) e.getSource();
				int row = GridPane.getRowIndex(b1);
				if (!deliverableArray[row].getText().equals("") && row != 10) {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setHeaderText("Are you sure you want to delete this deliverable?");
					
					if (alert.showAndWait().get() == ButtonType.OK) {
						String deliverable = deliverableArray[row].getText();
						
						try {
							PreparedStatement pstmt;
							
							pstmt = Main.connection.prepareStatement("DELETE FROM deliverable_table WHERE userID = ? AND name = ?;");
							pstmt.setInt(1, Main.userID);
							pstmt.setString(2, deliverable);
							pstmt.executeUpdate();
							
							pstmt.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
						deliverableArray[row].setText("");
						deliverableArray[row].setPromptText("");
						deliverableArray[row].setEditable(true);
						
						deliverableList.clear();
						for (int k = 1; k <= 10; k++) {
							if (!deliverableArray[k].getText().equals("")) {
								deliverableList.add(deliverableArray[k].getText());
							}
						}
					} 
				}
			});
			
			dDeliverableDeletionGrid.add(b, 1, i);
		}
    	
    	stmt = Main.connection.createStatement();
    	result = stmt.executeQuery("SELECT * FROM interruption_table WHERE userID = " + Main.userID + ";");
    	for (int i = 1; i <= 9; i++) {
    		interruptionArray[i] = new TextField("");
    		interruptionArray[i].setPromptText("");
    	}
    	interruptionArray[10] = new TextField("Other");
    	interruptionArray[10].setPromptText("Other");
    	interruptionArray[10].setEditable(false);
    	
    	// Initialize observable lists
    	while(result.next()) {
    		int index = result.getInt("index");
    		interruptionArray[index].setText(result.getString("name"));
    		interruptionArray[index].setPromptText(result.getString("name"));	
    		interruptionArray[index].setEditable(false);
    	}
    	result.close();
    	stmt.close();
    	
    	for (int k = 1; k <= 10; k++) {
			if (!interruptionArray[k].getText().equals("")) {
				interruptionList.add(interruptionArray[k].getText());
			}
		}
    	
    	for (int i = 1; i <= 10; i++) {
			TextField tf;
			
			tf = interruptionArray[i];
			
			
			tf.setOnKeyReleased(a -> {
				if (tf.getStyle().equals("-fx-text-fill: red;")) {
					tf.setText(tf.getPromptText());
            		tf.setStyle("-fx-text-fill: black;");
					return;
				}
				if (a.getCode().equals(KeyCode.ENTER)) {
					int row = GridPane.getRowIndex(tf); 
	            	
            		if (tf.getText().isEmpty() || tf.getText().length() > 100) {
						largeTextFieldError(tf, "Please enter an interruption name that is between 1 and 100 characters.");
						return;
					}

        			for (int k = 0; k < interruptionList.size(); k++) {
        				if (tf.getText().equals(interruptionList.get(k))) {
							largeTextFieldError(tf, "That interruption name already exists.");
							return;
						}
        			}
 
            		tf.setText(tf.getText());
					tf.setPromptText(tf.getText());

					try {
						PreparedStatement pstmt = Main.connection.prepareStatement("INSERT INTO interruption_table (userID,`index`,name) VALUES (?,?,?);");
						pstmt.setInt(1, Main.userID);
						pstmt.setInt(2, row);
						pstmt.setString(3, tf.getText());
						pstmt.executeUpdate();
						pstmt.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					interruptionList.clear();
					for (int k = 1; k <= 10; k++) {
						if (!interruptionArray[k].getText().equals("")) {
							interruptionList.add(interruptionArray[k].getText());
						}
					}
					
					tf.setEditable(false);	
				}
            });
            
            tf.focusedProperty().addListener((observableValue, oldValue, newValue) -> {
            	if (!newValue) {
            		tf.setText(tf.getPromptText());
            		tf.setStyle("-fx-text-fill: black;");
            		tf.setOnMouseClicked(null);
            		tf.setOnKeyPressed(null);
            	}
            });
            
            
            tf.setMinHeight(28);
            dInterruptionGrid.add(tf, 0, i);
            
            Button b = new Button();
			b.setMinHeight(28);
			b.setStyle("-fx-text-fill: red;");
			b.setText("Delete");
			b.setOpacity(0);

			b.setOnMouseEntered(e -> {
				Button b1 = (Button) e.getSource();
				int row = GridPane.getRowIndex(b1);
				if (!interruptionArray[row].getText().equals("") && row != 10) {
					b1.setOpacity(1);
				}
			});
			
			b.setOnMouseExited(e -> {
				Button b1 = (Button) e.getSource();
				b1.setOpacity(0);
			});
			
			b.setOnAction(e -> {
				Button b1 = (Button) e.getSource();
				int row = GridPane.getRowIndex(b1);
				if (!interruptionArray[row].getText().equals("") && row != 10) {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setHeaderText("Are you sure you want to delete this interruption?");
					
					if (alert.showAndWait().get() == ButtonType.OK) {
						String interruption = interruptionArray[row].getText();
						
						try {
							PreparedStatement pstmt;
							
							pstmt = Main.connection.prepareStatement("DELETE FROM interruption_table WHERE userID = ? AND name = ?;");
							pstmt.setInt(1, Main.userID);
							pstmt.setString(2, interruption);
							pstmt.executeUpdate();
							
							pstmt.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
						interruptionArray[row].setText("");
						interruptionArray[row].setPromptText("");
						interruptionArray[row].setEditable(true);
						
						interruptionList.clear();
						for (int k = 1; k <= 10; k++) {
							if (!interruptionArray[k].getText().equals("")) {
								interruptionList.add(interruptionArray[k].getText());
							}
						}	
					} 
				}
			});
			
			dInterruptionDeletionGrid.add(b, 1, i);
		}
    	
    	stmt = Main.connection.createStatement();
    	result = stmt.executeQuery("SELECT * FROM defect_category_table WHERE userID = " + Main.userID + ";");
    	for (int i = 1; i <= 15; i++) {
    		defectCategoryArray[i] = new TextField("");
    		defectCategoryArray[i].setPromptText("");
    	}
    	
    	// Initialize observable lists
    	while(result.next()) {
    		int index = result.getInt("index");
    		defectCategoryArray[index].setText(result.getString("name"));
    		defectCategoryArray[index].setPromptText(result.getString("name"));	
    		defectCategoryArray[index].setEditable(false);
    	}
    	result.close();
    	stmt.close();
    	
    	for (int k = 1; k <= 15; k++) {
			if (!defectCategoryArray[k].getText().equals("")) {
				defectCategoryList.add(defectCategoryArray[k].getText());
			}
		}
    	
    	for (int i = 1; i <= 15; i++) {
			TextField tf;
			
			tf = defectCategoryArray[i];
			
			
			tf.setOnKeyReleased(a -> {
				if (tf.getStyle().equals("-fx-text-fill: red;")) {
					tf.setText(tf.getPromptText());
            		tf.setStyle("-fx-text-fill: black;");
					return;
				}
				if (a.getCode().equals(KeyCode.ENTER)) {
					int row = GridPane.getRowIndex(tf); 
	            	
            		if (tf.getText().isEmpty() || tf.getText().length() > 100) {
						largeTextFieldError(tf, "Please enter an defect category name that is between 1 and 100 characters.");
						return;
					}
            		if (!defectCategoryList.isEmpty()) {
	        			for (int k = 0; k < defectCategoryList.size(); k++) {
	        				if (tf.getText().equals(defectCategoryList.get(k))) {
								largeTextFieldError(tf, "That defect category name already exists.");
								return;
							}
	        			}
            		}
 
            		tf.setText(tf.getText());
					tf.setPromptText(tf.getText());

					try {
						PreparedStatement pstmt = Main.connection.prepareStatement("INSERT INTO defect_category_table (userID,`index`,name) VALUES (?,?,?);");
						pstmt.setInt(1, Main.userID);
						pstmt.setInt(2, row);
						pstmt.setString(3, tf.getText());
						pstmt.executeUpdate();
						pstmt.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					defectCategoryList.clear();
					for (int k = 1; k <= 15; k++) {
						if (!defectCategoryArray[k].getText().equals("")) {
							defectCategoryList.add(defectCategoryArray[k].getText());
						}
					}
					
					tf.setEditable(false);	
				}
            });
            
            tf.focusedProperty().addListener((observableValue, oldValue, newValue) -> {
            	if (!newValue) {
            		tf.setText(tf.getPromptText());
            		tf.setStyle("-fx-text-fill: black;");
            		tf.setOnMouseClicked(null);
            		tf.setOnKeyPressed(null);
            	}
            });
            
            tf.setMinHeight(28);
            dDefectCategoryGrid.add(tf, 0, i);
            
            Button b = new Button();
			b.setMinHeight(28);
			b.setStyle("-fx-text-fill: red;");
			b.setText("Delete");
			b.setOpacity(0);

			b.setOnMouseEntered(e -> {
				Button b1 = (Button) e.getSource();
				int row = GridPane.getRowIndex(b1);
				if (!defectCategoryArray[row].getText().equals("")) {
					b1.setOpacity(1);
				}
			});
			
			b.setOnMouseExited(e -> {
				Button b1 = (Button) e.getSource();
				b1.setOpacity(0);
			});
			
			b.setOnAction(e -> {
				Button b1 = (Button) e.getSource();
				int row = GridPane.getRowIndex(b1);
				if (!defectCategoryArray[row].getText().equals("")) {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setHeaderText("Are you sure you want to delete this defect category?");
					
					if (alert.showAndWait().get() == ButtonType.OK) {
						String defectCategory = defectCategoryArray[row].getText();
						
						try {
							PreparedStatement pstmt;
							
							pstmt = Main.connection.prepareStatement("DELETE FROM defect_category_table WHERE userID = ? AND name = ?;");
							pstmt.setInt(1, Main.userID);
							pstmt.setString(2, defectCategory);
							pstmt.executeUpdate();
							
							pstmt.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
						defectCategoryArray[row].setText("");
						defectCategoryArray[row].setPromptText("");
						defectCategoryArray[row].setEditable(true);
						
						defectCategoryList.clear();
						for (int k = 1; k <= 15; k++) {
							if (!defectCategoryArray[k].getText().equals("")) {
								defectCategoryList.add(defectCategoryArray[k].getText());
							}
						}
					} 
				}
			});
			
			dDefectCategoryDeletionGrid.add(b, 1, i);
		}	
    }
    
} 
