package view_control;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import model.ImageFile;

import java.io.IOException;
import java.util.Objects;

class AddTagScene {

    private static ImageFile inputFile;


    static void display() {
        Stage addScene = new Stage();
        addScene.initModality(Modality.APPLICATION_MODAL);
        addScene.setTitle("Add tag(s)");
        addScene.setMinWidth(250);
        Label AddInstruction = new Label();
        AddInstruction.setText("Please enter tag(s), separated with \",\"");

        //Create two buttons
        Button done = new Button("Done");
        Button goBack = new Button("Go back");
        //Form a Text area
        TextField tagInput = new TextField();

    // Clicking will addTags and close window
    done.setOnAction(
        e -> {
          String tags = tagInput.getText();
          if (!Objects.equals(tags, "")){
            try {
                inputFile.addTag(tags);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            tagInput.clear();
          }
        });
        goBack.setOnAction(e -> {
            //            ManipulationManagerScene.setFile(inputFile);
            addScene.close();
        });

        done.setMinWidth(100);
        goBack.setMinWidth(100);
        VBox addTagLayout = new VBox(10);
        addTagLayout.setPadding(new Insets(20, 20, 20, 20));
        addTagLayout.getChildren().addAll(AddInstruction, tagInput, done, goBack);
        addTagLayout.setAlignment(Pos.CENTER);

        Scene tagScene = new Scene(addTagLayout);
        addScene.setScene(tagScene);
        addScene.showAndWait();
    }

    static void setImageFile(ImageFile imageFile){
        inputFile = imageFile;
    }
}
