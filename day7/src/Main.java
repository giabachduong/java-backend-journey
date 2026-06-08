import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        /*
        HashSet<Integer> integers = new HashSet<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(2);
        System.out.println(integers);

        */

        Parcels parcel1 = new Parcels(100, "A", "B", "Pending");
        Parcels parcel2 = new Parcels(101, "C", "D", "Shipping");
        Parcels parcel3 = new Parcels(103, "X", "Y", "Shipping");
        Parcels parcel4 = new Parcels(104, "W", "Z", "Delivered");
        Parcels parcel5 = new Parcels(105, "A", "U", "Pending");

        List<Parcels> parcelsList = new ArrayList<>();
        parcelsList.add(parcel1);
        parcelsList.add(parcel2);
        parcelsList.add(parcel3);
        parcelsList.add(parcel4);
        parcelsList.add(parcel5);

        HashMap<Integer, Parcels> map = new HashMap<>();
        for(Parcels p : parcelsList){
            map.put(p.getId(), p);
        }

        Parcels foundParcel = map.get(100);
        if(foundParcel != null){
            foundParcel.displayInfo();
        }

        // Lambda expressions
        System.out.println("Print status: ");
        parcelsList.forEach(p -> System.out.println(p.getStatus()));
        System.out.println(" ");

        System.out.println("Print ID: ");
        parcelsList.forEach(p -> System.out.println(p.getId()));
        System.out.println(" ");

        System.out.println("Print pending parcels: ");
        parcelsList.stream().filter(
                p -> p.getStatus().equals("Pending"))
                .forEach(
                        p -> p.displayInfo()
                );
        System.out.println(" ");

        System.out.println("Print shipping parcels: ");
        parcelsList.stream().filter(
                c -> c.getStatus().equals("Shipping"))
                        .forEach(
                                c -> c.displayInfo()
                        );
        System.out.println(" ");

        System.out.println("Print shipping parcels as collection: ");
        List<Parcels> shippingParcels = parcelsList.stream()
                .filter(p -> p.getStatus().equals("Shipping"))
                .collect(Collectors.toList());

        shippingParcels.forEach(p -> p.displayInfo());
        System.out.println(" ");

        System.out.println("Print senders: ");
        HashSet<String> senders = new HashSet<>();
        for(Parcels p : parcelsList){
            senders.add(p.getSender());
        }
        System.out.println(senders);
        System.out.println(" ");

        System.out.println("Descending: ");
        parcelsList.sort(
                (a,b) ->
                        Integer.compare(
                                b.getId(),
                                a.getId()
                        )
        );
        parcelsList.forEach(
                p -> p.displayInfo()
        );
        System.out.println(" ");

        System.out.println("Print pending count: ");
        long pendingCount = parcelsList.stream().filter(
                p -> p.getStatus().equals("Pending")
        ).count();
        System.out.println(pendingCount);

    }
}
