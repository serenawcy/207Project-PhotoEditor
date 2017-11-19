package view_control;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.ImageFile;
import java.io.File;
import javafx.scene.control.ScrollBar;

import javax.swing.*;
import java.io.IOException;


/**
 * A manipulation scene than contains buttons for the user to manipulate the selected image.
 */
public class ManipulationManagerScene{

    private static ImageFile imgFile;
    private static ImageView imageView;
    private static Text logHistory = new Text();
    private static Scene logTextScene;


    /**
     * Display the Scene and construct the buttons.
     */
    static void display() {
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
        Button getLog = new Button("Get Log History");
        getLog.setMinWidth(120);
        Button goBack = new Button("Go Back");
        goBack.setMinWidth(120);

        VBox logLayout = new VBox(20);
        logTextScene = new Scene(logLayout, 600, 300);

//        ScrollBar sc = new ScrollBar();
//        sc.setLayoutX(logTextScene.getWidth() - sc.getWidth());
//        sc.setMin(0);
//        sc.setOrientation(Orientation.VERTICAL);
//        sc.setPrefHeight(180);
//        sc.setMax(300);
//        sc.setUnitIncrement(5.0);
//        sc.setBlockIncrement(20.0);
//        sc.valueProperty().addListener((ov, old_val, new_val) -> {
//            logLayout.setLayoutY(-new_val.doubleValue());
//        });
//
//        logLayout.getChildren().addAll(goBack,sc);


//        logLayout.getChildren().addAll(goBack);
//        ScrollPane scrollPane = new ScrollPane(logLayout);
//        scrollPane.setFitToHeight(true);
//        ScrollBar s1 = new ScrollBar();
//        s1.setOrientation(Orientation.VERTICAL);
//        logLayout.getChildren().addAll(goBack, logHistory);
        logLayout.getChildren().addAll(goBack);
//        hbar.addAdjustmentListener(new MyAdjustmentListener());
//        vbar.addAdjustmentListener(new MyAdjustmentListener());

        add.setOnAction(
                e -> {
                    AddTagScene.setImageFile(imgFile);
                    AddTagScene.display();
                    }
        );

        delete.setOnAction(
                e -> {
                    DeleteTagScene.setImageFile(imgFile);
                    DeleteTagScene.display();
                }
        );
        select.setOnAction(
                e -> {
                    SelectTagScene.setImageFile(imgFile);
                    SelectTagScene.display();
                }
        );
        move.setOnAction(
                e -> {
                    MoveFileScene.setImageFile(imgFile);
                    MoveFileScene.display();
                }
        );
        back.setOnAction(
                e -> {
                    FileChooserScene.display();
                    window.close();
                }
        );
        rename.setOnAction(
                e -> {
                    FileRenameScene.setImageFile(imgFile);
                    FileRenameScene.display();
                }
        );

        getLog.setOnAction(
                e -> {
                    logHistory = new Text(imgFile.getLog());
                    logLayout.getChildren().add(logHistory);
                    window.setScene(logTextScene);
                });


        VBox layout1 = new VBox(20);
        getImage();
        layout1.getChildren().addAll(add, delete, select, move, rename, getLog, back, imageView);
        layout1.setAlignment(Pos.CENTER);
        Scene general = new Scene(layout1, 400, 600);
        goBack.setOnAction(event ->  window.setScene(general));

        window.setScene(general);
        window.show();
    }

    /**
     * Get the Image that user selected and show it onto the scene.
     */

    // 这里的image parameter 可以去掉了  PASS IN 给我一个IMAGEFILE -> getimage method
    private static void getImage(){
        Image img = ImageFile.getImage();
        imageView = new ImageView(img);
        imageView.setFitHeight(300);
        imageView.setFitWidth(300);
 //       imageView.setPreserveRatio(true);
    }

    /**
     * Pass in the file
     * @param imageFile the
     */
    public static void setFile(ImageFile imageFile){
        imgFile = imageFile;
    }
}

