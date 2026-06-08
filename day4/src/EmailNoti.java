public class EmailNoti implements NotificationService{
    @Override
    public void sendMessage(String message){
        System.out.println("Email: " + message);
    }
}
