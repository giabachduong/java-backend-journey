public class Developer extends Employee{
    Developer(String name, int salary){
        super(name, salary);
    }
    @Override
    public void work(){
        System.out.println("Developers are writing code");
    }

}
