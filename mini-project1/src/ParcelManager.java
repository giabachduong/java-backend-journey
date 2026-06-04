import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class ParcelManager {
    private List<Parcel> parcels;

    public ParcelManager(){
        this.parcels = new ArrayList<>();
    }

    public List<Parcel> getParcels(){
        return this.parcels;
    }

    public void addParcel(Parcel parcel){
        this.parcels.add(parcel);
    }

    public void removeParcel(Parcel parcel){
        this.parcels.remove(parcel);
    }

    public static boolean isValidStatus(String status){
        return ParcelStatus.fromInput(status) != null;
    }

    public Parcel createParcel(Scanner scanner, int id){
        System.out.print("Enter sender: ");
        String sender = scanner.nextLine();

        System.out.print("Enter receiver: ");
        String receiver = scanner.nextLine();

        System.out.print("Enter status: ");
        ParcelStatus status = ParcelStatus.fromInput(scanner.nextLine());
        if(status == null){
            System.out.println("Invalid status");
            return null;
        }

        System.out.print("Enter weight: ");
        double weight = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter shipping type: ");
        String shippingType = scanner.nextLine();

        return new Parcel(id, sender, receiver, weight, status, shippingType);
    }

    public Parcel findParcelById(int id){
        for(Parcel p : this.parcels){
            if(p.getId() == id){
                return p;
            }
        }
        return null;
    }

    public ShippingMethod getShippingMethod(String shippingType){
        if(shippingType.equalsIgnoreCase("Standard")){
            return new StandardShipping();
        }
        else if(shippingType.equalsIgnoreCase("Express")){
            return new ExpressShipping();
        }

        return null;
    }

    public double calculateShippingFee(Parcel parcel){
        ShippingMethod shippingMethod = getShippingMethod(parcel.getShippingType());

        if(shippingMethod == null){
            return -1;
        }

        return shippingMethod.calculateFee(parcel.getWeight());
    }

    public long countPendingParcels(){
        return this.parcels.stream().filter(
                p -> p.getStatus() == ParcelStatus.PENDING
        ).count();
    }

    public void sortByIdDescending(){
        this.parcels.sort(
                (a,b) -> Integer.compare(
                        b.getId(),
                        a.getId()
                )
        );
    }

    public HashSet<String> getUniqueSenders(){
        HashSet<String> uniqueSenders = new HashSet<>();

        for(Parcel p : this.parcels){
            uniqueSenders.add(p.getSender());
        }

        return uniqueSenders;
    }

    public void loadParcels(String filePath){
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;

            while((line = reader.readLine()) != null){
                String[] data = line.split(",");

                if(data.length != 6){
                    continue;
                }

                try{
                    int id = Integer.parseInt(data[0]);
                    String sender = data[1];
                    String receiver = data[2];
                    ParcelStatus status = ParcelStatus.fromInput(data[3]);
                    double weight = Double.parseDouble(data[4]);
                    String shippingType = data[5];

                    if(status == null){
                        continue;
                    }

                    this.parcels.add(new Parcel(id, sender, receiver, weight, status, shippingType));
                }
                catch(NumberFormatException e){
                    System.out.println("Invalid parcel data skipped");
                }
            }
        }
        catch(IOException e){
            System.out.println("No saved data found");
        }
    }

    public void saveParcels(String filePath){

        try(FileWriter writer =
                    new FileWriter(filePath)){

            for(Parcel p : this.parcels){

                writer.write(
                        p.getId() + "," +
                                p.getSender() + "," +
                                p.getReceiver() + "," +
                                p.getStatus().getDisplayName() + "," +
                                p.getWeight() + "," +
                                p.getShippingType()
                );
                writer.write("\n");
            }

            System.out.println("Saved parcels");
        }
        catch(IOException e){
            System.out.println("Error saving file");
        }
    }

    public void showAllParcels(){
        for(Parcel p : this.parcels){
            p.displayInfo();
        }
    }
}
