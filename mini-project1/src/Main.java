import java.util.Optional;
import java.util.OptionalInt;
import java.util.Scanner;

public class Main {
    public static OptionalInt askForId(Scanner scanner){
        System.out.print("Enter ID: ");
        if(!scanner.hasNextInt()){
            System.out.println("Invalid ID");
            scanner.nextLine();
            return OptionalInt.empty();
        }

        int id = scanner.nextInt();
        scanner.nextLine();
        return OptionalInt.of(id);
    }
    public static void main(String[] args) {

        String filepath = "mini-project1/src/test.txt";
        Scanner scanner = new Scanner(System.in);

        ParcelRepository repository = new ParcelRepository();
        ParcelFileStorage storage = new ParcelFileStorage(repository);
        ParcelService service = new ParcelService(repository);

        storage.loadParcels(filepath);

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
            System.out.println("11 Sender names");
            System.out.println("12 Any shipping parcel ? ");
            System.out.println("13 Are all parcels delivered ? ");
            System.out.println("14 Sort by ID");
            System.out.println("15 Sort by weight");
            System.out.println("0 Exit");

            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "2":
                    repository.showAllParcels();
                    break;

                case "1":
                    OptionalInt idInput = askForId(scanner);
                    if(idInput.isEmpty()){
                        break;
                    }
                    int id = idInput.getAsInt();

                    Optional<Parcel> existParcel = repository.findParcelById(id);
                    if (existParcel.isPresent()) {
                        System.out.println("Id already exist");
                        break;
                    }
                    System.out.print("Enter sender: ");
                    String sender = scanner.nextLine();

                    System.out.print("Enter receiver: ");
                    String receiver = scanner.nextLine();

                    System.out.print("Enter status: ");
                    ParcelStatus status = ParcelStatus.fromInput(scanner.nextLine());
                    if (status == null) {
                        System.out.println("Invalid status");
                        break;
                    }

                    System.out.print("Enter weight: ");
                    double weight;
                    try {
                        weight = Double.parseDouble(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid weight");
                        break;
                    }

                    System.out.print("Enter shipping type: ");
                    String shippingType = scanner.nextLine();

                    Parcel newParcel = new Parcel(id, sender, receiver, weight, status, shippingType);
                    try {
                        service.createParcel(newParcel);
                        System.out.println("Parcel added");
                    } catch (InvalidParcelException e) {
                        System.out.println("Cannot add parcel: " + e.getMessage());
                    }

                    break;

                case "3": {
                    OptionalInt searchIdInput = askForId(scanner);
                    if(searchIdInput.isEmpty()){
                        break;
                    }
                    int searchId = searchIdInput.getAsInt();

                    Optional<Parcel> foundParcel = repository.findParcelById(searchId);

                    if (foundParcel.isPresent()) {
                        foundParcel.get().displayInfo();
                    } else {
                        System.out.println("Parcel not found");
                    }
                    break;
                }

                case "4": {
                    OptionalInt searchIdInput = askForId(scanner);
                    if(searchIdInput.isEmpty()){
                        break;
                    }
                    int searchId = searchIdInput.getAsInt();

                    Optional<Parcel> foundParcel = repository.findParcelById(searchId);

                    if(foundParcel.isPresent()){
                        System.out.print("Enter the new status: ");
                        ParcelStatus newStatus = ParcelStatus.fromInput(scanner.nextLine());
                        if(newStatus == null){
                            System.out.println("Invalid status");
                            break;
                        }

                        foundParcel.get().setStatus(newStatus);
                        System.out.println("Parcel updated");
                    }
                    else {
                        System.out.println("Parcel not found");
                    }
                    break;
                }

                case "5": {
                    OptionalInt searchIdInput = askForId(scanner);
                    if(searchIdInput.isEmpty()){
                        break;
                    }
                    int searchId = searchIdInput.getAsInt();

                    Optional<Parcel> foundParcel = repository.findParcelById(searchId);

                    if(foundParcel.isPresent()){
                        repository.removeParcel(foundParcel.get());
                        System.out.println("Parcel deleted");
                    }
                    else {
                        System.out.println("Parcel not found");
                    }
                    break;
                }

                case "6": {
                    long pendingCount = service.countPendingParcels();
                    System.out.println("The number of parcels is pending: " + pendingCount);
                    break;
                }

                case "7": {
                    service.sortByIdDescending();
                    repository.getParcels().forEach(
                            p -> p.displayInfo()
                    );
                    break;
                }

                case "8": {
                    for(String sender1 : service.getUniqueSenders()){
                        System.out.println(sender1);
                    }
                    break;
                }

                case "9": {
                    storage.saveParcels(filepath);
                    break;
                }

                case "10": {
                    OptionalInt searchIdInput = askForId(scanner);
                    if(searchIdInput.isEmpty()){
                        break;
                    }
                    int searchId = searchIdInput.getAsInt();

                    Optional<Parcel> foundParcel = repository.findParcelById(searchId);

                    if(foundParcel.isEmpty()){
                        System.out.println("Parcel not found");
                        break;
                    }

                    Optional<Double> fee = service.calculateShippingFee(foundParcel.get());

                    if(fee.isEmpty()){
                        System.out.println("Invalid shipping type");
                    }
                    else {
                        System.out.println("Shipping fee: " + fee.get());
                    }
                    break;
                }

                case "11": {
                    for(String sender2 : service.getSenderNames()){
                        System.out.println(sender2);
                    }
                    break;
                }

                case "12": {
                    if(service.anyShippingParcel()){
                        System.out.println("There is at least one shipping parcel");
                    }
                    else {
                        System.out.println("There are no shipping parcels");
                    }
                    break;
                }

                case "13": {
                    if(service.areAllDelivered()){
                        System.out.println("All parcels are delivered");
                    }
                    else {
                        System.out.println("Not all parcels are delivered");
                    }
                    break;
                }

                case "14": {
                    service.sortByDefaultId();
                    repository.showAllParcels();
                    break;
                }

                case "15": {
                    service.sortByWeight();
                    repository.showAllParcels();
                    break;
                }

                case "0": {
                    storage.saveParcels(filepath);
                    break menuLoop;
                }


                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }

    }
}
