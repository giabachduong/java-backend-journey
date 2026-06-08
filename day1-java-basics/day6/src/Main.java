import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static ArrayList<Parcels> loadParcel(String filePath){
        ArrayList<Parcels> parcelsArrayList = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;

            while((line = reader.readLine()) != null){
                String [] data = line.split(",");
                if(data.length !=4){
                    continue;
                }
                try{
                    int id = Integer.parseInt(data[0]);
                    String sender = data[1];
                    String receiver = data[2];
                    String status = data[3];

                    Parcels parcel = new Parcels(id, sender, receiver, status);
                    parcelsArrayList.add(parcel);
                }
                catch(NumberFormatException e){
                    System.out.println("Invalid ID format");
                }
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Could not locate file");
        }
        catch(IOException e){
            System.out.println("Something went wrong");

        }
        return parcelsArrayList;
    }

    public static void saveParcels(
            ArrayList<Parcels> parcels, String filePath){

        try(FileWriter writer =
                    new FileWriter(filePath)){

            for(Parcels p : parcels){

                writer.write(
                        p.getId() + "," +
                                p.getSender() + "," +
                                p.getReceiver() + "," +
                                p.getStatus()
                );
                writer.write("\n");
            }

            System.out.println("Saved parcels");
        }
        catch(IOException e){
            System.out.println("Error saving file");
        }
    }

    public static void main(String[] args) {
        String filePath = "day6/test.txt";
        ArrayList<Parcels> parcelsArrayList = loadParcel(filePath);
        for(Parcels p : parcelsArrayList){
            p.displayInfo();
        }

        ArrayList<Parcels> shippingParcels = parcelsArrayList.stream()
                .filter(p -> p.getStatus().equals("Shipping"))
                .collect(Collectors.toCollection(ArrayList::new));

        System.out.println("Shipping parcels:");
        for(Parcels p : shippingParcels){
            p.displayInfo();
        }

        Parcels newParcel = new Parcels(200, "X", "Y", "Delivered");
        parcelsArrayList.add(newParcel);
        saveParcels(parcelsArrayList, filePath);
    }
}
