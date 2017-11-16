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
        Button Delete = new Button("Delete Tag");
        Button Select = new Button("Select Old Tag");
        Button Move = new Button("Move To");
        Button Back = new Button("Back");

        Add.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                    AddTagScene.display();
                }
            }
        );
        Delete.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        DeleteTagScene.display();
                    }
                }
        );
//        Select.setOnAction(
//                new EventHandler<ActionEvent>() {
//                    @Override
//                    public void handle(final ActionEvent e) {
//                        SelectTagScene.display();
//                    }
//                }
//        );
//        Move.setOnAction(
//                new EventHandler<ActionEvent>() {
//                    @Override
//                    public void handle(final ActionEvent e) {
//                        MoveImageScene.display();
//                    }
//                }
//        );
        Back.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        FileChooserScene.display();
                    }
                }
        );


        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(Add, Delete, Select, Move, Back, imageView);
        layout1.setAlignment(Pos.CENTER);
        Scene general = new Scene(layout1, 200, 300);
        window.setScene(general);
        window.show();


    }

    public static void setImage(Image image){
        img = image;
        imageView = new ImageView(img);
        imageView.setFitHeight(600);
        imageView.setFitWidth(500);
        imageView.setPreserveRatio(true);
    }

    public static void setFile(ImageFile imageFile){
        imgFile = imageFile;
    }
}

