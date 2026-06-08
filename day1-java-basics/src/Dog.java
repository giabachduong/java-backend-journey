public class Dog extends Animal{
    int lives = 1;

    Dog(String name){
        super(name);
    }
    void speak(){
        System.out.println("The dogs goes woof");
    }
}
