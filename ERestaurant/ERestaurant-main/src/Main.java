import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class Main {
    protected static Catalog catalog = new Catalog();
    protected static ColectieAngajati Staff = new ColectieAngajati();
    protected static String pin;
    protected static ArrayList<Comanda> comenzi = new ArrayList<>();

    public static void main(String[] args) {


        pin = Objects.requireNonNull(FileManager.readFile(new File("pin.txt"))).get(0);
        catalog.incarcaProduse();
        Staff.incarcaAngajati();

        Interfata.getInstance();
    }
}
