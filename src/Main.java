public class Main {

    public static void main(String[] args) {
        Product product = new Product("book", 10);
        Parcel parcel = new Parcel(1000, "A", "B", "Shipping", 2.3);
        Bank bank = new Bank(1000, "A");

        bank.withdraw(2000);
        bank.deposit(100);
        parcel.setWeight(-5);
        parcel.setStatus("");
    }
}