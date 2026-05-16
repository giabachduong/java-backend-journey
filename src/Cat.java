public class Cat extends Animal{

    int lives = 9;

    Cat(String name){
        super(name);
    }

    void speak(){
        System.out.println("The cat goes meow");
    }
}
