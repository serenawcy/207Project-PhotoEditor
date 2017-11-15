package view_control;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.apple.eio.FileManager;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.control.Button;

public final class FileChooserScene extends Application {


    private Desktop desktop = Desktop.getDesktop();
    Scene logTextScene;
    File inputFile;
    @Override
    public void start(final Stage stage) {
        stage.setTitle("Image Manager");

        final FileChooser fileChooser = new FileChooser();

        final Button openButton = new Button("Open a Picture...");
        final Button getLog = new Button("Get log history");
        final Button quit = new Button("Quit");
        Button goBack = new Button("Go Back");

        openButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        configureFileChooser(fileChooser);
                        File file = fileChooser.showOpenDialog(stage);
                        if (file != null) {
                            String filePath = file.getAbsolutePath();
                            String fileName = file.getName();
//                            File inputFile = new File(fileName, filePath);
//                            inputFile = FileManager.checkExist();
//                            ManipulationManagerScene.setFile(inputFile);
//                            ManipulationManagerScene.display();
                            openFile(file);
                            ManipulationManagerScene.display();
                            stage.close();
                        }
                    }
                });

        getLog.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        stage.setScene(logTextScene);
                            }
                });

        quit.setOnAction(event -> stage.close());

        final GridPane inputGridPane = new GridPane();
        GridPane.setConstraints(openButton, 0, 0);
        GridPane.setConstraints(getLog, 1, 0);
        GridPane.setConstraints(quit, 2, 0);
        inputGridPane.setHgap(6);
        inputGridPane.setVgap(6);
        inputGridPane.getChildren().addAll(openButton, getLog, quit);

        final Pane rootGroup = new VBox(12);
        rootGroup.getChildren().addAll(inputGridPane);
        rootGroup.setPadding(new Insets(12, 12, 12, 12));

        Scene fileChooserScene = new Scene(rootGroup);
        stage.setScene(fileChooserScene);
        stage.show();


//       Log Scene
        goBack.setOnAction(event ->  stage.setScene(fileChooserScene));
//                Text logHistory = new Text(inputFile.getLog());
        Text logHistory = new Text(25, 25,"AASDFSAF \n daskfjalsd");
        VBox logLayout = new VBox(20);
        logLayout.getChildren().addAll(goBack, logHistory);
        logTextScene = new Scene(logLayout, 600, 300);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    private static void configureFileChooser(final FileChooser fileChooser) {
        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp"),
                new FileChooser.ExtensionFilter("GIF", "*.gif")
        );
    }

    private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(
                    FileChooserScene.class.getName()).log(
                    Level.SEVERE, null, ex
            );
        }
    }

}

