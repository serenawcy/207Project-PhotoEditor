package view_control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.ImageFile;
import model.ImageFileManager;

public final class FileChooserScene extends Application {


    private static Stage fileChooserStage;

    @Override
    public void start(final Stage stage) throws FileNotFoundException {
        FileChooserScene.fileChooserStage = stage;
        stage.setTitle("Choose An Image");

        FileChooser fileChooser = new FileChooser();

        Button openButton = new Button("Open a Picture");
        Button quit = new Button("Quit");
        Button goBack = new Button("Go Back");

        openButton.setOnAction(
                e -> {
                    configureFileChooser(fileChooser);
                    File file = fileChooser.showOpenDialog(stage);
                    if (file != null) {
                        String serPath = "./serializedImageFiles.ser";
                        try {
                            boolean checkFileExist = false;
                            ImageFile inputFile = new ImageFile(file);
                            ImageFileManager imageFileManager = new ImageFileManager(serPath);
                            ArrayList<ImageFile> imageFiles = ImageFileManager.getImageFileList();

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
                });

        quit.setOnAction(event -> fileChooserStage.close());

        //resize the button
        openButton.setMinWidth(120);
        quit.setMinWidth(120);
        goBack.setMinWidth(120);

        final GridPane inputGridPane = new GridPane();
        GridPane.setConstraints(openButton, 0, 0);
        GridPane.setConstraints(quit, 2, 0);
        inputGridPane.setHgap(6);
        inputGridPane.setVgap(6);
        inputGridPane.getChildren().addAll(openButton, quit);

        final Pane rootGroup = new VBox(12);
        rootGroup.getChildren().addAll(inputGridPane);
        rootGroup.setPadding(new Insets(12, 12, 12, 12));

        Scene fileChooserScene = new Scene(rootGroup);
        fileChooserStage.setScene(fileChooserScene);
        fileChooserStage.show();
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