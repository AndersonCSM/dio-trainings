import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(){
        Set<User> users = new HashSet<>();

        users.add(new User(1, "Nom"));
        users.add(new User(2, "Mon"));
        users.add(new User(3, "Rom"));
        users.add(new User(4, "Cast"));

        System.out.println(users.contains(new User(1, "Nom")));

    }
}
