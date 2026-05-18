public class Parcel {
    private int id;
    private String sender;
    private String receiver;
    private String status;

    Parcel(int id, String sender, String receiver, String status){
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

    public String getStatus(){
        return this.status;
    }

    public void setStatus(String status){
        if(status.isEmpty()){
            System.out.println("Status cannot be empty");
        }
        else{
            this.status = status;
        }
    }

    public void displayInfo(){
        System.out.println("Parcel ID: " + id);
        System.out.println("Sender: " + sender);
        System.out.println("Receiver: " + receiver);
        System.out.println("Status: " + status);
    }






}
