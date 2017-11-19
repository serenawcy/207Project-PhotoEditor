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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class FileRenameScene {

  private static ListView<String> listView;
  private static ImageFile inputFile;

  static void display() {
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

    for (String tag : inputFile.getOldName()) {
      listView.getItems().add(tag);
    }

    done.setOnAction(
            e -> {
              try {
                buttonClicked();
              } catch (IOException e1) {
                e1.printStackTrace();
              }
            });
    goBack.setOnAction(e -> window.close());

    VBox layout = new VBox(10);
    layout.setPadding(new Insets(20, 20, 20, 20));
    layout.getChildren().addAll(listView, done, goBack);

    Scene scene = new Scene(layout, 300, 250);
    window.setScene(scene);
    window.show();
  }

  private static void buttonClicked() throws IOException {
    ObservableList<String> names;
    ArrayList<String> nameSelected = new ArrayList<>();
    ArrayList<String> tagWanted = new ArrayList<>();
    names = listView.getSelectionModel().getSelectedItems();
    for (String name : names) {
      try {
        nameSelected.add(name);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    if (nameSelected.size() != 0) {
      String nameGet = nameSelected.get(0);
      String nameToChange = nameGet.substring(0, nameGet.lastIndexOf("."));
      String[] tagWant = nameToChange.split("@");
      Collections.addAll(tagWanted, tagWant);
      tagWanted.remove(0);
      inputFile.changeImageName(nameToChange);
      inputFile.changeTagHistory(tagWanted);
    }
  }

  //        for (String tag: tags){
  //            try {
  //                String FileInfo = tag.substring(0, tag.lastIndexOf("."));
  //                String[] tagCollection = FileInfo.split("@");
  //
  //                if (tagCollection.length > 1) {
  //                    tagsSelected.addAll(Arrays.asList(tagCollection).subList(1,
  // tagCollection.length));
  //                }
  //            }catch (Exception e) {
  //                e.printStackTrace();
  //            }
  //            listView.getItems().remove(tag);
  //        }
  //        String newName = tags.get(0);
  //        String[] separate = newName.split("@");
  //        System.out.println(separate[0]);
  //        inputFile.changeImageName(separate[0]);
  //        return tagsSelected;

  //    @Override
  //    public void start(Stage primaryStage) throws Exception {
  //        Stage window = new Stage();
  //        window.setTitle("Delete Tag(s)");
  //        Label label = new Label("Please check the tag(s) you want to delete");
  //        Button Delete = new Button("Delete");
  //
  //        listView = new ListView<>();
  //          for(String tag: Image.getTags.ReadTag()) {
  //              listView.getItems().add(tag);
  //          }
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

  static void setImageFile(ImageFile imageFile) {
    inputFile = imageFile;
  }
}