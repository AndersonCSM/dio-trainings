import br.com.dio.persistence.FilePersistence;
import br.com.dio.persistence.IOFilePersistence;

import java.io.IOException;

public class main {

    public static void main(String[] args) throws IOException {

        FilePersistence persistence = new IOFilePersistence("user.csv");
        System.out.println(persistence.write("Anderson;exemple@exemple.com;15/01/1995"));
        System.out.println(persistence.write("Carlos;exemple@exemple.com;18/04/1945"));
        System.out.println(persistence.write("Kraven;exemple@exemple.com;23/10/1965"));

        System.out.println(persistence.findAll());

    }
}
