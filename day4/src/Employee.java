public class Employee {
    protected String name;
    protected int salary;

    Employee(String name, int salary){
        this.name = name;
        this.salary = salary;
    }
    void work(){
        System.out.println("People is working");
    }

    void displayInfo(){
        System.out.println(name);
        System.out.println(salary);
    }
}
