public class PushNoti implements NotificationService{
    @Override
    public void sendMessage(String message){
        System.out.println("PUSH: " + message);
    }
}
