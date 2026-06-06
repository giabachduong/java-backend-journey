import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

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

    private void validateWeight(double weight) throws InvalidParcelException{
        if(weight <= 0){
            throw new InvalidParcelException("Weight must be positive");
        }
    }

    public void validateParcel(Parcel p) throws InvalidParcelException{
        if(p.getSender().isBlank()){
            throw new InvalidParcelException("Sender cannot be empty");
        }

        if(p.getReceiver().isBlank()){
            throw new InvalidParcelException("Receiver cannot be empty");
        }

        validateWeight(p.getWeight());

        if(getShippingMethod(p.getShippingType()).isEmpty()){
            throw new InvalidParcelException("Invalid shipping type");
        }
    }

    public Optional<Parcel> createParcel(Scanner scanner, int id){
        System.out.print("Enter sender: ");
        String sender = scanner.nextLine();

        System.out.print("Enter receiver: ");
        String receiver = scanner.nextLine();

        System.out.print("Enter status: ");
        ParcelStatus status = ParcelStatus.fromInput(scanner.nextLine());
        if(status == null){
            System.out.println("Invalid status");
            return Optional.empty();
        }

        System.out.print("Enter weight: ");
        double weight = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter shipping type: ");
        String shippingType = scanner.nextLine();

        Parcel parcel = new Parcel(id, sender, receiver, weight, status, shippingType);

        try{
            validateParcel(parcel);
        }
        catch(InvalidParcelException e){
            System.out.println(e.getMessage());
            return Optional.empty();
        }

        return Optional.of(parcel);
    }

    public Optional<Parcel> findParcelById(int id){
        for(Parcel p : this.parcels){
            if(p.getId() == id){
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    public Optional<ShippingMethod> getShippingMethod(String shippingType){
        if(shippingType.equalsIgnoreCase("Standard")){
            return Optional.of(new StandardShipping());
        }
        else if(shippingType.equalsIgnoreCase("Express")){
            return Optional.of(new ExpressShipping());
        }

        return Optional.empty();
    }

    public Optional<Double> calculateShippingFee(Parcel parcel){
        Optional<ShippingMethod> shippingMethod = getShippingMethod(parcel.getShippingType());

        if(shippingMethod.isEmpty()){
            return Optional.empty();
        }

        return Optional.of(shippingMethod.get().calculateFee(parcel.getWeight()));
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

    public void sortByDefaultId(){
        Collections.sort(this.parcels);
    }

    public void sortByWeight(){
        this.parcels.sort(new ParcelWeightComparator());
    }

    public HashSet<String> getUniqueSenders(){
        HashSet<String> uniqueSenders = new HashSet<>();

        for(Parcel p : this.parcels){
            uniqueSenders.add(p.getSender());
        }

        return uniqueSenders;
    }

    public List<String> getSenderNames(){
        return this.parcels.stream()
                .map(Parcel::getSender)
                .toList();
    }

    public boolean anyShippingParcel(){
        return this.parcels.stream()
                .anyMatch(
                        p -> p.getStatus() == ParcelStatus.SHIPPING
                );
    }

    public boolean areAllDelivered(){
        return this.parcels.stream()
                .allMatch(
                        p -> p.getStatus() == ParcelStatus.DELIVERED
                );
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
