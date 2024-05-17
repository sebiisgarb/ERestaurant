import java.io.Serializable;
import java.util.*;


public class Comanda implements Serializable {
    public Map<Produs, Integer> lista = new HashMap<>();
    public int masa;

    Comanda(int masa) {
        this.masa = masa;
    }

    public Comanda() {

    }

    public void adaugaProdus(String nume) {
        int index = Main.catalog.getIndex(nume);
        int count = lista.getOrDefault(Main.catalog.lista.get(index), -1);
        if (count != -1) {
            lista.put(Main.catalog.lista.get(index), count + 1);
            FileManager.encodeFile();
            PanouComanda.model.setRowCount(0);
            ((PanouMese)(Interfata.getInstance().panouMese)).populeazaTabel();
            return;
        }
        lista.put(Main.catalog.lista.get(index),1);
        FileManager.encodeFile();

        PanouComanda.model.setRowCount(0);
        ((PanouMese)(Interfata.getInstance().panouMese)).populeazaTabel();
    }

    public void stergeProdus(String nume) {
        int index = Main.catalog.getIndex(nume);
        int count = lista.getOrDefault(Main.catalog.lista.get(index), -1);

        if (count > 1) {
            lista.put(Main.catalog.lista.get(index),count-1);

            FileManager.encodeFile();
            PanouComanda.model.setRowCount(0);
            ((PanouMese)(Interfata.getInstance().panouMese)).populeazaTabel();
            return;
        }
        lista.remove(Main.catalog.lista.get(index));
        FileManager.encodeFile();
        PanouComanda.model.setRowCount(0);
        ((PanouMese)(Interfata.getInstance().panouMese)).populeazaTabel();
    }

    public void schimbaMasa(int n) {
        this.masa = n;
    }
}
