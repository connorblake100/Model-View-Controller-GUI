import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class ElectronicStoreButtonPane extends Pane {
    private     Button  resetButton, addButton, removeButton, soldButton;
    public Button getAddButton() { return addButton; }
    public Button getRemoveButton() { return removeButton; }
    public Button getSoldButton() { return soldButton; }
    public Button getResetButton() { return resetButton; }

    public ElectronicStoreButtonPane(){
        Pane innerPane = new Pane();
        // Create the buttons
        addButton = new Button("Add to Cart");
        addButton.setStyle("-fx-font: 12 arial; -fx-base: rgb(0,100,0); -fx-text-fill: rgb(255,255,255);");
        addButton.relocate(280, 150);
        addButton.setPrefSize(90,30);
        //make add button not clickable if nothing is selected
        addButton.setDisable(true);

        removeButton = new Button("Remove from Cart");
        removeButton.setStyle("-fx-font: 12 arial; -fx-base: rgb(200,0,0); -fx-text-fill: rgb(255,255,255);");
        removeButton.relocate(500, 150);
        removeButton.setPrefSize(120,30);
        //make remove button not clickable if nothing is selected
        removeButton.setDisable(true);

        soldButton = new Button("Complete Sale");
        soldButton.setStyle("-fx-font: 12 arial;");
        soldButton.relocate(620, 150);
        soldButton.setPrefSize(100,30);
        //make sold button not clickable if nothing is in cart list
        soldButton.setDisable(true);


        resetButton = new Button("Reset Store");
        resetButton.setStyle("-fx-font: 12 arial;");
        resetButton.relocate(40, 150);
        resetButton.setPrefSize(90,30);

        // Add all three buttons to the pane
        innerPane.getChildren().addAll(addButton, removeButton, soldButton, resetButton);

        getChildren().addAll(innerPane);//, titleLabel);
    }
}
