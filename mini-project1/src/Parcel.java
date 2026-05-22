public class Parcel {
    private int id;
    private String sender;
    private String receiver;
    private ParcelStatus status;
    private double weight;
    private String shippingType;

    public Parcel(int id, String sender, String receiver, double weight, ParcelStatus status, String shippingType){
    this.id = id;
    this.receiver = receiver;
    this.sender = sender;
    this.status = status;
    this.weight = weight;
    this.shippingType = shippingType;
    }

    //Getter Setter for id
    public int getId(){
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //Getter setter for sender
    public String getSender(){
        return this.sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    //Getter setter for receiver
    public String getReceiver(){
        return this.receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    // Getter setter for status
    public ParcelStatus getStatus(){
        return this.status;
    }

    public void setStatus(ParcelStatus status) {
        this.status = status;
    }

    // Getter setter for weight
    public double getWeight(){
        return this.weight;
    }
    public void setWeight(double weight){
        this.weight = weight;
    }
    //Getter setter for shippingType
    public String getShippingType(){
        return this.shippingType;
    }
    public void setShippingType(String shippingType){
        this.shippingType = shippingType;
    }
    //displayInfo
    public void displayInfo(){
        System.out.println("Parcel: " + id + " "
                + "Sender: " + sender + " "
                + "Receiver: " + receiver + " "
                + "Status: " + status.getDisplayName() + " "
                + "Weight: " + weight + " "
                + "Type of shipping: " + shippingType);
    }




}
