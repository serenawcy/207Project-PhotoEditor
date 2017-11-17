package view_control;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.ListView;
import model.ImageFile;

import java.io.File;
import java.util.ArrayList;

public class DeleteTagScene {

    private static ListView<String> listView;
    private static ImageFile inputFile;

//    public static void main(String[] args) {
//        launch(args);
//    }
    public static void display(){
        Stage window = new Stage();
        window.setTitle("Delete Tag(s)");
        window.initModality(Modality.APPLICATION_MODAL);
        Label label = new Label("Please check the tag(s) you want to delete");
        Button Delete = new Button("Delete");
        Button Back = new Button("Go back");
        Delete.setMinWidth(120);
        Back.setMinWidth(120);

        listView = new ListView<>();
        for(String tag: inputFile.getTagManager().readTags()) {
            listView.getItems().add(tag);
        }
//        listView.getItems().addAll("Iron Man", "Titanic", "Contact", "Surrogates");
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        Delete.setOnAction(e -> buttonClicked());
        Back.setOnAction(e -> {
            //            ManipulationManagerScene.setFile(inputFile);
            window.close();
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(listView, Delete, Back);

        Scene scene = new Scene(layout, 300, 250);
        window.setScene(scene);
        window.show();

    }

    private static void buttonClicked() {
        ObservableList<String> tags;
        tags = listView.getSelectionModel().getSelectedItems();
        for (String tag: tags){
            try {
                inputFile.deleteTag(tag);
            } catch (Exception e) {
                e.printStackTrace();
            }
            listView.getItems().remove(tag);

        }

    }
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        Stage window = new Stage();
//        window.setTitle("Delete Tag(s)");
//        Label label = new Label("Please check the tag(s) you want to delete");
//        Button Delete = new Button("Delete");
//
//        listView = new ListView<>();
////        for(String tag: Image.getTags.ReadTag()) {
////            listView.getItems().add(tag);
////        }
//        listView.getItems().addAll("Iron Man", "Titanic", "Contact", "Surrogates");
//        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//        Delete.setOnAction(e -> buttonClicked());
//
//        VBox layout = new VBox(10);
//        layout.setPadding(new Insets(20, 20, 20, 20));
//        layout.getChildren().addAll(listView, Delete);
//
//        Scene scene = new Scene(layout, 300, 250);
//        window.setScene(scene);
//        window.show();
//
//    }

    public static void setImageFile(ImageFile imageFile){
        inputFile = imageFile;
    }

}