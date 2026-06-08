import java.util.ArrayList;
import java.util.List;

public class Main {
    public static<T> void printList(List<T> list){
        for(T item: list){
            System.out.println(item);
        }

    }

    public static void main(String[] args) {
        Box<String> stringBox = new Box<>();
        stringBox.set("Haha");
        System.out.println("Value: " + stringBox.getValue());

        List<String> names = new ArrayList<>();
        names.add("Bach");
        names.add("An");
        printList(names);

        List<Integer> numbers = new ArrayList<>();
        numbers.add(100);
        numbers.add(200);
        printList(numbers);


    }
}