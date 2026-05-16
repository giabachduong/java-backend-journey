public class Manager extends Employee{
    Manager(String name, int salary){
        super(name,salary);
    }

    @Override
    void work(){
        System.out.println("Manager " + name + " manages team");
    }
}
