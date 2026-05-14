public class Book {
    String title;
    String author;
    int price;

    Book(String title, String author, int price){
        this.title = title;
        this.author = author;
        this.price = price;

    }

    void displayInfo(){
        System.out.println(title);
        System.out.println(author);
        System.out.println(price);
    }
}
