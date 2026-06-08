import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ParcelFileStorage {
    ParcelRepository repository;

    public ParcelFileStorage(ParcelRepository repository){
        this.repository = repository;
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

                    this.repository.addParcel(new Parcel(id, sender, receiver, weight, status, shippingType));
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

            for(Parcel p : this.repository.getParcels()){

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

}
