package base.config;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StageManager {

    private final Stage primaryStage;
    private final SpringFXMLLoader springFXMLLoader;

    public StageManager(SpringFXMLLoader springFXMLLoader, Stage stage) {
        this.springFXMLLoader = springFXMLLoader;
        this.primaryStage = stage;
    }

    public void switchScene(String fxmlPath, String title) {
        Parent rootNode = loadView(fxmlPath);
        Scene scene = new Scene(rootNode);
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    private Parent loadView(String fxmlPath) {
        try {
            return springFXMLLoader.load(fxmlPath);
        } catch (Exception e) {
            e.printStackTrace();
            Platform.exit();
            return null;
        }
    }
}