public class Parcel {
    private int id;
    private String sender;
    private String receiver;
    private double weight;

    Parcel(int id, String sender, String receiver, double weight){
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.weight = weight;
    }

    void displayInfo(){
        System.out.println(id + " " + sender + " " + receiver + " " + weight);
    }
}
