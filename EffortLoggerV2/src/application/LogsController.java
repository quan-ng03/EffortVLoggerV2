package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.control.cell.PropertyValueFactory;

public class LogsController implements Initializable {
	@FXML
    private TableView<EffortLog> logsEffortTable;
	@FXML
	private TableColumn<?, ?> logsEffortEntries;
	@FXML
	private ComboBox<String> logsProjectCombo1;
    @FXML
    private TableColumn<EffortLog, Integer> logsIndex1;
    @FXML
    private TableColumn<EffortLog, String> logsDate;
    @FXML
    private TableColumn<EffortLog, String> logsStart;
    @FXML
    private TableColumn<EffortLog, String> logsStop;
    @FXML
    private TableColumn<EffortLog, String> logsDelta;
    @FXML
    private TableColumn<EffortLog, String> logsLifeCycleStep;
    @FXML
    private TableColumn<EffortLog, String> logsEffortCategory;
    @FXML
    private TableColumn<EffortLog, String> logsDeliverable;
    
    @FXML
    private TableView<DefectLog> logsDefectTable;
    @FXML
    private TableColumn<?, ?> logsDefectEntries;
    @FXML
    private ComboBox<String> logsProjectCombo2;
    @FXML
    private TableColumn<DefectLog, Integer> logsIndex2;
    @FXML
    private TableColumn<DefectLog, String> logsName;
    @FXML
    private TableColumn<DefectLog, String> logsDetail;
    @FXML
    private TableColumn<DefectLog, Integer> logsInjected;
    @FXML
    private TableColumn<DefectLog, Integer> logsRemoved;
    @FXML
    private TableColumn<DefectLog, Integer> logsCategory;
    @FXML
    private TableColumn<DefectLog, Integer> logsStatus;
    @FXML
    private TableColumn<DefectLog, Integer> logsFix;


//    User user = new User();
//    EffortLog eLog = new EffortLog();
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
/*		logsProjectCombo1.setCellFactory(ComboBoxListCell.forListView(Main.getUserList().get(Main.getId()).getDefinitions().getProjects()));
		logsProjectCombo1.setItems(Main.getUserList().get(Main.getId()).getDefinitions().getProjects());
		logsProjectCombo1.valueProperty().addListener((options, oldValue, newValue) -> {
				logsProjectCombo2.setValue(newValue);
				Boolean isEmpty = true;
				//for (EffortLog elog : Main.getUserList().get(Main.getId()).getEffortLogList()) {
					if (elog.getProject() == newValue.toString()) {
						logsIndex1.setCellValueFactory(new PropertyValueFactory<EffortLog, Integer>("index"));
						logsDate.setCellValueFactory(new PropertyValueFactory<EffortLog, String>("date"));
						logsStart.setCellValueFactory(new PropertyValueFactory<EffortLog, String>("startTime"));
						logsStop.setCellValueFactory(new PropertyValueFactory<EffortLog, String>("stopTime"));
						logsDelta.setCellValueFactory(new PropertyValueFactory<EffortLog, String>("deltaTime"));
						logsLifeCycleStep.setCellValueFactory(new PropertyValueFactory<EffortLog, String>("lifeCycleStep"));
						logsEffortCategory.setCellValueFactory(new PropertyValueFactory<EffortLog, String>("effortCategory"));
						logsDeliverable.setCellValueFactory(new PropertyValueFactory<EffortLog, String>("plan"));
						logsEffortTable.getItems().add(elog);
						isEmpty = false;
					}
				}
				if (isEmpty) {
					logsEffortTable.getItems().clear();
				}
		});
		
	/*	for (EffortLog elog : Main.getUserList().get(Main.getId()).getEffortLogList()) {
			if (elog.getProject() == newValue) {
				logsIndex1.setCellValueFactory(new PropertyValueFactory<EffortLog, Integer>("index"));
				logsDate.setCellValueFactory(new PropertyValueFactory<EffortLog, String>("date"));
				logsStart.setCellValueFactory(new PropertyValueFactory<EffortLog, String>("startTime"));
				logsStop.setCellValueFactory(new PropertyValueFactory<EffortLog, String>("stopTime"));
				logsDelta.setCellValueFactory(new PropertyValueFactory<EffortLog, String>("deltaTime"));
				logsLifeCycleStep.setCellValueFactory(new PropertyValueFactory<EffortLog, String>("lifeCycleStep"));
				logsEffortCategory.setCellValueFactory(new PropertyValueFactory<EffortLog, String>("effortCategory"));
				logsDeliverable.setCellValueFactory(new PropertyValueFactory<EffortLog, String>("plan"));
			//	logsEffortTable.getItems().add(elog);
			}
		
	
	
		}*/
		//logsEffortTable.setItems(Main.getUserList().get(Main.getId()).getEffortLogList());
		
	
		
		/*logsEffortEntries.setText("worked");
		

		logsProjectCombo2.setCellFactory(ComboBoxListCell.forListView(Main.getUserList().get(Main.getId()).getDefinitions().getProjects()));
		logsProjectCombo2.setItems(Main.getUserList().get(Main.getId()).getDefinitions().getProjects());
		logsProjectCombo2.valueProperty().addListener((options, oldValue, newValue) -> {
				logsProjectCombo1.setValue(newValue);
				Boolean isEmpty = true;
				for (DefectLog dlog : Main.getUserList().get(Main.getId()).getDefectLogList()) {
					if (dlog.getProject() == newValue.toString()) {
						logsIndex2.setCellValueFactory(new PropertyValueFactory<DefectLog, Integer>("index"));
						logsProjectCombo2.setCellFactory(ComboBoxListCell.forListView(Main.getUserList().get(Main.getId()).getDefinitions().getProjects()));
						logsName.setCellValueFactory(new PropertyValueFactory<DefectLog, String>("name"));
						logsDetail.setCellValueFactory(new PropertyValueFactory<DefectLog, String>("detail"));
						logsInjected.setCellValueFactory(new PropertyValueFactory<DefectLog, Integer>("injected"));
						logsRemoved.setCellValueFactory(new PropertyValueFactory<DefectLog, Integer>("removed"));
						logsCategory.setCellValueFactory(new PropertyValueFactory<DefectLog, Integer>("category"));
						logsStatus.setCellValueFactory(new PropertyValueFactory<DefectLog, Integer>("status"));
						logsFix.setCellValueFactory(new PropertyValueFactory<DefectLog, Integer>("fix"));
						logsDefectTable.getItems().add(dlog);
						isEmpty = false;
					}
				}
				if (isEmpty) {
				logsDefectTable.getItems().clear();
				}
		});*/
		
	/*	logsIndex2.setCellValueFactory(new PropertyValueFactory<DefectLog, Integer>("index"));
		logsProjectCombo2.setCellFactory(ComboBoxListCell.forListView(Main.getUserList().get(Main.getId()).getDefinitions().getProjects()));
		logsName.setCellValueFactory(new PropertyValueFactory<DefectLog, String>("name"));
		logsDetail.setCellValueFactory(new PropertyValueFactory<DefectLog, String>("detail"));
		logsInjected.setCellValueFactory(new PropertyValueFactory<DefectLog, Integer>("injected"));
		logsRemoved.setCellValueFactory(new PropertyValueFactory<DefectLog, Integer>("removed"));
		logsCategory.setCellValueFactory(new PropertyValueFactory<DefectLog, Integer>("category"));
		logsStatus.setCellValueFactory(new PropertyValueFactory<DefectLog, Integer>("status"));
		logsFix.setCellValueFactory(new PropertyValueFactory<DefectLog, Integer>("fix"));
		logsDefectTable.setItems(Main.getUserList().get(Main.getId()).getDefectLogList());
		
		logsProjectCombo2.setCellFactory(ComboBoxListCell.forListView(Main.getUserList().get(Main.getId()).getDefinitions().getProjects()));
		logsProjectCombo2.setItems(Main.getUserList().get(Main.getId()).getDefinitions().getProjects());
		logsProjectCombo2.valueProperty().addListener((options, oldValue, newValue) -> {
			logsProjectCombo1.setValue(newValue);
	});*/
		
	}

}
