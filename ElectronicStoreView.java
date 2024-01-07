import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.collections.FXCollections;


import java.util.ArrayList;
import java.util.*;

public class ElectronicStoreView extends Pane {
    private ListView<Product> popularList,stockList ;
    private ListView<String> cartList;
    private ElectronicStoreButtonPane buttonPane;
    ElectronicStore model;
    private TextField numberSales, revenue, dollarsPerSale;
    private ArrayList<Product> Cart, cartHistory, popular,popularTwo;

    public ElectronicStoreView(ElectronicStore initModel) {
        model = initModel;

        Cart = new ArrayList<Product>();
        // cart history
        cartHistory = new ArrayList<Product>();
        // popular
        popular = new ArrayList<Product>();

        popularTwo = new ArrayList<Product>();


        // Create and position the "new item" TextField
        numberSales = new TextField();
        numberSales.relocate(90, 40);
        numberSales.setPrefSize(120, 25);
        numberSales.setEditable(false);

        revenue = new TextField();
        revenue.relocate(90, 70);
        revenue.setPrefSize(120, 25);
        revenue.setEditable(false);


        dollarsPerSale = new TextField();
        dollarsPerSale.relocate(90, 100);
        dollarsPerSale.setPrefSize(120, 25);
        dollarsPerSale.setEditable(false);


        // Create the labels
        Label label1 = new Label("Store Summary:");
        label1.relocate(40, 10);
        Label label2 = new Label("Store Stock:");
        label2.relocate(300, 10);
        Label label3 = new Label("Current Cart:");
        label3.relocate(580, 10);
        Label label4 = new Label("# Sales:");
        label4.relocate(35, 40);
        Label label5 = new Label("Revenue:");
        label5.relocate(35, 70);
        Label label6 = new Label("$ / Sale:");
        label6.relocate(35, 100);
        Label label7 = new Label("Most Popular Items:");
        label7.relocate(40, 140);


        // Create the lists
        popularList = new ListView<Product>();
        popularList.relocate(10, 160);
        popularList.setPrefSize(200, 180);

        stockList = new ListView<Product>();
        stockList.relocate(220, 40);
        stockList.setPrefSize(270, 300);

        cartList = new ListView<String>();
        cartList.relocate(500, 40);
        cartList.setPrefSize(270, 300);

        // Create the button pane
        buttonPane = new ElectronicStoreButtonPane();
        buttonPane.relocate(30, 200);
        buttonPane.setPrefSize(305, 30);

        // Add all the components to the window
        getChildren().addAll(buttonPane, cartList, stockList, popularList, label1,label2,label3,label4,label5,label6,label7,numberSales,revenue,dollarsPerSale );
    }

    public ListView<Product> getStockList(){return stockList;}
    public ListView<String> getCartList(){return cartList;}
    public ArrayList<Product> getCart(){return Cart;}
    public ArrayList<Product> setCart(ArrayList<Product> x){return Cart = x;}
    public ArrayList<Product> getPopular(){return popular;}



    //  public ArrayList<Product> setCartHistory(ArrayList<Product> x){return cartHistory = x;}
    public ArrayList<Product> getCartHistory(){return cartHistory;}



    public ArrayList<Product> setPopular(ArrayList<Product> x){return popular = x;}



    public ArrayList<Product> popularListTing() {
        popular.clear();
        cartHistory.sort(Collections.reverseOrder());
        for (int i = 0; i < cartHistory.size() && i < 3; i++) {
            popular.add(cartHistory.get(i));
        }
        return popular;
    }


    private int totPrice = 0;
    public int setTotPrice(int x){return totPrice = x;}


    public int totalDollars(){
        for (int i = 0; i < Cart.size(); i++) {
            totPrice += Cart.get(i).getPrice() * Cart.get(i).getCartQuantity();
        }
        return totPrice;
    }

    private int Price = 0;
    public int setPrice(int x){return Price = x;}

    public int priceDollars(){
        for (int i = 0; i < Cart.size(); i++) {
            Price += Cart.get(i).getPrice() * Cart.get(i).getCartQuantity();
        }
        return Price;
    }






    public ListView<Product> getPopularList(){return popularList;}
    public TextField getNumberSales(){return numberSales;}
    public TextField getRevenue() {return revenue;}
    public TextField getDollarsPerSale() {return dollarsPerSale;}
    public Button getAddButtonTwo() {return buttonPane.getAddButton();}
    public Button getRemoveButtonTwo() {return buttonPane.getRemoveButton();}
    public Button getSoldButtonTwo() { return buttonPane.getSoldButton(); }
    public Button getResetButtonTwo(){return buttonPane.getResetButton();}




    public boolean addToCart(Product addedToCart){
        if (addedToCart.getCartQuantity() == 0){
            addedToCart.increaseCartQuantity();
            // cart history
            addedToCart.increaseCartHistoryQuantity();
            cartHistory.add(addedToCart);
          //  System.out.println(cartHistory);
         //   System.out.println("hhhhhh     " +Cart);
            return Cart.add(addedToCart);
        }

        if (addedToCart.getCartQuantity() == 10){
            // max amount
        }
        else{
            addedToCart.increaseCartQuantity();
            // cart History
            addedToCart.increaseCartHistoryQuantity();
         //   System.out.println(cartHistory);
        //    System.out.println("shhhhhh     " +Cart);
        }
        return true;
    }


    public void removeFromCart(int productRemovedIndex){
        // if only 1 item in cart returns a new empty list since it won't let the last product in cart be removed
        if (productRemovedIndex >= 0) {
            if (Cart.size() == 0 && Cart.get(productRemovedIndex).getCartQuantity() == 1) {
                Cart.remove(Cart.get(0));
                // cart history
                cartHistory.get(productRemovedIndex).decreaseCartHistoryQuantity();
                System.out.println(cartHistory);
            }
            if (Cart.get(productRemovedIndex).getCartQuantity() == 1){
                Cart.remove(Cart.get(productRemovedIndex));
                // cart history
                if (cartHistory.size() < productRemovedIndex){
                    cartHistory.get(productRemovedIndex).decreaseCartHistoryQuantity();
                }
            }
            else {
                Cart.get(productRemovedIndex).decreaseCartQuantity();
                // cart history
                cartHistory.get(productRemovedIndex).decreaseCartHistoryQuantity();
                System.out.println(cartHistory);
            }
        }
    }



    public void update() {
        // displaying the stock
        Product[] stock = model.getStock();
        stockList.setItems(FXCollections.observableArrayList(stock));

        // if no sale been made still displays 3 in popular list
        if (popular.size() == 0){
            popularTwo.clear();
            for (int i = 0; i < 3; i++) {
                popularTwo.add(stock[i]);
            }
            popularList.setItems(FXCollections.observableArrayList(popularTwo));
        }
        // if 1 sale been made still displays 3 in popular list
        if (popular.size() == 1){
            popular.add(stock[0]);
            popular.add(stock[1]);

            popularList.setItems(FXCollections.observableArrayList(popular));
        }
        // if 2 sale been made still displays 3 in popular list
        if (popular.size() == 2){
            popular.add(stock[0]);
            popularList.setItems(FXCollections.observableArrayList(popular));
        }
        // if popular list is somehow greater than 3
        if (popular.size() > 3){
            popularList.setItems(FXCollections.observableArrayList(popularListTing()));
        }

            // displays cartList
        if (Cart != null) {
            String[] names = new String[Cart.size()];
            for (int i = 0; i < Cart.size(); i++) {
                names[i] = Cart.get(i).getCartQuantity() + "x " + Cart.get(i).toString();
            }
            cartList.setItems(FXCollections.observableArrayList(names));
            }

        // remove button / complete sale button
        if (Cart.size()==0){
            getRemoveButtonTwo().setDisable(true);
            getSoldButtonTwo().setDisable(true);
        }
    }
}