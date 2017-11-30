package view_control;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.*;
import javafx.stage.DirectoryChooser;
import model.ImageFile;

import java.io.File;
import java.io.IOException;

class MoveFileScene {

  /** Magic number 10 */
  private static final int MAGIC10 = 10;

  /** Magic number 20 */
  private static final int MAGIC20 = 20;

  /** Magic number 250 */
  private static final int MAGIC250 = 250;

  /** Magic number 200 */
  private static final int MAGIC200 = 200;

  /** Initialize an ImageFile */
  private static ImageFile inputFile;

  private static Stage dialogStage;

  /** Display the Scene and construct the buttons. */
  static void display() {
    Stage moveToStage = new Stage();
    moveToStage.initModality(Modality.APPLICATION_MODAL);
    moveToStage.setTitle("Select a directory");
    moveToStage.setMinWidth(MAGIC250);
    Label MoveInstruction = new Label();
    MoveInstruction.setText("Please select a directory that you want the Image to move");

    // Create two buttons
    Button move = new Button("Select Directory");
    Button goBack = new Button("Go back Last Page");

    move.setMinWidth(MAGIC200);
    goBack.setMinWidth(MAGIC200);

    // Directory Chooser adapted from ORACLE:
    // https://docs.oracle.com/javafx/2/ui_controls/file-chooser.html
    move.setOnAction(
        e -> {
          if (inputFile != null) {
            String directory;
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDirectory = directoryChooser.showDialog(moveToStage);
            if (selectedDirectory != null) {
              directory = selectedDirectory.getAbsolutePath();
              if (!collison(directory)) {
                try {
                  ImageFile inputFileSer = inputFile;
                  ImageFile saveCurrent = inputFile;
                  inputFileSer.changeDirectory(directory);
                  ManipulationManagerScene.imageFileManager.delete(
                      saveCurrent, ManipulationManagerScene.imageFileManagerPath);
                  ManipulationManagerScene.imageFileManager.add(
                      inputFileSer, ManipulationManagerScene.imageFileManagerPath);
                  inputFile = inputFileSer;
                } catch (Exception e1) {
                  e1.printStackTrace();
                }
                if (!isInSubDirectory(
                    ManipulationManagerScene.getCurrentDirectory(), inputFile.getFile())) {
                  ManipulationManagerScene.imgFiles.remove(inputFile);
                }
                ManipulationManagerScene.setImageListView(ManipulationManagerScene.imgFiles);
                  //                  ManipulationManagerScene.setFile(null);
//                  ManipulationManagerScene.setImage();
                  ManipulationManagerScene.setPath(inputFile);
                  moveToStage.close();
              }
            }
          }
        });

    goBack.setOnAction(e -> moveToStage.close());

    VBox addTagLayout = new VBox(MAGIC10);
    addTagLayout.setPadding(new Insets(MAGIC20, MAGIC20, MAGIC20, MAGIC20));
    addTagLayout.getChildren().addAll(MoveInstruction, move, goBack);
    addTagLayout.setAlignment(Pos.CENTER);

    Scene tagScene = new Scene(addTagLayout);
    moveToStage.setScene(tagScene);
    moveToStage.showAndWait();
  }

  /**
   * Set the ImageFile
   *
   * @param imageFile the ImageFile
   */
  static void setImageFile(ImageFile imageFile) {
    inputFile = imageFile;
  }

  private static boolean isInSubDirectory(File dir, File file) {

    return file != null && (file.equals(dir) || isInSubDirectory(dir, file.getParentFile()));
  }

  private static void inappropriateMove() {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Inappropriate Move");
    alert.setHeaderText("This directory already has an image with the same name!");
    alert.setContentText("Choose another directory");

    alert.showAndWait();
  }

  private static boolean collison(String directory) {
    for (ImageFile file : ManipulationManagerScene.imgFiles) {
      if (!inputFile.equals(file) && file.getFile().getParent().equals(directory)) {
        if ((inputFile.getOriginalName()).equals(file.getOriginalName())) {
          inappropriateMove();
          return true;
        }
      }
    }
    return false;
  }
}
