package view_control;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ImageFile;

import java.util.ArrayList;

public class SelectTagScene {

    //    Stage window;
//    Scene scene;
//    Button button;
    private static ImageFile inputFile;

//    public static void main(String[] args) {
//        launch(args);
//    }

    public static void display(){
        Stage window = new Stage();
        window.setTitle("Select Old Tag(s)");
        window.initModality(Modality.APPLICATION_MODAL);
        Label label = new Label("Please check the tag(s) you want to rename the photo for");
        Button Submit = new Button("Rename");
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        ArrayList checkBox = new ArrayList(); //Type is CheckBox box1 = new CheckBox();
        for (String tag : inputFile.getTagManager().readTags()) {
            CheckBox box = new CheckBox(tag);
            checkBox.add(box);
            layout.getChildren().add(box); //Display
        }
        layout.getChildren().addAll(label,Submit);
        Submit.setOnAction(e -> handleOptions(checkBox));
        Scene scene = new Scene(layout, 500, 250);
        window.setScene(scene);
        window.show();
    }


//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        Stage window = new Stage();
//        window.setTitle("Select Old Tag(s)");
//        window.initModality(Modality.APPLICATION_MODAL);
//        Label label = new Label("Please check the tag(s) you want to rename the photo for");
//        Button Submit = new Button("Submit");
//        VBox layout = new VBox(10);
//        layout.setPadding(new Insets(20, 20, 20, 20));
//        ArrayList checkBox = new ArrayList(); //Type is CheckBox box1 = new CheckBox();
//        for (String tag : inputFile.getTagManager().readTags()) {
//            CheckBox box = new CheckBox(tag);
//            checkBox.add(box);
//            layout.getChildren().add(box); //Display
//        }
//        Submit.setOnAction(e -> handleOptions(checkBox));
//        Scene scene = new Scene(layout, 300, 250);
//        window.setScene(scene);
//        window.show();
//    }

    //Handle checkbox options
    private static void  handleOptions(ArrayList<CheckBox> checkBox) {

        StringBuilder CurrentName = new StringBuilder();
        CurrentName.append(inputFile.getOriginalName());

        for (CheckBox box : checkBox) {
            if (box.isSelected()) {
                CurrentName.append(box.getText());
            }

        }
        inputFile.changeImageName(CurrentName.toString());
    }


    public static void setImageFile(ImageFile imageFile) {
        inputFile = imageFile;
    }

}