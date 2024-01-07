//Base class for all products the store will sell
public abstract class Product implements Comparable<Product>{
    private double price;
    private int stockQuantity;
    private int soldQuantity;

    private int cartQuantity;
    private int cartHistoryQuantity;

    public Product(double initPrice, int initQuantity) {
        price = initPrice;
        stockQuantity = initQuantity;
        cartQuantity = 0;

        cartHistoryQuantity = 0;
    }

    public int getCartQuantity() {return cartQuantity;}
    public void increaseCartQuantity(){cartQuantity += 1;}
    public void decreaseCartQuantity(){cartQuantity -= 1;}
    //cart history
    public void increaseCartHistoryQuantity(){cartHistoryQuantity += 1;}
    public void decreaseCartHistoryQuantity(){cartHistoryQuantity -= 1;}
    //  public int getCartHistoryQuantity() {return cartHistoryQuantity;}








  //  public int getStockQuantity() {return stockQuantity;}

  //  public int getSoldQuantity() {return soldQuantity;}

    public double getPrice() {
        return price;
    }

    //Returns the total revenue (price * amount) if there are at least amount items in stock
    //Return 0 otherwise (i.e., there is no sale completed)
    public double sellUnits(int amount) {
        if (amount > 0 && stockQuantity >= amount) {
            stockQuantity -= amount;
            soldQuantity += amount;
            return price * amount;
        }
        return 0.0;
    }

    // for popular list
    @Override
    public int compareTo(Product x){
        return Integer.compare(this.cartHistoryQuantity, x.cartHistoryQuantity);
    }
}