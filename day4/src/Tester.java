public class Tester extends Employee{
    Tester(String name, int salary){
        super(name, salary);

    }
    @Override
    public void work(){
        System.out.println("Testers are testing software");
    }
}
