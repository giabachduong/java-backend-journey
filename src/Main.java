public class Main {

    public static void main(String[] args) {

        Parcel parcel1 = new Parcel(1500, "Nguyen A", "Nguyen B", "Shipping", 2.3);
        parcel1.displayInfo();
        parcel1.isHeavy();
        parcel1.updateStatus("Pending");
        System.out.println(parcel1.status);
        double fee = parcel1.calculateShippingFee();
        System.out.println(fee);
    }
}