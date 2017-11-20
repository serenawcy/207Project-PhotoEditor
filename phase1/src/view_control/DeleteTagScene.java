package view_control;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.ListView;
import model.ImageFile;



class DeleteTagScene {

    /** Initialize an ImageFile */
    private static ImageFile inputFile;


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

    /** Display the Scene and construct the buttons. */
    static void display(){
        Stage window = new Stage();
        window.setTitle("Delete Tag(s)");
        window.initModality(Modality.APPLICATION_MODAL);
        Label label = new Label("Please check the tag(s) you want to delete");
        Button delete = new Button("Delete");
        Button back = new Button("Go back");
        delete.setMinWidth(MAGIC120);
        back.setMinWidth(MAGIC120);

        listView = new ListView<>();
        for(String tag: inputFile.getExistTag()) {
            listView.getItems().add(tag);
        }

        delete.setOnAction(e -> buttonClicked());
        back.setOnAction(e -> window.close());

        VBox layout = new VBox(MAGIC10);
        layout.setPadding(new Insets(MAGIC20, MAGIC20, MAGIC20, MAGIC20));
        layout.getChildren().addAll(label, listView, delete, back);

        Scene scene = new Scene(layout, MAGIC350, MAGIC400);
        window.setScene(scene);
        window.show();

    }

    /**
     * Delete the selected tags once button has been delete clicked.
     */

    private static void buttonClicked() {
        ObservableList<String> tags;
        tags = listView.getSelectionModel().getSelectedItems();
        for (String tag: tags){
            try {
                inputFile.deleteTag(tag);
            } catch (Exception e) {
                e.printStackTrace();
            }
            listView.getItems().remove(tag);

        }

    }

    /**
     * Set the ImageFile
     * @param imageFile the ImageFile
     */
    static void setImageFile(ImageFile imageFile){
        inputFile = imageFile;
    }

}