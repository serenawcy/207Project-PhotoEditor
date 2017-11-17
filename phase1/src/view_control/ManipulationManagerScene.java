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
        Button Add = new Button("Add New Tag");
        Add.setMinWidth(120);
        Button Delete = new Button("Delete Tag");
        Delete.setMinWidth(120);
        Button Select = new Button("Select Old Tag");
        Select.setMinWidth(120);
        Button Move = new Button("Move To");
        Move.setMinWidth(120);
        Button Back = new Button("Back");
        Back.setMinWidth(120);


        Add.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                AddTagScene.setImageFile(imgFile);
                AddTagScene.display();
                }
            }
        );
        Delete.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        DeleteTagScene.setImageFile(imgFile);
                        DeleteTagScene.display();
                    }
                }
        );
        Select.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        SelectTagScene.setImageFile(imgFile);
                        SelectTagScene.display();
                    }
                }
        );
        Move.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        MoveFileScene.setImageFile(imgFile);
                        MoveFileScene.display();
                    }
                }
        );
        Back.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        FileChooserScene.display();
                        window.close();
                    }
                }
        );


        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(Add, Delete, Select, Move, Back, imageView);
        layout1.setAlignment(Pos.CENTER);
        Scene general = new Scene(layout1, 400, 550);
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

