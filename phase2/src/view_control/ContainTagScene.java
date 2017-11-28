package view_control;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ImageFile;

import java.io.IOException;
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
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                buttonClicked();
            }
        });

        for (ImageFile file : imgFiles) {
            listView.getItems().add(file.getFile().getName());
        }

        ToolBar toolbar = new ToolBar();
        toolbar.getItems().add(back);
        select.setOnAction(e -> buttonClicked());

        back.setOnAction(e -> {
            paneCenter.getChildren().remove(imageView);
            window.close();
                }
        );


        inputGridPane.setCenter(paneCenter);
        inputGridPane.setLeft(listView);
        inputGridPane.setBottom(toolbar);

        VBox layout = new VBox(MAGIC10);
        layout.setPadding(new Insets(MAGIC20, MAGIC20, MAGIC20, MAGIC20));
        layout.getChildren().add(inputGridPane);


        Scene scene = new Scene(layout, 950, 700);
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

    static void setImage(ImageFile file) {
        paneCenter.getChildren().remove(imageView);
        if (file != null) {
//            BorderPane.clearConstraints(imageView);
            Image img = new Image(file.getFile().toURI().toString());
            imageView = new ImageView(img);
            imageView.setFitHeight(400);
            imageView.setFitWidth(400);
            paneCenter.getChildren().add(imageView);
            StackPane.setMargin(imageView, new Insets(50, MAGIC10, 50, 50));
    }
}
}
