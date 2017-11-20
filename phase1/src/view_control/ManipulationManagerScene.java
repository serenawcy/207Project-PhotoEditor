package view_control;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.ImageFile;

public class ManipulationManagerScene {

    /** Initialize a new ImageFile Object */
    private static ImageFile imgFile;

    /** Initialize a new ImageView Object */
    private static ImageView imageView;

    /** Initialize a new Scene to display the log text */
    private static Scene logTextScene;

    /** Initialize a listView for the logTextScene to display the log text */
    private static ListView<String> listView = new ListView<>();

    /** Magic number 600 */
    private static final int MAGIC600 = 600;

    /** Magic number 1000 */
    private static final int MAGIC1000 = 1000;

    /** Magic number 20 */
    private static final int MAGIC20 = 20;

    /** Magic number 400 */
    private static final int MAGIC400 = 400;

    /** Magic number 300 */
    private static final int MAGIC300 = 300;

    /** Magic number 10 */
    private static final int MAGIC10 = 10;

    /** Magic number 15 */
    private static final int MAGIC15 = 15;

    /** Magic number 120 */
    private static final int MAGIC120 = 120;

    /** Display the Scene and construct the buttons. */
    static void display() {
        Stage window = new Stage();
        window.setTitle("Image Editor");
        Button add = new Button("Add New Tag");
        add.setMinWidth(MAGIC120);
        Button delete = new Button("Delete Tag");
        delete.setMinWidth(MAGIC120);
        Button select = new Button("Select Old Tag");
        select.setMinWidth(MAGIC120);
        Button move = new Button("Move To");
        move.setMinWidth(MAGIC120);
        Button back = new Button("Back");
        back.setMinWidth(MAGIC120);
        Button rename = new Button("Rename");
        rename.setMinWidth(MAGIC120);
        Button getLog = new Button("Get Log History");
        getLog.setMinWidth(MAGIC120);
        Button goBack = new Button("Go Back");
        goBack.setMinWidth(MAGIC120);

        VBox logLayout = new VBox(MAGIC20);
        logTextScene = new Scene(logLayout, MAGIC1000, MAGIC600);

        add.setOnAction(
                e -> {
                    AddTagScene.setImageFile(imgFile);
                    AddTagScene.display();
                });

        delete.setOnAction(
                e -> {
                    DeleteTagScene.setImageFile(imgFile);
                    DeleteTagScene.display();
                });
        select.setOnAction(
                e -> {
                    SelectTagScene.setImageFile(imgFile);
                    SelectTagScene.display();
                });
        move.setOnAction(
                e -> {
                    MoveFileScene.setImageFile(imgFile);
                    MoveFileScene.display();
                });
        back.setOnAction(
                e -> {
                    FileChooserScene.display();
                    window.close();
                });
        rename.setOnAction(
                e -> {
                    FileRenameScene.setImageFile(imgFile);
                    FileRenameScene.display();
                });

        logLayout.getChildren().add(goBack);

        getLog.setOnAction(
                e -> {
                    logLayout.getChildren().remove(listView);
                    listView.getItems().clear();
                    for (String logHistory : imgFile.getLog()) {
                        listView.getItems().add(logHistory);
                    }
                    logLayout.getChildren().add(listView);
                    window.setScene(logTextScene);
                });

        VBox generalLayout = new VBox(MAGIC20);
        getImage();
        final GridPane inputGridPane = new GridPane();
        GridPane.setConstraints(add, 4, 2);
        GridPane.setConstraints(delete, 4, 4);
        GridPane.setConstraints(select, 4, 6);
        GridPane.setConstraints(rename, 8, 2);
        GridPane.setConstraints(move, 8, 4);
        GridPane.setConstraints(getLog, 8, 6);
        inputGridPane.setHgap(MAGIC10);
        inputGridPane.setVgap(MAGIC10);
        inputGridPane.getChildren().addAll(add, delete, select, rename, move, getLog);

        final Pane rootGroup = new VBox(MAGIC20);
        rootGroup.getChildren().addAll(inputGridPane);
        rootGroup.setPadding(new Insets(MAGIC15, MAGIC15, MAGIC15, MAGIC15));
        generalLayout.getChildren().addAll(rootGroup, imageView, back);
        generalLayout.setAlignment(Pos.CENTER);
        Scene general = new Scene(generalLayout, MAGIC400, MAGIC600);
        goBack.setOnAction(event -> window.setScene(general));

        window.setScene(general);
        window.show();
    }

    /** Get the Image that user selected and show it onto the scene. */
    private static void getImage() {
        Image img = ImageFile.getImage();
        imageView = new ImageView(img);
        imageView.setFitHeight(MAGIC300);
        imageView.setFitWidth(MAGIC300);
    }

    /**
     * Pass in the file
     * @param imageFile the
     */
    public static void setFile(ImageFile imageFile) {
        imgFile = imageFile;
    }
}