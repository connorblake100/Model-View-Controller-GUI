import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ElectronicStoreApp extends Application {
    ElectronicStore model;
    ElectronicStoreView view;
    private int count;

    public ElectronicStoreApp(){
        model = ElectronicStore.createStore();
        view = new ElectronicStoreView(model);
    }

    public void start(Stage primaryStage) {
        Pane aPane = new Pane();

        // if something is selected in stockList, add button can be clicked
        view.getStockList().setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e){
                view.getAddButtonTwo().setDisable(false);
                view.update();
            }
        });
        // if add button is clicked then adds product to cartList
        view.getAddButtonTwo().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                Product productAdded = view.getStockList().getSelectionModel().getSelectedItem();
                view.addToCart(productAdded);
                // if something is added the complete sale button is clickable
                view.getSoldButtonTwo().setDisable(false);
                view.update();
            }
        });

        view.getCartList().setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e){
                // if something is selected in cartList, remove button can be clicked
                view.getRemoveButtonTwo().setDisable(false);
                view.update();
            }
        });
        // if remove button is clicked then remove product from cartList
        view.getRemoveButtonTwo().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                int productRemovedIndex = view.getCartList().getSelectionModel().getSelectedIndex();
                view.removeFromCart(productRemovedIndex);
                // if all products have been removed from cartList remove and complete sale button can no longer be pressed
                if (view.getCart().size() < 1){
                    //remove button deCLICK
                    view.getRemoveButtonTwo().setDisable(true);
                    // complete sale button deCLICKed
                    view.getSoldButtonTwo().setDisable(true);
                }
                view.update();
            }
        });

        // if complete sale button is clicked then popularList, # Sales, revenue, $/sale is updated
        view.getSoldButtonTwo().setOnAction(new EventHandler<ActionEvent>(){
            // int count keeps count of # of sales
            //count = 0;
            public void handle(ActionEvent actionEvent) {
                // does the revenue
                // not 2 nice
                view.getRevenue().setText(Integer.toString(view.totalDollars()));
                count++;
                // does the $/sale
                view.getDollarsPerSale().setText((Integer.toString(view.priceDollars()/count)));
                // call sometin for popular list
                view.getPopularList().setItems(FXCollections.observableArrayList(view.popularListTing()));
                // once complete sale is pressed current cart is cleared
             //   ArrayList<Product> emptyCart = new ArrayList<>();
                //view.setCart(emptyCart);
                view.getCart().clear();
                view.getCartList().setItems(FXCollections.observableArrayList());
                // records number of sales
                view.getNumberSales().setText(Integer.toString(count));
                view.update();
            }
        });

        // if reset button gets clicked window gets reset to original
        view.getResetButtonTwo().setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent actionEvent) {
                ArrayList<Product> empty = new ArrayList<>();
                view.getCart().clear();
                view.getCartHistory().clear();
                count = 0;
                view.setPrice(0);
                view.setTotPrice(0);
                view.getRevenue().setText(Integer.toString(view.totalDollars()));
                view.getDollarsPerSale().setText((Integer.toString(0)));
                view.getNumberSales().setText(Integer.toString(0));
                view.getPopular().clear();
                view.update();
            }
        });

        primaryStage.setTitle("Electronic Store Application");
        primaryStage.setResizable(true);
        primaryStage.setScene(new Scene(view, 800, 400)); // Set size of window
        primaryStage.show();
        view.update();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
