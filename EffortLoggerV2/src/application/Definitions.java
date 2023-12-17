package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Definitions {
	private ObservableList<String> projects = FXCollections.observableArrayList();
	private ObservableList<Integer> projectLifeCycleNums = FXCollections.observableArrayList();
	private ObservableList<String> lifeCycleSteps = FXCollections.observableArrayList();
	private ObservableList<Integer> ecdNums = FXCollections.observableArrayList();
	private ObservableList<String> effortCategories = FXCollections.observableArrayList();
	private ObservableList<String> plans = FXCollections.observableArrayList();
	private ObservableList<String> deliverables = FXCollections.observableArrayList();
	private ObservableList<String> interruptions = FXCollections.observableArrayList();
	private ObservableList<String> defectCategories = FXCollections.observableArrayList();
	
	public ObservableList<String> getProjects() {
		return projects;
	}
	public void setProjects(ObservableList<String> projects) {
		this.projects = projects;
	}
	public ObservableList<Integer> getProjectLifeCycleNums() {
		return projectLifeCycleNums;
	}
	public void setProjectLifeCycleNums(ObservableList<Integer> projectLifeCycleNums) {
		this.projectLifeCycleNums = projectLifeCycleNums;
	}
	public ObservableList<String> getLifeCycleSteps() {
		return lifeCycleSteps;
	}
	public void setLifeCycleSteps(ObservableList<String> lifeCycleSteps) {
		this.lifeCycleSteps = lifeCycleSteps;
	}
	public ObservableList<Integer> getEcdNums() {
		return ecdNums;
	}
	public void setEcdNums(ObservableList<Integer> ecdNums) {
		this.ecdNums = ecdNums;
	}
	public ObservableList<String> getEffortCategories() {
		return effortCategories;
	}
	public void setEffortCategories(ObservableList<String> effortCategories) {
		this.effortCategories = effortCategories;
	}
	public ObservableList<String> getPlans() {
		return plans;
	}
	public void setPlans(ObservableList<String> plans) {
		this.plans = plans;
	}
	public ObservableList<String> getDeliverables() {
		return deliverables;
	}
	public void setDeliverables(ObservableList<String> deliverables) {
		this.deliverables = deliverables;
	}
	public ObservableList<String> getInterruptions() {
		return interruptions;
	}
	public void setInterruptions(ObservableList<String> interruptions) {
		this.interruptions = interruptions;
	}
	public ObservableList<String> getDefectCategories() {
		return defectCategories;
	}
	public void setDefectCategories(ObservableList<String> defectCategories) {
		this.defectCategories = defectCategories;
	}
}
