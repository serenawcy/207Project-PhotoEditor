package view_control;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.*;
import javafx.stage.DirectoryChooser;

import java.io.File;

public class MoveFileScene {

//    private Image inputImage;
//    private static Stage moveTo;
    private static Stage moveTo = new Stage();

    public static void display() {
//        moveTo = new Stage();
        moveTo.initModality(Modality.APPLICATION_MODAL);
        moveTo.setTitle("Select a directory");
        moveTo.setMinWidth(250);
        Label MoveInstruction = new Label();
        MoveInstruction.setText("Please select a directory that you want the Image to move");

        //Create two buttons
        Button move = new Button("Select Directory");
        Button goBack = new Button("Go back Last Page");

        move.setMinWidth(200);
        goBack.setMinWidth(200);

    move.setOnAction(
        e -> {
          //                    String directoryPath = moveDirectory();
          String directory;
          final DirectoryChooser directoryChooser = new DirectoryChooser();
          final File selectedDirectory = directoryChooser.showDialog(moveTo);
          if (selectedDirectory != null) {
              directory = selectedDirectory.getAbsolutePath();
            System.out.println(directory);
            //                        inputImage.changeDirectory(directoryPath);
          }
          //            ManipulationManagerScene.setFile(inputImage);
//            ManipulationManagerScene.display();
          moveTo.close();
        });

        goBack.setOnAction(e -> {
            FileChooserScene.display();
            moveTo.close();
//            ManipulationManagerScene.setFile(inputFile);
//            ManipulationManagerScene.display();
        });

        VBox addTagLayout = new VBox(10);
        addTagLayout.setPadding(new Insets(20, 20, 20, 20));
        addTagLayout.getChildren().addAll(MoveInstruction, move, goBack);
        addTagLayout.setAlignment(Pos.CENTER);

        Scene tagScene = new Scene(addTagLayout);
        moveTo.setScene(tagScene);
        moveTo.show();
    }

//    public void setImage(Image image){
//        this.inputImage = image;
//    }

//    private static String moveDirectory() {
//        String directory;
//        final DirectoryChooser directoryChooser = new DirectoryChooser();
//        final File selectedDirectory = directoryChooser.showDialog(moveTo);
//        if (selectedDirectory != null) {
//            directory = selectedDirectory.getAbsolutePath();
//            return directory;
//        }
//    }
}
