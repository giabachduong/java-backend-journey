public class Product {
    private String name;
    private double price;

    Product(String name, double price){
        this.name = name;
        this.price = price;
    }

    void setPrice(double price){
        if(price < 0){
            System.out.println("Invalid");
        }
        else{
            this.price = price;
        }
    }
}
