package view_control;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ImageFile;

import java.util.ArrayList;
import java.util.Arrays;

public class FileRenameScene {

    private static ListView<String> listView;
    private static ImageFile inputFile;

    //    public static void main(String[] args) {
//        launch(args);
//    }
    public static void display(){
        Stage window = new Stage();
        window.setTitle("Rename the File");
        window.initModality(Modality.APPLICATION_MODAL);
        Label label = new Label("Please choose the name(s) you want to change");
        Button done = new Button("Done");
        Button goBack = new Button("Go back");
        done.setMinWidth(120);
        goBack.setMinWidth(120);

        listView = new ListView<>();
        ArrayList<String> tagToPass = new ArrayList<>();

        //Serena's (Tag Manager)
        for(String tag: inputFile.getOldName()) {
            listView.getItems().add(tag);
        }

//        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        done.setOnAction(e -> {
            inputFile.changeTagHistory(buttonClicked());

        });
        goBack.setOnAction(e -> {
            //            ManipulationManagerScene.setFile(inputFile);
            window.close();
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(listView, done,goBack );

        Scene scene = new Scene(layout, 300, 250);
        window.setScene(scene);
        window.show();

    }

    private static ArrayList<String> buttonClicked() {
        ObservableList<String> tags;
        ArrayList<String> tagsSelected = new ArrayList<>();
        tags = listView.getSelectionModel().getSelectedItems();
        for (String tag: tags){
            try {
                String FileInfo = tag.substring(0, tag.lastIndexOf("."));
                String[] tagCollection = FileInfo.split("@");

                if (tagCollection.length > 1) {
                    tagsSelected.addAll(Arrays.asList(tagCollection).subList(1, tagCollection.length));
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
            listView.getItems().remove(tag);
        }
        String newName = tags.get(0);
        String[] separate = newName.split("\\.(?=[^.]+$)");
        inputFile.changeImageName(separate[0]);
        return tagsSelected;

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