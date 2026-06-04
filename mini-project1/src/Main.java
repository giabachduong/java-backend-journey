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
        ParcelManager manager = new ParcelManager();
        manager.loadParcels(filepath);

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
                    manager.showAllParcels();
                    break;

                case "1":
                    int id = askForId(scanner);
                    if(id == -1){
                        break;
                    }

                    Parcel existParcel = manager.findParcelById(id);

                    if (existParcel != null) {
                        System.out.println("Id already exist");
                        break;
                    }

                    Parcel newParcel = manager.createParcel(scanner, id);
                    if(newParcel == null){
                        break;
                    }

                    manager.addParcel(newParcel);
                    System.out.println("Parcel added");
                    break;

                case "3": {
                    int searchId = askForId(scanner);
                    if(searchId == -1){
                        break;
                    }

                    Parcel foundParcel = manager.findParcelById(searchId);

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

                    Parcel foundParcel = manager.findParcelById(searchId);

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

                    Parcel foundParcel = manager.findParcelById(searchId);

                    if(foundParcel != null){
                        manager.removeParcel(foundParcel);
                        System.out.println("Parcel deleted");
                    }
                    else {
                        System.out.println("Parcel not found");
                    }
                    break;
                }

                case "6": {
                    long pendingCount = manager.countPendingParcels();
                    System.out.println("The number of parcels is pending: " + pendingCount);
                    break;
                }

                case "7": {
                    manager.sortByIdDescending();
                    manager.getParcels().forEach(
                            p -> p.displayInfo()
                    );
                    break;
                }

                case "8": {
                    for(String sender : manager.getUniqueSenders()){
                        System.out.println(sender);
                    }
                    break;
                }

                case "9": {
                    manager.saveParcels(filepath);
                    break;
                }

                case "10": {
                    int searchId = askForId(scanner);
                    if(searchId == -1){
                        break;
                    }

                    Parcel foundParcel = manager.findParcelById(searchId);

                    if(foundParcel == null){
                        System.out.println("Parcel not found");
                        break;
                    }

                    double fee = manager.calculateShippingFee(foundParcel);

                    if(fee == -1){
                        System.out.println("Invalid shipping type");
                    }
                    else {
                        System.out.println("Shipping fee: " + fee);
                    }
                    break;
                }

                case "0": {
                    manager.saveParcels(filepath);
                    break menuLoop;
                }


                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }

    }
}
