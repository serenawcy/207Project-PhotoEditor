package view_control;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.ImageFile;



import java.io.File;
import java.io.IOException;


/**
 * A manipulation scene than contains buttons for the user to manipulate the selected image.
 */
public class ManipulationManagerScene{

    private static Image img;
    private static ImageFile imgFile;
    private static ImageView imageView;
    private static Text logHistory = new Text();
    private static Scene logTextScene;


    /**
     * Display the Scene and construct the buttons.
     */
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
        Button getLog = new Button("Get Log History");
        getLog.setMinWidth(120);
        Button goBack = new Button("Go Back");
        goBack.setMinWidth(120);

        VBox logLayout = new VBox(20);
        logLayout.getChildren().add(goBack);
        logTextScene = new Scene(logLayout, 600, 300);
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
                    if (ImageFile.isLogFileExist()) {
                        try {
                            logHistory = new Text(ImageFile.getLog());
                            logLayout.getChildren().add(logHistory);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
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
    public static void getImage(){
        img = imgFile.getImage();
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

