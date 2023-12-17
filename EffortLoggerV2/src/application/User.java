package application;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class User {
	private String username;
	private int userID;
	private String roleLevel;
	private EffortLog newEffortLog = new EffortLog();
	//private DefectLog newDefectLog = new DefectLog();
	private ObservableList<EffortLog> effortLogList = FXCollections.observableArrayList();
	private ObservableList<DefectLog> defectLogList = FXCollections.observableArrayList();
	private Definitions definitions = new Definitions();
	private MessageDigest digest;
	
	// Hash the password before creating the user. This will simply store whatever string provided.
	public User(String username, int ID){
		this.username = username;
		this.userID = ID;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setRoleLevel(String roleLevel) {
		this.roleLevel = roleLevel;
	}
	public EffortLog getNewEffortLog() {
		return newEffortLog;
	}
	public void setNewEffortLog(EffortLog newEffortLog) {
		this.newEffortLog = newEffortLog;
	}
	/*public DefectLog getNewDefectLog() {
		return newDefectLog;
	}
	public void setNewDefectLog(DefectLog newDefectLog) {
		this.newDefectLog = newDefectLog;
	}*/
	public ObservableList<EffortLog> getEffortLogList() {
		return effortLogList;
	}
	public void setEffortLogList(ObservableList<EffortLog> effortLogList) {
		this.effortLogList = effortLogList;
	}
	public ObservableList<DefectLog> getDefectLogList() {
		return defectLogList;
	}
	public void setDefectLogList(ObservableList<DefectLog> defectLogList) {
		this.defectLogList = defectLogList;
	}
	public Definitions getDefinitions() {
		return definitions;
	}
	public void setDefinitions(Definitions definitions) {
		this.definitions = definitions;
	}
}