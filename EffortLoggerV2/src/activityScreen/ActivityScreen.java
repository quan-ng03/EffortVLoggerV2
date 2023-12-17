package activityScreen;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.InetAddress;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ActivityScreen extends Application {

    private Text sessionTitle;
    private Text hostnameText;
    private TableView<UserStory> userStoriesTable;
    private Text userStoryTitleText;
    private Button startButton;
    private Button stopButton;
    private Text timerText;
    
    
    
    private Timer timer;
    private int secondsElapsed;
    private List<String> userStories = Arrays.asList("User Story 1", "User Story 2", "User Story 3", "User Story 4");
    private boolean userStorySelected = false;
   
   
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Activity Screen");

        // Create elements
        sessionTitle = new Text("Session Title: Activity Screen ");
        hostnameText = new Text("Hostname: " + getHostName());
        userStoryTitleText = new Text("Selected User Story: ");
        userStoriesTable = createUserStoriesTable();
        startButton = new Button("Start");
        stopButton = new Button("Stop");
        timerText = new Text("00:00:00");
        timerText.setVisible(false);
        startButton.setVisible(false);
        stopButton.setVisible(false);

        // Button actions
        startButton.setOnAction(e -> {
            startSession();
        });

        stopButton.setOnAction(e -> {
        	stopSession();
        });

        // Layout
        VBox layout = new VBox(20);
        layout.getChildren().addAll(sessionTitle, hostnameText,userStoryTitleText, userStoriesTable, startButton, stopButton, timerText);
        layout.setSpacing(10);
        layout.setPrefSize(500, 400); // Setting preferred size for the VBox
        layout.setTranslateX(50);
        layout.setTranslateY(50);
        
        startButton.setMaxWidth(300);
        stopButton.setMaxWidth(300);
        
        Scene scene = new Scene(layout);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to get the hostname
    private String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (Exception e) {
            return "Hostname not found";
        }
    }
    
    // Get the User Story
    private TableView<UserStory> createUserStoriesTable() {
    	TableView<UserStory> table = new TableView<>();

        TableColumn<UserStory, String> nameColumn = new TableColumn<>("User Story Name");
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

        TableColumn<UserStory, String> creatorColumn = new TableColumn<>("Creator");
        creatorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCreator()));

        TableColumn<UserStory, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));

        TableColumn<UserStory, String> relevanceColumn = new TableColumn<>("Relevance");
        relevanceColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRelevance()));

        table.getColumns().addAll(nameColumn, creatorColumn, descriptionColumn, relevanceColumn);

        List<UserStory> userStories = loadUserStoriesFromCSV("src/ user_stories.csv");
        table.getItems().addAll(userStories);

        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                userStorySelected = true; // Set the flag to true
                startButton.setVisible(true);
                stopButton.setVisible(true);
                userStoryTitleText.setText("Selected User Story: " + newSelection.getName());
                if (timer != null) {
                    timer.cancel();
                    timer.purge();
                    timerText.setVisible(false);
                }
                secondsElapsed = 0;
                timerText.setText("00:00:00");
            } else {
                userStorySelected = false; // Set the flag to false if no User Story is selected
                startButton.setVisible(false);
                stopButton.setVisible(false);
            }
        });

        return table;
    }
    
    private List<UserStory> loadUserStoriesFromCSV(String filename) {
        List<UserStory> userStories = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            br.readLine(); // Skip the header line
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    UserStory userStory = new UserStory(data[0], data[1], data[2], data[3]);
                    userStories.add(userStory);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userStories;
    }

    // Sample methods for session start and stop
    private void startSession() {
    	if (userStorySelected) {
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    secondsElapsed++;
                    updateTimerText();
                }
            }, 0, 1000); // Update timer every second
            timerText.setVisible(true);
        }
    }

    private void stopSession() {
    	if (userStorySelected && timer != null) {
            timer.cancel();
            timer.purge();
            timerText.setVisible(false);
        }
    }
    
    
    // Update the displayed timer
    private void updateTimerText() {
        int hours = secondsElapsed / 3600;
        int minutes = (secondsElapsed % 3600) / 60;
        int seconds = secondsElapsed % 60;

        DecimalFormat df = new DecimalFormat("00");
        String time = df.format(hours) + ":" + df.format(minutes) + ":" + df.format(seconds);
        timerText.setText(time);
    }


    // You can add more methods for other functionalities as needed

    public static void main(String[] args) {
        launch(args);
    }
}