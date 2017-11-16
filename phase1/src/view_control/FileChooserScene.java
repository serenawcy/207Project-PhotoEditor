package view_control;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.text.Text;

public final class FileChooserScene extends Application {


    private Desktop desktop = Desktop.getDesktop();
    private Scene logTextScene, fileChooserScene;
    File inputFile;
    private static Stage fileChooserStage;
    @Override
    public void start(final Stage stage) {
        FileChooserScene.fileChooserStage = stage;
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
//              System.out.println(filePath);
//              image inputImage = new Image(fileName, filePath);
//              inputImage = ImageManager.checkExist();
//                Image img = new Image(file.toURI().toString());
//                ManipulationManagerScene.setImage(img);
//                ManipulationManagerScene.setFile(inputImage);
                ManipulationManagerScene.display();
//              openFile(file);
              MoveFileScene.display();
              fileChooserStage.close();
            }
          }
        });

        getLog.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        fileChooserStage.setScene(logTextScene);
                    }
                });

        quit.setOnAction(event -> fileChooserStage.close());

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

        fileChooserScene = new Scene(rootGroup);
        fileChooserStage.setScene(fileChooserScene);
        fileChooserStage.show();


//       Log Scene
        goBack.setOnAction(event ->  fileChooserStage.setScene(fileChooserScene));
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

//    private void openFile(File file) {
//        try {
//            desktop.open(file);
//        } catch (IOException ex) {
//            Logger.getLogger(
//                    FileChooserScene.class.getName()).log(
//                    Level.SEVERE, null, ex
//            );
//        }
//    }

    public static void display(){
        fileChooserStage.show();
    }

}