package view_control;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.TriangleMesh;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import model.ImageFile;
import model.ImageFileManager;
import model.LogManager;
import model.TagManager;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static java.awt.Color.RED;
import static java.awt.Color.red;

public class ManipulationManagerScene extends Application {

    /** Initialize a new ImageFile Object */
    private static ImageFile inputFile;

    /** Initialize a new ImageView Object */
    private static ImageView imageView = new ImageView();

    /** Initialize a new Scene to display the log text */
    private static Scene logTextScene;

    private static Scene allLogTextScene;

    /** Initialize a logListView for the logTextScene to display the log text */
    private static ListView<String> logListView = new ListView<>();

    private static ListView<String> allLogListView = new ListView<>();

    private static BorderPane inputGridPane = new BorderPane();

    private static VBox generalLayout = new VBox(20);

    /** Magic number 10 */
    private static final int MAGIC10 = 10;

    /** Magic number 50 */
    private static final int MAGIC50 = 50;

    /** Magic number 1000 */
    private static final int MAGIC1000 = 1000;

    /** Magic number 20 */
    private static final int MAGIC20 = 20;

    /** Magic number 200 */
    private static final int MAGIC200 = 200;

    /** Magic number 30 */
    private static final int MAGIC30 = 30;

    /** Magic number 800 */
    private static final int MAGIC800 = 800;

    /** Magic number 650 */
    private static final int MAGIC650 = 650;

    /** Magic number 120 */
    private static final int MAGIC120 = 120;

    /** Magic number 250 */
    private static final int MAGIC250 = 250;

    /** Magic number 550 */
    private static final int MAGIC550 = 550;

    /** Magic number 500 */
    private static final int MAGIC500 = 500;

    /** Magic number 1350 */
    private static final int MAGIC1350 = 1350;

    private static Label path = new Label();

    static ArrayList<ImageFile> imgFiles = new ArrayList<>();

    private static ListView<String> imgListView = new ListView<>();

    private static Stage window = new Stage();

    private static StackPane paneCenter = new StackPane();

    private static ListView<String> tagsView = new ListView<>();

    private static Pane pathArea = new Pane();

    static String logManagerPath = "./serializedLogFiles.ser";

    static String tagManagerPath = "./serializedTagFiles.ser";

    static String imageFileManagerPath = "./serializedImageFiles.ser";

    static TagManager tagManager;

    static LogManager logManager;

    static ImageFileManager imageFileManager;

    static {
        try {
            tagManager = new TagManager(tagManagerPath);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    static {
        try {
            imageFileManager = new ImageFileManager(imageFileManagerPath);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    static {
        try {
            logManager = new LogManager(logManagerPath);
            logManager.add(" ", logManagerPath);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        Application.launch(args);
    }


    /** Display the Scene and construct the buttons. */
    @Override
    public void start(Stage primaryStage) throws Exception {

        window.setTitle("Image Editor");

        Button add = new Button("Add Tag");
        add.setMinWidth(100);
//        BackgroundImage backgroundImage = new BackgroundImage( new Image( getClass().getResource("/icon/add.jpeg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
//        Background background = new Background(backgroundImage);
//        add.setBackground(background);



        Button delete = new Button("Delete Tag");
        delete.setMinWidth(100);

        Button selectOldTag = new Button("Select Old Tag");
        selectOldTag.setMinWidth(100);

        Button move = new Button("Move To");
        move.setMinWidth(100);

        Button quit = new Button("Quit The Program");
        quit.setMinWidth(150);

        Button rename = new Button("Rename");
        rename.setMinWidth(100);

        Button getLog = new Button("Get Log History");
        getLog.setMinWidth(150);

        Button goBack = new Button("Go Back");
        goBack.setMinWidth(100);

        Button back = new Button("Go Back");
        back.setMinWidth(100);

        Button openButton = new Button("Choose A Directory");
        openButton.setMinWidth(100);

        // ***************** NEW FEATURE'S BUTTON *****************
        Button imgContainTag = new Button("Show Image with Tag");
        imgContainTag.setMinWidth(150);
        // ***************** NEW FEATURE'S BUTTON *****************


        Button deleteTagHistory = new Button("Remove From History");
        deleteTagHistory.setMinWidth(150);


        Pane addToTagSet = new Pane();
        addToTagSet.setMinHeight(20);
        addToTagSet.setMinWidth(20);
        addToTagSet.setMaxWidth(20);
        addToTagSet.setMaxHeight(20);
        Line line1 = new Line(5,10,15,10);
        line1.setStrokeWidth(3);
        Line line2 = new Line(10,5,10,15);
        line2.setStrokeWidth(3);
        addToTagSet.getChildren().addAll(line1,line2);

//        addToTagSet.setStyle("-fx-background-color:#fff8dc");



//        Button addToTagSet = new Button("+");
//        addToTagSet.setMinWidth(10);
//        BackgroundImage backgroundImage = new BackgroundImage( new Image( getClass().getResource("/test/dogTEST.jpeg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
//        Background background = new Background(backgroundImage);
//        addToTagSet.setBackground(background);

        Button addToImageFile = new Button("Add to Image File");
        addToImageFile.setMinWidth(150);


        Button getAllLog = new Button(("Get All Log History"));

        setTagSetView();

        VBox logLayout = new VBox(MAGIC20);
        logTextScene = new Scene(logLayout, 1349, 1000);

        VBox allLogLayout = new VBox(MAGIC20);
        allLogTextScene = new Scene(allLogLayout, 1349,1000);

        tagsView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        add.setOnAction(
                (ActionEvent e) -> {
                    AddTagScene.setImageFile(inputFile);
                    AddTagScene.display();
                });

        delete.setOnAction(
                (ActionEvent e) -> {
                    DeleteTagScene.setImageFile(inputFile);
                    DeleteTagScene.display();
                });
        selectOldTag.setOnAction(
                (ActionEvent e) -> {
                    SelectTagScene.setImageFile(inputFile);
                    SelectTagScene.display();
                });
        move.setOnAction(
                (ActionEvent e) -> {
                    MoveFileScene.setImageFile(inputFile);
                    MoveFileScene.display();
                });

        quit.setOnAction((ActionEvent event) -> window.close());

        rename.setOnAction(
                (ActionEvent e) -> {
                    FileRenameScene.setImageFile(inputFile);
                    FileRenameScene.display();
                });

        logLayout.getChildren().add(goBack);
        allLogLayout.getChildren().add(back);

        getLog.setOnAction(
                (ActionEvent e) -> {
                    logLayout.getChildren().remove(logListView);
                    logListView.getItems().clear();

                    if (inputFile != null) {
                        for (String logHistory : inputFile.getLog()) {
                            logListView.getItems().add(logHistory);
                        }
                    }
                    logLayout.getChildren().add(logListView);
                    window.setScene(logTextScene);
                });

        getAllLog.setOnAction(
                (ActionEvent e) -> {
                    allLogLayout.getChildren().remove(allLogListView);
                    allLogListView.getItems().clear();
                    for (String allLogHistory : logManager.getLogHistory()) {
                        allLogListView.getItems().add(allLogHistory);
                    }
                    allLogLayout.getChildren().add(allLogListView);
                    window.setScene(allLogTextScene);
                });

        openButton.setOnAction(
                (ActionEvent e) -> {
                    ArrayList<ImageFile> directoryImageFile = new ArrayList<>();
                    DirectoryChooser directoryChooser = new DirectoryChooser();

                    File selectedDirectory = directoryChooser.showDialog(window);
                    if (selectedDirectory != null) {
                        for (File file : selectedDirectory.listFiles()) {
                            if (file.getName().toLowerCase().endsWith(".jpg")
                                    || file.getName().toLowerCase().endsWith(".jpeg")
                                    || file.getName().toLowerCase().endsWith(".gif")
                                    || file.getName().toLowerCase().endsWith(".png")) {
                                boolean checkFileExist = false;
                                ImageFile inputImageFile = null;
                                try {
                                    inputImageFile = new ImageFile(file);
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                                ArrayList<ImageFile> imageFiles = imageFileManager.getSerializedList();

                                for (ImageFile imgFile : imageFiles) {
                                    if (imgFile.equals(inputImageFile)) {
                                        ArrayList<String> currentTagList = tagManager.getSerializedList();
                                        ArrayList<String> existTagList = imgFile.getExistTag();
                                        for (String tag: existTagList){
                                            if (!currentTagList.contains(tag)){
                                                try {
                                                    tagManager.add(tag, tagManagerPath);
                                                } catch (IOException e1) {
                                                    e1.printStackTrace();
                                                }
                                            }
                                        }
                                        inputImageFile = imgFile;
                                        checkFileExist = true;
                                    }
                                }
                                if (!checkFileExist) {
                                    try {
                                        ArrayList<String> autoAddTags= inputImageFile.getExistTag();
                                        for (String tag: autoAddTags){
                                            tagManager.add(tag, tagManagerPath);
                                        }
                                        imageFileManager.add(inputImageFile, imageFileManagerPath);
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }
                                directoryImageFile.add(inputImageFile);
                            }
                            imgFiles = directoryImageFile;
                            setImageListView(imgFiles);
                        }
                    }
                    setTagSetView();
                });

        deleteTagHistory.setOnAction((ActionEvent event) -> {
            try {
                deleteTagHistoryButtonClicked();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        addToTagSet.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                AddToTagSet.display();
            }
            });

        addToImageFile.setOnAction(event -> {
            try {
                addTagToFileButtonClicked();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // ***************** NEW FEATURE'S ACTION *****************
        imgContainTag.setOnAction(
                event -> {
                    ObservableList<String> chooseTags = tagsView.getSelectionModel().getSelectedItems();
                    ArrayList<ImageFile> chooseFile = new ArrayList<>();

                    if (chooseTags.size() >= 1) {
                        for (String chooseTag: chooseTags) {
                            for (ImageFile file : imgFiles) {
                                if (file.getExistTag().contains(chooseTag)) {
                                    chooseFile.add(file);
                                }
                            }
                        }
                        ContainTagScene.setImageFilesWithTags(chooseFile);
                        ContainTagScene.setImage(null);
//                        System.out.println(chooseFile.size());
                        ContainTagScene.display();
                    }
                });

        // ***************** NEW FEATURE'S ACTION *****************


        inputGridPane.setPrefSize(MAGIC800,MAGIC800);
        Label tagHistory = new Label("Tag History");
        pathArea.setPrefSize(MAGIC200,MAGIC30);
        pathArea.getChildren().add(path);
//        pathArea.setTranslateX(250);
        ToolBar toolbar = new ToolBar();
        toolbar.getItems().addAll(openButton, getAllLog,imgContainTag,tagHistory,addToTagSet);
        imgContainTag.setTranslateX(600);
        tagHistory.setTranslateX(680);
        addToTagSet.setTranslateX(700);
        addToTagSet.setTranslateY(1);
        ToolBar toolbarBottom = new ToolBar();
        toolbarBottom.setMinHeight(100);
        toolbarBottom.setPadding(new Insets(0,0,10,10));



        FlowPane divisionBottom = new FlowPane();
        divisionBottom.setMaxWidth(100);
//        FlowPane divisionBottomRight = new FlowPane();
//        divisionBottomRight.setMaxWidth(5);

        toolbarBottom.getItems().addAll(quit, getLog, add, delete, selectOldTag,rename,move,
                deleteTagHistory, addToImageFile);

//        add.setTranslateX(120);
//        delete.setTranslateX(140);
//        selectOldTag.setTranslateX(160);
//        rename.setTranslateX(180);
//        move.setTranslateX(200);
//        addToTagSet.setTranslateX(220);
//        deleteTagHistory.setTranslateX(300);
//        addToImageFile.setTranslateX(300);
        quit.setTranslateX(0);
        quit.setTranslateY(20);
        getLog.setTranslateX(-152);
        getLog.setTranslateY(-30);
        add.setTranslateX(-70);
        add.setTranslateY(-30);
        delete.setTranslateX(-30);
        delete.setTranslateY(-30);
        selectOldTag.setTranslateX(10);
        selectOldTag.setTranslateY(-30);
        rename.setTranslateX(50);
        rename.setTranslateY(-30);
        move.setTranslateX(90);
        move.setTranslateY(-30);
 //       addToTagSet.setTranslateY(-30);
        deleteTagHistory.setTranslateX(250);
        deleteTagHistory.setTranslateY(20);
        addToImageFile.setTranslateX(95);
        addToImageFile.setTranslateY(-30);


        paneCenter.setStyle("-fx-background-color: #f5f5dc");
        inputGridPane.setCenter(paneCenter);
        inputGridPane.setTop(toolbar);
        inputGridPane.setLeft(imgListView);
        inputGridPane.setRight(tagsView);
        inputGridPane.setBottom(toolbarBottom);
        inputGridPane.getChildren().add(tagHistory);
       // Pane bottomSelection = new Pane();
       // bottomSelection.getChildren().addAll(quit, imgContainTag);

        generalLayout.getChildren().addAll(inputGridPane);

        final Scene general = new Scene(generalLayout, 1350, 950);
        goBack.setOnAction((ActionEvent event) -> window.setScene(general));
        back.setOnAction((ActionEvent event) -> window.setScene(general));

        window.setScene(general);
        window.show();
    }

    /** Get the Image that user selected and show it onto the scene. */
   static void setImage() {
        paneCenter.getChildren().remove(imageView);
        if (inputFile != null){
        Image img = new Image(inputFile.getFile().toURI().toString());
        imageView = new ImageView(img);
        imageView.setFitHeight(400);
        imageView.setFitWidth(400);

        paneCenter.getChildren().add(imageView);
        StackPane.setMargin(imageView,new Insets(MAGIC50,MAGIC10,MAGIC50,MAGIC50));
    }
   }

    /**
     * Pass in the file
     *
     * @param imageFile the
     */
    static void setFile(ImageFile imageFile) throws IOException {
        //       inputGridPane.getChildren().remove(path);
        inputFile = imageFile;
//        path.setText(inputFile.getFile().getAbsolutePath());
//        for (String tags: inputFile.getExistTag()){
//            tagsView.getItems().add(tags);
//        }

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
        imgListView.getItems().clear();
        for (ImageFile file : imgList) {
            imgListView.getItems().add(file.getFile().getName());
        }
        imgListView.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                try {
                    buttonClicked();
                    if (inputFile != null){
                        setPath(inputFile);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        }

//        if(inputFile != null) {
//            path.setText(inputFile.getFile().getAbsolutePath());
//            tagsView.getItems().clear();
//            for (String tags: inputFile.getExistTag()){
//                tagsView.getItems().add(tags);
//            }
//        }


    static void setPath(ImageFile inputFile) {
        pathArea.getChildren().clear();
        if (inputFile != null) {
            path.setText(ManipulationManagerScene.inputFile.getFile().getAbsolutePath());
            pathArea.getChildren().add(path);
            paneCenter.getChildren().remove(pathArea);
            paneCenter.getChildren().add(pathArea);

        }

    }

     static void setTagSetView(){
//        inputGridPane.getChildren().remove(tagsView);
        tagsView.getItems().clear();
        ArrayList<String> tagHistory = tagManager.getSerializedList();
        for (String tag: tagHistory){
            tagsView.getItems().add(tag);
        }
//        inputGridPane.getChildren().add(tagsView);
    }

    private void deleteTagHistoryButtonClicked() throws IOException {
        ObservableList<String> tags = tagsView.getSelectionModel().getSelectedItems();
//        if(tags.size() >= 1) {
//            String name = tags.get(0);
//            tagManager.delete(name, tagManagerPath);
//            setTagSetView();
//        }
        for (String tag: tags){
            tagManager.delete(tag, tagManagerPath);
        }
        setTagSetView();
        }

    private void addTagToFileButtonClicked() throws IOException {

//        String temp = "";
        ObservableList<String> tags = tagsView.getSelectionModel().getSelectedItems();
        if(tags.size() >= 1 && inputFile != null){
            for (String tag : tags){
                ImageFile saveCurrent = inputFile;
                String logHistory = inputFile.addTag(tag);
                imageFileManager.add(inputFile, imageFileManagerPath);
                imageFileManager.delete(saveCurrent, imageFileManagerPath);
                logManager.add(logHistory,logManagerPath);
//                temp += tag + "\n";
//                System.out.println(tag);
            }
//      System.out.println(temp);

//            String tag = tags.get(0);
//            inputFile.addTag(tag);
            setImageListView(imgFiles);
        }
    }

}