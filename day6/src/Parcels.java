public class Parcels{
    private int id;
    private String sender;
    private String receiver;
    private String status;

    Parcels(int id, String sender, String receiver, String status){
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.status = status;
    }

    public int getId(){
        return this.id;
    }

    public String getSender(){
        return this.sender;
    }

    public String getReceiver(){
        return this.receiver;
    }

    public String getStatus() {
        return status;
    }

    public void displayInfo(){
        System.out.println("Parcel:" + id + " " + "Sender:" + sender + " "
                            + "Receiver:" + receiver + " " + "Status:" + status );
    }
}


