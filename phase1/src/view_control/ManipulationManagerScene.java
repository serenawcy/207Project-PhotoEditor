package view_control;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.ImageFile;



import java.io.File;


public class ManipulationManagerScene{

    private static ImageFile imgFile;
    private static Image img;
    private static ImageView imageView;



    public static void display() {
        Stage window = new Stage();
        window.setTitle("Manipulation Scene");
        Button add = new Button("Add New Tag");
        add.setMinWidth(120);
        Button delete = new Button("Delete Tag");
        delete.setMinWidth(120);
        Button select = new Button("Select Old Tag");
        select.setMinWidth(120);
        Button move = new Button("Move To");
        move.setMinWidth(120);
        Button back = new Button("Back");
        back.setMinWidth(120);
        Button rename = new Button("Rename");
        rename.setMinWidth(120);


        add.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                AddTagScene.setImageFile(imgFile);
                AddTagScene.display();
                }
            }
        );
        delete.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        DeleteTagScene.setImageFile(imgFile);
                        DeleteTagScene.display();
                    }
                }
        );
        select.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        SelectTagScene.setImageFile(imgFile);
                        SelectTagScene.display();
                    }
                }
        );
        move.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        MoveFileScene.setImageFile(imgFile);
                        MoveFileScene.display();
                    }
                }
        );
        back.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        FileChooserScene.display();
                        window.close();
                    }
                }
        );
        rename.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        FileRenameScene.setImageFile(imgFile);
                        FileRenameScene.display();
                    }
                }

        );


        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(add, delete, select, move, rename, back, imageView);
        layout1.setAlignment(Pos.CENTER);
        Scene general = new Scene(layout1, 400, 600);
        window.setScene(general);
        window.show();


    }

    public static void setImage(Image image){
        img = image;
        imageView = new ImageView(img);
        imageView.setFitHeight(300);
        imageView.setFitWidth(300);
 //       imageView.setPreserveRatio(true);
    }

    public static void setFile(ImageFile imageFile){
        imgFile = imageFile;
    }
}

