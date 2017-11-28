package view_control;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ImageFile;

import java.util.ArrayList;

class ContainTagScene {

    /** Initialize an ListView of string to display the current tags. */
    private static ListView<String> listView;

    /** Magic number 350 */
    private static final int MAGIC350 = 350;

    /** Magic number 120 */
    private static final int MAGIC120 = 120;

    /** Magic number 10 */
    private static final int MAGIC10 = 10;

    /** Magic number 20 */
    private static final int MAGIC20 = 20;

    /** Magic number 400 */
    private static final int MAGIC400 = 400;

    private static ArrayList<ImageFile> imgFiles = new ArrayList<>();

    private static ImageView imageView = new ImageView();

    private static StackPane paneCenter = new StackPane();
    private static BorderPane inputGridPane = new BorderPane();

    /** Display the Scene and construct the buttons. */
    static void display(){
        Stage window = new Stage();
        inputGridPane.setPrefSize(500,450);
        paneCenter.setStyle("-fx-background-color: #f5f5dc");

        window.setTitle("Show Images with Tags");
        window.initModality(Modality.APPLICATION_MODAL);
//        Label label = new Label("Please check the tag(s) you want to delete");
        Button select = new Button("Select");
        Button back = new Button("Go back");
        select.setMinWidth(MAGIC120);
        back.setMinWidth(MAGIC120);


        listView = new ListView<>();
        for (ImageFile file : imgFiles) {
            listView.getItems().add(file.getFile().getName());
        }

        ToolBar toolbar = new ToolBar();
        toolbar.getItems().addAll(select, back);
        select.setOnAction(e -> buttonClicked());

        back.setOnAction(e -> window.close());

        inputGridPane.setCenter(paneCenter);
        inputGridPane.setLeft(listView);
        inputGridPane.setBottom(toolbar);

        VBox layout = new VBox(MAGIC10);
        layout.setPadding(new Insets(MAGIC20, MAGIC20, MAGIC20, MAGIC20));
        layout.getChildren().addAll(select, back, inputGridPane);


        Scene scene = new Scene(layout, 700, 700);
        window.setScene(scene);
        window.show();
    }

    /**
     * Delete the selected tags once button has been delete clicked.
     */

    private static void buttonClicked() {
        ObservableList<String> imgNames;
        imgNames = listView.getSelectionModel().getSelectedItems();
        if(imgNames.size() == 1) {
            String name = imgNames.get(0);
            for (ImageFile file : imgFiles) {
                if ((file.getFile().getName()).equals(name)){
                    setImage(file);
                }
            }
        }
    }

    static void setImageFilesWithTags(ArrayList<ImageFile> imageFile){
        imgFiles = imageFile;

    }

    private static void setImage(ImageFile file) {

        if (file != null) {
            BorderPane.clearConstraints(imageView);
            Image img = new Image(file.getFile().toURI().toString());
            imageView = new ImageView(img);
            imageView.setFitHeight(400);
            imageView.setFitWidth(400);

            paneCenter.getChildren().add(imageView);
            StackPane.setMargin(imageView, new Insets(50, MAGIC10, 50, 50));
        }
    }
}
