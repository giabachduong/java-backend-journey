public class Parcel {
    private int trackingId;
    private String sender;
    private String receiver;
    private String status;
    private double weight;
    private double fee;

    Parcel(int trackingId, String sender, String receiver, String status, double weight){
        this.trackingId = trackingId;
        this.sender = sender;
        this.receiver = receiver;
        this.status = status;
        this.weight = weight;
    }

    void displayInfo(){
        System.out.println(trackingId);
        System.out.println(sender);
        System.out.println(receiver);
        System.out.println(status);
        System.out.println(weight);
    }

    void updateStatus(String newStatus){
        status = newStatus;
    }

    void isHeavy(){
        if(weight > 20){
            System.out.println("Heavy parcel");
        }
        else{
            System.out.println("Normal parcel");
        }
    }

    double calculateShippingFee(){
        if(weight <= 5){
            return 20;
        }
        else if(weight <= 20){
            return 50;
        }
        else{
            return 100;
        }
    }

    void setWeight(double weight){
        if(weight < 0){
            System.out.println("Invalid");
        }
        else{
            this.weight = weight;
        }
    }

    void setStatus(String status){
        if(status.isEmpty()){
            System.out.println("Status cannot be empty");
        }
        else{
            this.status = status;
        }
    }
}
