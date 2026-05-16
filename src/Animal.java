public class Animal extends Organism{
    protected String name;

    Animal(String name){
        this.name = name;
    }

    void move(){
        System.out.println("This animal is running");
    }

}
