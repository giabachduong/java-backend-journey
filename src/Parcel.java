public class Parcel {

    int trackingId;
    String sender;
    String receiver;
    String status;
    double weight;
    double fee;

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
}
