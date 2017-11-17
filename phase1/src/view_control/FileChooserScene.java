package view_control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import model.ImageFile;
import model.ImageFileManager;

public final class FileChooserScene extends Application {


    private Scene logTextScene, fileChooserScene;
    private static Stage fileChooserStage;

    @Override
    public void start(final Stage stage) throws FileNotFoundException {
        FileChooserScene.fileChooserStage = stage;
        stage.setTitle("Image Manager");

        final FileChooser fileChooser = new FileChooser();

        final Button openButton = new Button("Open a Picture");
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
              ImageFileManager imageFileManager = new ImageFileManager();

              //get back a ImageFile to keep track of this ImageFile
                try {
                    ImageFile imageFile = imageFileManager.checkExist(fileName, filePath);
                    ManipulationManagerScene.setFile(imageFile);
                    Image inputImg = new Image(file.toURI().toString());
                    ManipulationManagerScene.setImage(inputImg);
                    ManipulationManagerScene.display();
                    fileChooserStage.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
//                Image inputImg = new Image(file.toURI().toString());
//                ManipulationManagerScene.setImage(inputImg);
//                ManipulationManagerScene.display();
//              openFile(file);
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

        //resize the button
        openButton.setMinWidth(120);
        quit.setMinWidth(120);
        goBack.setMinWidth(120);
        getLog.setMinWidth(120);

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


        //The Log Scene
        goBack.setOnAction(event ->  fileChooserStage.setScene(fileChooserScene));
        Text logHistory = new Text(ImageFile.getLog());
        VBox logLayout = new VBox(20);
        logLayout.getChildren().addAll(goBack);
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
                new FileChooser.ExtensionFilter("GIF", "*.gif"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg")
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

    static void display(){
        fileChooserStage.show();
    }

}