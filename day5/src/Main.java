import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static int askForId(Scanner scanner){
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        return id;
    }

    public static Parcel findParcelById(ArrayList<Parcel> parcels, int id){
        for(Parcel p : parcels){
            if(p.getId() == id){
                return p;
            }
        }

        return null;
    }

    public static Parcel createParcel(Scanner scanner, int id){
        System.out.println("Enter sender: ");
        String sender = scanner.nextLine();

        System.out.println("Enter receiver: ");
        String receiver = scanner.nextLine();

        System.out.println("Enter status: ");
        String status = scanner.nextLine();

        return new Parcel(id, sender, receiver, status);
    }

    public static void main(String[] args) {

        // parcel crud system
        Parcel parcel = new Parcel(100, "Nguyen A", "Nguyen B", "Shipping");
        Parcel parcel1 = new Parcel(102, "C", "D","Waiting");
        ArrayList<Parcel> parcels = new ArrayList<>();
        parcels.add(parcel);
        parcels.add(parcel1);

        Scanner scanner = new Scanner(System.in);

        menuLoop:
        while(true) {
            System.out.println("1 Add parcel");
            System.out.println("2 Show parcel");
            System.out.println("3 Search parcel");
            System.out.println("4 Update parcel");
            System.out.println("5 Delete parcel");
            System.out.println("6 Exit");

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
                    Parcel existParcel = findParcelById(parcels, id);

                    if(existParcel != null){
                        System.out.println("Id already exist");
                        break;
                    }

                    Parcel newParcel = createParcel(scanner, id);
                    parcels.add(newParcel);
                    System.out.println("Parcel added");
                    break;

                case "3": {
                    int searchId = askForId(scanner);
                    Parcel foundParcel = findParcelById(parcels, searchId);

                    if(foundParcel != null){
                        foundParcel.displayInfo();
                    }
                    else {
                        System.out.println("Parcel not found");
                    }
                    break;
                }

                case "4": {
                    int searchId = askForId(scanner);
                    Parcel foundParcel = findParcelById(parcels, searchId);

                    if(foundParcel != null){
                        System.out.print("Enter the new status: ");
                        String newStatus = scanner.nextLine();
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
                    Parcel foundParcel = findParcelById(parcels, searchId);

                    if(foundParcel != null){
                        parcels.remove(foundParcel);
                        System.out.println("Parcel deleted");
                    }
                    else {
                        System.out.println("Parcel not found");
                    }
                    break;
                }

                case "6":
                    break menuLoop;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }

        scanner.close();
    }
}
