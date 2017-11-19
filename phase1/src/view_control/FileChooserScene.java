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

    /** Magic number 120 */
    private static final int MAGIC120 = 120;

    /** Magic number 6 */
    private static final int MAGIC6 = 6;

    /** Magic number 12 */
    private static final int MAGIC12 = 12;

    /** Initialize an Stage */
    private static Stage fileChooserStage;

    public static void main(String[] args) {
        Application.launch(args);
    }

    /**
     * A FileChooserScene scene than contains buttons for the user to select an Image File from their
     * computers.
     */
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

                            for (ImageFile imgfile : imageFiles) {
                                if (imgfile.equals(inputFile)) {
                                    inputFile = imgfile;
                                    checkFileExist = true;
                                }
                            }
                            if (!checkFileExist) {
                                ImageFileManager.add(inputFile);
                                ImageFileManager.writeToFile(serPath);
                            }
                            ManipulationManagerScene.setFile(inputFile);
                            ManipulationManagerScene.display();
                            fileChooserStage.close();

                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (ClassNotFoundException e1) {
                            e1.printStackTrace();
                        }
                    }
                });

        quit.setOnAction(event -> fileChooserStage.close());

        // resize the button
        openButton.setMinWidth(MAGIC120);
        quit.setMinWidth(MAGIC120);
        goBack.setMinWidth(MAGIC120);

        final GridPane inputGridPane = new GridPane();
        GridPane.setConstraints(openButton, 0, 0);
        GridPane.setConstraints(quit, 2, 0);
        inputGridPane.setHgap(MAGIC6);
        inputGridPane.setVgap(MAGIC6);
        inputGridPane.getChildren().addAll(openButton, quit);

        final Pane rootGroup = new VBox(MAGIC12);
        rootGroup.getChildren().addAll(inputGridPane);
        rootGroup.setPadding(new Insets(MAGIC12, MAGIC12, MAGIC12, MAGIC12));

        Scene fileChooserScene = new Scene(rootGroup);
        fileChooserStage.setScene(fileChooserScene);
        fileChooserStage.show();
    }

    /** Choose an Image with jpg, png, bmp, gif, jepg format only from user's computer. */
    private static void configureFileChooser(final FileChooser fileChooser) {
        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser
                .getExtensionFilters()
                .addAll(
                        new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                        new FileChooser.ExtensionFilter("PNG", "*.png"),
                        new FileChooser.ExtensionFilter("BMP", "*.bmp"),
                        new FileChooser.ExtensionFilter("GIF", "*.gif"),
                        new FileChooser.ExtensionFilter("JPEG", "*.jpeg"));
    }

    /** Display the Scene. */
    static void display() {
        fileChooserStage.show();
    }
}
