package application;

import javafx.scene.control.TextField;

public class LifeCycleStep {
	TextField name;
	TextField EC;
	TextField D;
	
	public LifeCycleStep() {
		name = new TextField("");
		name.setPromptText("");
		EC = new TextField("");
		EC.setPromptText("");
		D = new TextField("");
		D.setPromptText("");
	}
	
	public String toString() {
		return name.getText();
	}
}
