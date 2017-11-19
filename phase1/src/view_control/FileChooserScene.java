package view_control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

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
    private static Text logHistory = new Text();
//
//    private static String serPath = "./serializedImageFiles.ser";
//    private static ImageFileManager imageFileManager = new ImageFileManager(serPath);

    @Override
    public void start(final Stage stage) throws FileNotFoundException {
        FileChooserScene.fileChooserStage = stage;
        stage.setTitle("Image Manager");

        FileChooser fileChooser = new FileChooser();

        Button openButton = new Button("Open a Picture");
        Button getLog = new Button("Get log history");
        Button quit = new Button("Quit");
        Button goBack = new Button("Go Back");
        VBox logLayout = new VBox(20);

    openButton.setOnAction(
        new EventHandler<ActionEvent>() {
          @Override
          public void handle(final ActionEvent e) {
            configureFileChooser(fileChooser);
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                String serPath = "./serializedImageFiles.ser";
                try {
                    System.out.println("User的照片"+ file.getAbsolutePath());
                    boolean checkFileExist = false;
                    ImageFile inputFile = new ImageFile(file);
                    ImageFileManager imageFileManager = new ImageFileManager(serPath);
                    ArrayList<ImageFile> imageFiles = ImageFileManager.getImageFileList();
                    System.out.println(imageFiles + "ahahaha");
                    for(ImageFile imgfile: imageFiles){
                        if(imgfile.equals(inputFile)){
                            inputFile = imgfile;
                            checkFileExist = true;
                        }

                    }
                    if(!checkFileExist){
                        ImageFileManager.add(inputFile);
                        ImageFileManager.writeToFile(serPath);
                    }
                    ManipulationManagerScene.setFile(inputFile);
                    ManipulationManagerScene.display();
                    fileChooserStage.close();

                } catch (ClassNotFoundException | IOException e1) {
                    e1.printStackTrace();
                }
            }
          }
        });

        getLog.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        if (ImageFile.isLogFileExist()) {
                            try {
                                logHistory = new Text(ImageFile.getLog());
//                System.out.println(logHistory);
                                logLayout.getChildren().add(logHistory);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
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
//        Text logHistory = new Text(ImageFile.getLog());
//        VBox logLayout = new VBox(20);
        logLayout.getChildren().add(goBack);
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

    static void display(){
        fileChooserStage.show();
    }

}