public class User {
    private String username;
    private String email;
    private String password;

    User(String username, String email, String password){
        this.username = username ;
        this.email = email;
        this.password = password;
    }
    String getUsername(){
        return this.username;
    }
    String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        if(email.contains("@")){
            this.email = email;
        }
        else{
            System.out.println("Invalid email");
        }
    }
}
