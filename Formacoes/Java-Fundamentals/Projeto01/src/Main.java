import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Employee p1 = new Manager();
        p1.setAge(30);
        p1.setAddress("Rua alguma");
        p1.setCode("123456");
        p1.setName("Robson");
        p1.setSalary(4000.0);

        printEmplyoee(p1);
    }

    public static void printEmplyoee(Employee empregado){
        switch (empregado){
            case Manager mg -> {
                mg.setPassword("12345");
                mg.setComission(0.2);
                mg.setLogin("Lakan");

                System.out.println(mg.getLogin());
                System.out.println(mg.getFullSalary());
            }
            case Salesman sl -> {
                sl.setPercentPerSold(10.0);

                System.out.println(sl.getPercentPerSold());
            }
        }
    }

}
