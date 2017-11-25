package view_control;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import model.ImageFile;
import model.ImageFileManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ManipulationManagerScene extends Application {

    /** Initialize a new ImageFile Object */
    private static ImageFile imgFile;

    /** Initialize a new ImageView Object */
    private static ImageView imageView = new ImageView();

    /** Initialize a new Scene to display the log text */
    private static Scene logTextScene;

    /** Initialize a logListView for the logTextScene to display the log text */
    private static ListView<String> logListView = new ListView<>();

    private static GridPane inputGridPane = new GridPane();

    private static VBox generalLayout = new VBox(20);

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

    /** Magic number 2 */
    private static final int MAGIC2 = 2;

    /** Magic number 4 */
    private static final int MAGIC4 = 4;

    /** Magic number 6 */
    private static final int MAGIC6 = 6;

    /** Magic number 8 */
    private static final int MAGIC8 = 8;

    private static Label path = new Label();

    static ArrayList<ImageFile> imgFiles = new ArrayList<>();

    private static ListView<String> imgListView = new ListView<>();

    private static Stage window = new Stage();

    public static void main(String[] args) {
        Application.launch(args);
    }

    /** Display the Scene and construct the buttons. */
    static void display() {
        window.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        window.setTitle("Image Editor");
        Button add = new Button("Add New Tag");
        add.setMinWidth(MAGIC120);
        Button delete = new Button("Delete Tag");
        delete.setMinWidth(MAGIC120);
        Button selectOldTag = new Button("Select Old Tag");
        selectOldTag.setMinWidth(MAGIC120);
        Button move = new Button("Move To");
        move.setMinWidth(MAGIC120);
        Button quit = new Button("Back");
        quit.setMinWidth(MAGIC120);
        Button rename = new Button("Rename");
        rename.setMinWidth(MAGIC120);
        Button getLog = new Button("Get Log History");
        getLog.setMinWidth(MAGIC120);
        Button goBack = new Button("Go Back");
        goBack.setMinWidth(MAGIC120);
        Button openButton = new Button("Choose A directory");
        openButton.setMinWidth(MAGIC120);

        Button selectImgFile = new Button("Select");
        openButton.setMinWidth(MAGIC120);
        selectImgFile.setMinWidth(MAGIC120);

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
        selectOldTag.setOnAction(
                e -> {
                    SelectTagScene.setImageFile(imgFile);
                    SelectTagScene.display();
                });
        move.setOnAction(
                e -> {
                    MoveFileScene.setImageFile(imgFile);
                    MoveFileScene.display();
                });

        quit.setOnAction(event -> window.close());

        rename.setOnAction(
                e -> {
                    FileRenameScene.setImageFile(imgFile);
                    FileRenameScene.display();
                });

        logLayout.getChildren().add(goBack);

        getLog.setOnAction(
                e -> {
                    logLayout.getChildren().remove(logListView);
                    logListView.getItems().clear();

                    if (imgFile != null) {
                        for (String logHistory : imgFile.getLog()) {
                            logListView.getItems().add(logHistory);
                        }
                    }
                    logLayout.getChildren().add(logListView);
                    window.setScene(logTextScene);
                });

        openButton.setOnAction(
                e -> {
                    ArrayList<ImageFile> directoryImageFile = new ArrayList<>();
                    DirectoryChooser directoryChooser = new DirectoryChooser();

                    File selectedDirectory = directoryChooser.showDialog(window);
                    String serPath = "./serializedImageFiles.ser";
                    try {
                        ImageFileManager imageFileManager = new ImageFileManager(serPath);
                    } catch (ClassNotFoundException | IOException e1) {
                        e1.printStackTrace();
                    }
                    if (selectedDirectory != null) {
                        for (File file : selectedDirectory.listFiles()) {
                            //                System.out.println(file.getName());
                            if (file.getName().toLowerCase().endsWith(".jpg")
                                    || file.getName().toLowerCase().endsWith(".jpeg")
                                    || file.getName().toLowerCase().endsWith(".gif")
                                    || file.getName().toLowerCase().endsWith(".png")) {
                                //                System.out.println(file.getName());
                                    boolean checkFileExist = false;
                                    ImageFile inputFile = null;
                                    try {
                                        inputFile = new ImageFile(file);
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                    ArrayList<ImageFile> imageFiles = ImageFileManager.getImageFileList();

                                    for (ImageFile imgFile : imageFiles) {
                                        if (imgFile.equals(inputFile)) {
                                            inputFile = imgFile;
                                            checkFileExist = true;
                                        }
                                    }
                                    if (!checkFileExist) {
                                        try {
                                            ImageFileManager.add(inputFile);
                                        } catch (IOException e1) {
                                            e1.printStackTrace();
                                        }
                                    }
                                directoryImageFile.add(inputFile);
                            }
                            imgFiles = directoryImageFile;
                            setImageListView(imgFiles);
                        }
                    }
                });

        selectImgFile.setOnAction(event -> {
            try {
                buttonClicked();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        //        VBox generalLayout = new VBox(MAGIC20);
        //        final GridPane inputGridPane = new GridPane();
        GridPane.setConstraints(add, 3, 0);
        GridPane.setConstraints(delete, 3, 1);
        GridPane.setConstraints(selectOldTag, 3, 2);
        GridPane.setConstraints(rename, 3, 3);
        GridPane.setConstraints(move, 3, 4);
        GridPane.setConstraints(getLog, 3, 5);
        GridPane.setConstraints(openButton, 0, 0);
        GridPane.setConstraints(selectImgFile, 0, 2);
        GridPane.setConstraints(imgListView, 0, 1);
        GridPane.setConstraints(imageView, 4, 4);

        inputGridPane.setHgap(MAGIC10);
        inputGridPane.setVgap(MAGIC10);
        inputGridPane
                .getChildren()
                .addAll(
                        add,
                        delete,
                        selectOldTag,
                        rename,
                        move,
                        getLog,
                        openButton,
                        selectImgFile,
                        imgListView,
                        imageView);

        final Pane rootGroup = new VBox(MAGIC20);
        rootGroup.getChildren().addAll(inputGridPane);
        rootGroup.setPadding(new Insets(MAGIC15, MAGIC15, MAGIC15, MAGIC15));
        generalLayout.getChildren().addAll(rootGroup, quit);
        generalLayout.setAlignment(Pos.CENTER);
        Scene general = new Scene(generalLayout, 800, 800);
        goBack.setOnAction(event -> window.setScene(general));

        window.setScene(general);
        window.show();
    }

    /** Get the Image that user selected and show it onto the scene. */
    private static void setImage() {
        inputGridPane.getChildren().remove(imageView);
        Image img = new Image(imgFile.getFile().toURI().toString());
        imageView = new ImageView(img);
        imageView.setFitHeight(MAGIC300);
        imageView.setFitWidth(MAGIC300);
        inputGridPane.getChildren().add(imageView);
        GridPane.setConstraints(imageView, 4, 4);
    }

    /**
     * Pass in the file
     *
     * @param imageFile the
     */
    private static void setFile(ImageFile imageFile) throws IOException {
        inputGridPane.getChildren().remove(path);
        imgFile = imageFile;
        path.setText(imgFile.getFile().getAbsolutePath());
        inputGridPane.getChildren().add(path);
        GridPane.setConstraints(path, 5, 4);
    }

    private static void buttonClicked() throws IOException {
        ObservableList<String> names;
        names = imgListView.getSelectionModel().getSelectedItems();
        String selectedFile = "";
        for (String name : names) {
            selectedFile = name;
        }

        if (!selectedFile.equals("")) {
            for (ImageFile imageFile : imgFiles) {
                if (imageFile.getFile().getName().equals(selectedFile)) {
                    setFile(imageFile);
                    setImage();
                }
            }
        }
    }

    static void setImageListView(ArrayList<ImageFile> imgList) {
        inputGridPane.getChildren().remove(imgListView);
        imgListView.getItems().clear();
        for (ImageFile file : imgList) {
            imgListView.getItems().add(file.getFile().getName());
        }
        inputGridPane.getChildren().add(imgListView);

        inputGridPane.getChildren().remove(path);
        if(imgFile != null) {
            path.setText(imgFile.getFile().getAbsolutePath());
            inputGridPane.getChildren().add(path);
            GridPane.setConstraints(path, 5, 4);
        }
    }
}