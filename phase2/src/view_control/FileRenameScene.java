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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

class FileRenameScene {

    private static ListView<String> listView;
    private static ImageFile inputFile;

    /** Magic number 500 */
    private static final int MAGIC500 = 500;

    /** Magic number 600 */
    private static final int MAGIC600 = 600;

    /** Magic number 10 */
    private static final int MAGIC10 = 10;

    /** Magic number 20 */
    private static final int MAGIC20 = 20;

    /** Magic number 120 */
    private static final int MAGIC120 = 120;

    /** Display the Scene and construct the buttons. */
    static void display() {
        Stage window = new Stage();
        window.setTitle("Rename the File");
        window.initModality(Modality.APPLICATION_MODAL);
        Label label = new Label("Please choose the name(s) you want to change");
        Button done = new Button("Done");
        Button goBack = new Button("Go back");
        done.setMinWidth(MAGIC120);
        goBack.setMinWidth(MAGIC120);

        listView = new ListView<>();

        if(inputFile != null) {
            for (String tag : inputFile.getOldName()) {
                listView.getItems().add(tag);
            }
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

        VBox layout = new VBox(MAGIC10);
        layout.setPadding(new Insets(MAGIC20, MAGIC20, MAGIC20, MAGIC20));
        layout.getChildren().addAll(label, listView, done, goBack);

        Scene scene = new Scene(layout, MAGIC500, MAGIC600);
        window.setScene(scene);
        window.show();
    }

    /**
     * Rename the file to be the name selected once button done was clicked.
     * @throws IOException IOException will be thrown.
     */
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
            ManipulationManagerScene.setImageListView(ManipulationManagerScene.imgFiles);
        }

    }

    /**
     * Set the ImageFile
     * @param imageFile the ImageFile
     */
    static void setImageFile(ImageFile imageFile) {
        inputFile = imageFile;
    }
}