public class User {
    String username;
    String email;

    User(String username, String email){
        this.username = username ;
        this.email = email;
    }
    void login(){
        System.out.println("User " + username + " login");
    }
}
