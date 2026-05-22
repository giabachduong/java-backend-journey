import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static int askForId(Scanner scanner){
        System.out.print("Enter ID: ");
        if(!scanner.hasNextInt()){
            System.out.println("Invalid ID");
            scanner.nextLine();
            return -1;
        }

        int id = scanner.nextInt();
        scanner.nextLine();
        return id;
    }
    public static void main(String[] args) {
        String filepath = "mini-project1/src/test.txt";
        Scanner scanner = new Scanner(System.in);
        List<Parcel> parcels = new ArrayList<>();
        ParcelManager.loadParcels(parcels, filepath);

        menuLoop:
        while(true) {
            System.out.println("1 Add parcel");
            System.out.println("2 Show parcels");
            System.out.println("3 Search Parcel");
            System.out.println("4 Update status");
            System.out.println("5 Delete parcel");
            System.out.println("6 Filter pending");
            System.out.println("7 Sort descending");
            System.out.println("8 Unique sender");
            System.out.println("9 Save");
            System.out.println("10 Calculate shipping fee");
            System.out.println("0 Exit");

            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "2":
                    for (Parcel p : parcels) {
                        p.displayInfo();
                    }
                    break;

                case "1":
                    int id = askForId(scanner);
                    if(id == -1){
                        break;
                    }

                    Parcel existParcel = ParcelManager.findParcelById(parcels, id);

                    if (existParcel != null) {
                        System.out.println("Id already exist");
                        break;
                    }

                    Parcel newParcel = ParcelManager.createParcel(scanner, id);
                    if(newParcel == null){
                        break;
                    }

                    parcels.add(newParcel);
                    System.out.println("Parcel added");
                    break;

                case "3": {
                    int searchId = askForId(scanner);
                    if(searchId == -1){
                        break;
                    }

                    Parcel foundParcel = ParcelManager.findParcelById(parcels, searchId);

                    if (foundParcel != null) {
                        foundParcel.displayInfo();
                    } else {
                        System.out.println("Parcel not found");
                    }
                    break;
                }

                case "4": {
                    int searchId = askForId(scanner);
                    if(searchId == -1){
                        break;
                    }

                    Parcel foundParcel = ParcelManager.findParcelById(parcels, searchId);

                    if(foundParcel != null){
                        System.out.print("Enter the new status: ");
                        ParcelStatus newStatus = ParcelStatus.fromInput(scanner.nextLine());
                        if(newStatus == null){
                            System.out.println("Invalid status");
                            break;
                        }

                        foundParcel.setStatus(newStatus);
                        System.out.println("Parcel updated");
                    }
                    else {
                        System.out.println("Parcel not found");
                    }
                    break;
                }

                case "5": {
                    int searchId = askForId(scanner);
                    if(searchId == -1){
                        break;
                    }

                    Parcel foundParcel = ParcelManager.findParcelById(parcels, searchId);

                    if(foundParcel != null){
                        parcels.remove(foundParcel);
                        System.out.println("Parcel deleted");
                    }
                    else {
                        System.out.println("Parcel not found");
                    }
                    break;
                }

                case "6": {
                    long pendingCount = parcels.stream().filter(
                            p -> p.getStatus() == ParcelStatus.PENDING
                    ).count();
                    System.out.println("The number of parcels is pending: " + pendingCount);
                    break;
                }

                case "7": {
                    parcels.sort(
                            (a,b) ->
                                    Integer.compare(
                                            b.getId(),
                                            a.getId()
                                    )
                    );
                    parcels.forEach(
                            p -> p.displayInfo()
                    );
                    break;
                }

                case "8": {
                    HashSet<String> uniqueSenders = new HashSet<>();

                    for(Parcel p : parcels){
                        uniqueSenders.add(p.getSender());
                    }

                    for(String sender : uniqueSenders){
                        System.out.println(sender);
                    }
                    break;
                }

                case "9": {
                    ParcelManager.saveParcels(parcels, filepath);
                    break;
                }

                case "10": {
                    int searchId = askForId(scanner);
                    if(searchId == -1){
                        break;
                    }

                    Parcel foundParcel = ParcelManager.findParcelById(parcels, searchId);

                    if(foundParcel == null){
                        System.out.println("Parcel not found");
                        break;
                    }

                    double fee = ParcelManager.calculateShippingFee(foundParcel);

                    if(fee == -1){
                        System.out.println("Invalid shipping type");
                    }
                    else {
                        System.out.println("Shipping fee: " + fee);
                    }
                    break;
                }

                case "0": {
                    ParcelManager.saveParcels(parcels, filepath);
                    break menuLoop;
                }


                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }

    }
}
