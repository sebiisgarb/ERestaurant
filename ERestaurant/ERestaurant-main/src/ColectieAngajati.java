import java.util.*;
import java.io.File;

public class ColectieAngajati {
    protected ArrayList<Chelner> angajati = new ArrayList();

    public ColectieAngajati(){
    }

    public void adaugaAngajat(String nume, String prenume){
        angajati.add(new Chelner(nume, prenume));
    }

    public void stergereAngajat(int n){

        angajati.remove(n);
        Chelner.globalID = 1;


        for(Chelner i: angajati){
            i.ID = Chelner.globalID++;
        }
        PanouAdmin.resetTabel(PanouChelneri.model);
        PanouAdmin.adaugaAngajatiTabel();
    }

    public void modificaAngajat(int row, int column){
        angajati.get(row).nume = PanouChelneri.model.getValueAt(row,0).toString();
        angajati.get(row).prenume = PanouChelneri.model.getValueAt(row,1).toString();
    }

    public void incarcaAngajati(){
        List<String> input = FileManager.readFile(new File("Angajati.txt"));
        if(input == null)
            return;

            for(String i: input){
                String[] cuvinte = i.split(" ");
                angajati.add(new Chelner(cuvinte[0],cuvinte[1],Integer.parseInt(cuvinte[2])));
            }

    }
}
