//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        Parcel parcel = new Parcel(100, "A", "B", 2.5);
        parcel.displayInfo();
        ShippingMethod ship1 = new StandardShipping();
        ShippingMethod ship2 = new ExpressShipping();
        ShippingMethod ship3 = new InternationShipping();

        ShippingMethod[] shippingMethods = {ship1, ship2, ship3};
        for (ShippingMethod shippingMethod : shippingMethods){
            System.out.println(
                    shippingMethod.getType() + " fee: " +
                    shippingMethod.calculateFee(2.5));
        }

        NotificationService service1 = new EmailNoti();
        NotificationService service2 = new SMSNoti();
        NotificationService service3 = new PushNoti();

        NotificationService[] services = {service1, service2, service3};
        for(NotificationService service : services){
            service.sendMessage("Parcel Delivered");
        }

    }
}