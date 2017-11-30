package view_control;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.ImageFile;
import model.ImageFileManager;
import model.LogManager;
import model.TagManager;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class ManipulationManagerScene extends Application {

    private ArrayList<ImageFile> directoryImageFile = new ArrayList<>();


    /**
     * Initialize a new ImageFile Object
     */
    private static ImageFile inputFile;

    /**
     * Initialize a new ImageView Object
     */
    private static ImageView imageView = new ImageView();

    /**
     * Initialize a new Scene to display the selected image's log text
     */
    private static Scene logTextScene;

    /**
     * Initialize a new Scene to display all log text
     */
    private static Scene allLogTextScene;

    /**
     * Initialize a logListView for the logTextScene to display the selected image's log text
     */
    private static ListView<String> logListView = new ListView<>();

    /**
     * Initialize an allLogListView for the allLogTextScene to display all log text
     */
    private static ListView<String> allLogListView = new ListView<>();

    /**
     * Initialize an inputGridPane to format the layout of manipulation scene
     */
    private static BorderPane inputGridPane = new BorderPane();

    /**
     * Initialize an generalLayout to place all elements and buttons
     */
    private static VBox generalLayout = new VBox(20);

    private static File currentDirectory;

    /**
     * Magic number 10
     */
    private static final int MAGIC10 = 10;

    /**
     * Magic number 0
     */
    private static final int MAGIC0 = 0;

    /**
     * Magic number 1
     */
    private static final int MAGIC1 = 1;

    /**
     * Magic number 600
     */
    private static final int MAGIC600 = 600;

    /**
     * Magic number 680
     */
    private static final int MAGIC680 = 680;

    /**Magic number 700*/
    private static final int MAGIC700 = 700;

    /**
     * Magic number 50
     */
    private static final int MAGIC50 = 50;

    /**
     * Magic number 20
     */
    private static final int MAGIC20 = 20;

    /**
     * Magic number 200
     */
    private static final int MAGIC200 = 200;

    /**
     * Magic number 250
     */
    private static final int MAGIC250 = 250;

    /**
     * Magic number 30
     */
    private static final int MAGIC30 = 30;

    /**
     * Magic number 70
     */
    private static final int MAGIC70 = 70;

    /**
     * Magic number 90
     */
    private static final int MAGIC90 = 90;

    /**
     * Magic number 95
     */
    private static final int MAGIC95 = 95;

    /**
     * Magic number 800
     */
    private static final int MAGIC800 = 800;

    /**
     * Magic number 950
     */
    private static final int MAGIC950 = 950;

    /**
     * Magic number 100
     */
    private static final int MAGIC100 = 100;

    /**
     * Magic number 150
     */
    private static final int MAGIC150 = 150;

    /**
     * Magic number 152
     */
    private static final int MAGIC152 = 152;

    /**
     * Magic number 5
     */
    private static final int MAGIC5 = 5;

    /**
     * Magic number 15
     */
    private static final int MAGIC15 = 15;

    /**
     * Magic number 3
     */
    private static final int MAGIC3 = 3;

    /**
     * Magic number 1000
     */
    private static final int MAGIC1000 = 1000;

    /**
     * Magic number 1349
     */
    private static final int MAGIC1349 = 1349;

    /**
     * Magic number 1350
     */
    private static final int MAGIC1350 = 1350;

    /**
     * Magic number 400
     */
    private static final int MAGIC400 = 400;

    /**
     * Initialize a new label for the path of the selected image
     */
    private static Label path = new Label();

    /**
     * Initialize a imgFiles that contains the collection of ImageFile under directory
     */
    static ArrayList<ImageFile> imgFiles = new ArrayList<>();

    /**
     * Initialize a imgListView for the to display imgFiles
     */
    private static ListView<String> imgListView = new ListView<>();

    /**
     * Initialize a Stage window for this scene
     */
    private static Stage window = new Stage();

    /**
     * Initialize a StackPane paneCenter to place the imageView of the selected image
     */
    private static StackPane paneCenter = new StackPane();

    /**
     * Initialize a tagsView to place the imageView of the selected file
     */
    private static ListView<String> tagsView = new ListView<>();

    /**
     * Initialize a pathArea to place the path of the selected file
     */
    private static Pane pathArea = new Pane();

    /**
     * Path to serialize the logManager
     */
    static String logManagerPath = "./serializedLogFiles.ser";

    /**
     * Path to serialize the tagManager
     */
    static String tagManagerPath = "./serializedTagFiles.ser";

    /**
     * Path to serialize the imageFileManager
     */
    static String imageFileManagerPath = "./serializedImageFiles.ser";

    /**
     * A tagManager object
     */
    static TagManager tagManager;

    /**
     * A  LogManager object
     */
    static LogManager logManager;

    /**
     * A  imageFileManager object
     */
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


    /**
     * Display the Scene and construct the buttons.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        window.setTitle("Image Editor");

        Button add = new Button("Add Tag");
        add.setMinWidth(MAGIC100);

        Button delete = new Button("Delete Tag");
        delete.setMinWidth(MAGIC100);

        Button selectOldTag = new Button("Select Old Tag");
        selectOldTag.setMinWidth(MAGIC100);

        Button move = new Button("Move To");
        move.setMinWidth(MAGIC100);

        Button quit = new Button("Quit The Program");
        quit.setMinWidth(MAGIC100);

        Button rename = new Button("Rename");
        rename.setMinWidth(MAGIC100);

        Button getLog = new Button("Get Log History");
        getLog.setMinWidth(MAGIC150);

        Button goBack = new Button("Go Back");
        goBack.setMinWidth(MAGIC100);

        Button back = new Button("Go Back");
        back.setMinWidth(MAGIC100);

        Button openButton = new Button("Choose A Directory");
        openButton.setMinWidth(MAGIC100);

        // ***************** NEW FEATURE'S BUTTON *****************
        Button imgContainTag = new Button("Show Image with Tag");
        imgContainTag.setMinWidth(MAGIC150);
        // ***************** NEW FEATURE'S BUTTON *****************


        Button deleteTagHistory = new Button("Remove From History");
        deleteTagHistory.setMinWidth(MAGIC150);


        Pane addToTagSet = new Pane();
        addToTagSet.setMinHeight(MAGIC20);
        addToTagSet.setMinWidth(MAGIC20);
        addToTagSet.setMaxWidth(MAGIC20);
        addToTagSet.setMaxHeight(MAGIC20);
        Line line1 = new Line(MAGIC5, MAGIC10, MAGIC15, MAGIC10);
        line1.setStrokeWidth(MAGIC3);
        Line line2 = new Line(MAGIC10, MAGIC5, MAGIC10, MAGIC15);
        line2.setStrokeWidth(MAGIC3);
        addToTagSet.getChildren().addAll(line1, line2);

        Button addToImageFile = new Button("Add to Image File");
        addToImageFile.setMinWidth(MAGIC150);


        Button getAllLog = new Button(("Get All Log History"));

        setTagSetView();

        VBox logLayout = new VBox(MAGIC20);
        logTextScene = new Scene(logLayout, MAGIC1349, MAGIC1000);

        VBox allLogLayout = new VBox(MAGIC20);
        allLogTextScene = new Scene(allLogLayout, MAGIC1349, MAGIC1000);

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
                    directoryImageFile.clear();
                    currentDirectory = null;
                    openButtonClicked();
                    setTagSetView();
                });

        deleteTagHistory.setOnAction((ActionEvent event) -> {
            try {
                deleteTagHistoryButtonClicked();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        addToTagSet.setOnMouseClicked(event -> AddToTagSet.display());

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
                        for (String chooseTag : chooseTags) {
                            for (ImageFile file : imgFiles) {
                                if (file.getExistTag().contains(chooseTag) && !(chooseFile.contains(file))) {
                                    chooseFile.add(file);
                                }
                            }
                        }
                        ContainTagScene.setImageFilesWithTags(chooseFile);
                        ContainTagScene.setImage(null);
                        ContainTagScene.display();
                    }
                });

        // ***************** NEW FEATURE'S ACTION *****************


        inputGridPane.setPrefSize(MAGIC800, MAGIC800);
        Label tagHistory = new Label("Tag History");
        pathArea.setPrefSize(MAGIC200, MAGIC30);
        pathArea.getChildren().add(path);
        ToolBar toolbar = new ToolBar();
        toolbar.getItems().addAll(openButton, getAllLog, imgContainTag, tagHistory, addToTagSet);
        imgContainTag.setTranslateX(MAGIC600);
        tagHistory.setTranslateX(MAGIC680);
        addToTagSet.setTranslateX(MAGIC700);
        addToTagSet.setTranslateY(MAGIC1);
        ToolBar toolbarBottom = new ToolBar();
        toolbarBottom.setMinHeight(MAGIC100);
        toolbarBottom.setPadding(new Insets(MAGIC0, MAGIC0, MAGIC10, MAGIC10));

        toolbarBottom.getItems().addAll(quit, getLog, add, delete, selectOldTag, rename, move,
                deleteTagHistory, addToImageFile);

        quit.setTranslateX(MAGIC0);
        quit.setTranslateY(MAGIC20);
        getLog.setTranslateX(-MAGIC152);
        getLog.setTranslateY(-MAGIC30);
        add.setTranslateX(-MAGIC70);
        add.setTranslateY(-MAGIC30);
        delete.setTranslateX(-MAGIC30);
        delete.setTranslateY(-MAGIC30);
        selectOldTag.setTranslateX(MAGIC10);
        selectOldTag.setTranslateY(-MAGIC30);
        rename.setTranslateX(MAGIC50);
        rename.setTranslateY(-MAGIC30);
        move.setTranslateX(MAGIC90);
        move.setTranslateY(-MAGIC30);
        deleteTagHistory.setTranslateX(MAGIC250);
        deleteTagHistory.setTranslateY(MAGIC20);
        addToImageFile.setTranslateX(MAGIC95);
        addToImageFile.setTranslateY(-MAGIC30);


        paneCenter.setStyle("-fx-background-color: #f5f5dc");
        inputGridPane.setCenter(paneCenter);
        inputGridPane.setTop(toolbar);
        inputGridPane.setLeft(imgListView);
        inputGridPane.setRight(tagsView);
        inputGridPane.setBottom(toolbarBottom);
        inputGridPane.getChildren().add(tagHistory);
        generalLayout.getChildren().addAll(inputGridPane);

        final Scene general = new Scene(generalLayout, MAGIC1350, MAGIC950);
        goBack.setOnAction((ActionEvent event) -> window.setScene(general));
        back.setOnAction((ActionEvent event) -> window.setScene(general));

        window.setScene(general);
        window.show();
    }

    private void setAction(File fileCollection) {
        for (File file : fileCollection.listFiles()) {
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
                        for (String tag : existTagList) {
                            if (!currentTagList.contains(tag)) {
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
                        ArrayList<String> autoAddTags = inputImageFile.getExistTag();
                        for (String tag : autoAddTags) {
                            tagManager.add(tag, tagManagerPath);
                        }
                        imageFileManager.add(inputImageFile, imageFileManagerPath);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                directoryImageFile.add(inputImageFile);
            } else if (file.isDirectory()) {
                setAction(file);
            }
        }
    }

    private void openButtonClicked(){
//        ArrayList<ImageFile> directoryImageFile = new ArrayList<>();
        DirectoryChooser directoryChooser = new DirectoryChooser();

        currentDirectory = directoryChooser.showDialog(window);
        if (currentDirectory != null) {
            setAction(currentDirectory);
        }
            imgFiles = directoryImageFile;
//            for (File file : selectedDirectory.listFiles()) {
//                if (file.getName().toLowerCase().endsWith(".jpg")
//                        || file.getName().toLowerCase().endsWith(".jpeg")
//                        || file.getName().toLowerCase().endsWith(".gif")
//                        || file.getName().toLowerCase().endsWith(".png")) {
//                    boolean checkFileExist = false;
//                    ImageFile inputImageFile = null;
//                    try {
//                        inputImageFile = new ImageFile(file);
//                    } catch (IOException e1) {
//                        e1.printStackTrace();
//                    }
//                    ArrayList<ImageFile> imageFiles = imageFileManager.getSerializedList();
//
//                    for (ImageFile imgFile : imageFiles) {
//                        if (imgFile.equals(inputImageFile)) {
//                            ArrayList<String> currentTagList = tagManager.getSerializedList();
//                            ArrayList<String> existTagList = imgFile.getExistTag();
//                            for (String tag : existTagList) {
//                                if (!currentTagList.contains(tag)) {
//                                    try {
//                                        tagManager.add(tag, tagManagerPath);
//                                    } catch (IOException e1) {
//                                        e1.printStackTrace();
//                                    }
//                                }
//                            }
//                            inputImageFile = imgFile;
//                            checkFileExist = true;
//                        }
//                    }
//                    if (!checkFileExist) {
//                        try {
//                            ArrayList<String> autoAddTags = inputImageFile.getExistTag();
//                            for (String tag : autoAddTags) {
//                                tagManager.add(tag, tagManagerPath);
//                            }
//                            imageFileManager.add(inputImageFile, imageFileManagerPath);
//                        } catch (IOException e1) {
//                            e1.printStackTrace();
//                        }
//                    }
//                    directoryImageFile.add(inputImageFile);
//                }
//                else if (file.isDirectory()){
//                    openButtonClicked();
//                }
//                imgFiles = directoryImageFile;
            setImageListView(imgFiles);


    }

    /**
     * Get the Image that user selected and show it onto the scene.
     */
    static void setImage() {
        paneCenter.getChildren().remove(imageView);
        if (inputFile != null) {
            Image img = new Image(inputFile.getFile().toURI().toString());
            imageView = new ImageView(img);
            imageView.setFitHeight(MAGIC400);
            imageView.setFitWidth(MAGIC400);

            paneCenter.getChildren().add(imageView);
            StackPane.setMargin(imageView, new Insets(MAGIC50, MAGIC10, MAGIC50, MAGIC50));
        }
    }

    /**
     * Pass in the file
     *
     * @param imageFile the imageFile selected
     */
    static void setFile(ImageFile imageFile) throws IOException {
        inputFile = imageFile;
    }

    private static void buttonClicked() throws IOException {
        if (imgListView.getSelectionModel().getSelectedIndices().size() == 1) {
            Integer index = imgListView.getSelectionModel().getSelectedIndices().get(0);
            setFile(imgFiles.get(index));
            setImage();
        }

    }



    /**
     * set the ImageListView to display the list of images under the selected directory.
     *
     * @param imgList: the collection of ImageFile under selected directory
     */
    static void setImageListView(ArrayList<ImageFile> imgList) {
        imgListView.getItems().clear();
        for (ImageFile file : imgList) {
            imgListView.getItems().add(file.getFile().getName());
        }
        imgListView.setOnMouseClicked(event -> {
            try {
                buttonClicked();
                if (inputFile != null) {
                    setPath(inputFile);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }


    /**
     * Set the path of the file selected to display.
     *
     * @param inputFile: the file user selected to view.
     */
    static void setPath(ImageFile inputFile) {
        pathArea.getChildren().clear();
        if (inputFile != null) {
            path.setText(ManipulationManagerScene.inputFile.getFile().getAbsolutePath());
            pathArea.getChildren().add(path);
            paneCenter.getChildren().remove(pathArea);
            paneCenter.getChildren().add(pathArea);

        }

    }


    /**
     * Set all history tag set list view
     */
    static void setTagSetView() {
        tagsView.getItems().clear();
        ArrayList<String> tagHistory = tagManager.getSerializedList();
        for (String tag : tagHistory) {
            tagsView.getItems().add(tag);
        }
    }

    /**
     * Delete the selected tag from the tag set
     *
     * @throws IOException: throw an IOException
     */
    private void deleteTagHistoryButtonClicked() throws IOException {
        ObservableList<String> tags = tagsView.getSelectionModel().getSelectedItems();
        for (String tag : tags) {
            tagManager.delete(tag, tagManagerPath);
        }
        setTagSetView();
    }

    /**
     * Add the selected tag to file
     *
     * @throws IOException: throw an IOException
     */
    private void addTagToFileButtonClicked() throws IOException {

        ObservableList<String> tags = tagsView.getSelectionModel().getSelectedItems();
        if (tags.size() >= 1 && inputFile != null) {
            for (String tag : tags) {
                ImageFile saveCurrent = inputFile;
                String logHistory = inputFile.addTag(tag);
                imageFileManager.add(inputFile, imageFileManagerPath);
                imageFileManager.delete(saveCurrent, imageFileManagerPath);
                logManager.add(logHistory, logManagerPath);
                setImageListView(imgFiles);
            }
            setPath(inputFile);
        }
    }

    static File getCurrentDirectory(){
        return currentDirectory;
    }
}
