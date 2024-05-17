import java.util.ArrayList;
import java.io.File;
import java.util.List;

public class Catalog {

    protected ArrayList<Produs> lista = new ArrayList<>();

    public void stergeProdus(int n){
        lista.remove(n);
        PanouAdmin.resetTabel(PanouCatalog.model);
        PanouAdmin.adaugaProdusTabel();
    }

    public void adaugaProdus(String nume, String cantitate, String unitate, String pret,  String categorie){
        lista.add(new Produs(nume,Float.parseFloat(cantitate),unitate,Float.parseFloat(pret),categorie));
    }
    public void modificaProdus(int row,int column){
        lista.get(row).nume = PanouCatalog.model.getValueAt(row, 0).toString();
        lista.get(row).cantitate = Float.parseFloat(PanouCatalog.model.getValueAt(row, 1).toString());
        lista.get(row).unitateMasura = PanouCatalog.model.getValueAt(row, 2).toString();
        lista.get(row).pret = Float.parseFloat(PanouCatalog.model.getValueAt(row, 3).toString());
    }

    public void incarcaProduse(){
        List<String> input = FileManager.readFile(new File("Produse.txt"));
        if(input == null) return;


        for(String i : input) {
            String[] cuvinte = i.split(" ");
            lista.add(new Produs(cuvinte[0],Float.parseFloat(cuvinte[1]),cuvinte[2],Float.parseFloat(cuvinte[3]),cuvinte[4]));
        }
    }
    public int getIndex(String nume){
        for(int i = 0 ; i < lista.size() ; i++){
            if(lista.get(i).nume.equals(nume)){
                return i;
            }
        }
        return -1;
    }
}