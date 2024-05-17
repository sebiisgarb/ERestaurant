import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class FileManager {

    public static List<String> readFile(File file){

        try{
            return Files.readAllLines(file.toPath());
        }catch (IOException ex){
            System.out.println("Eroare la citirea din fisier");
        }
        return null;
    }

    public static void writeFile(File file, String panel){
        PrintWriter writer = null;

        if(panel.equals("Angajati")){
            try{
                writer = new PrintWriter(new FileWriter(file));

                for(Angajat i: Main.Staff.angajati){
                    writer.printf("%s %s %s",((Chelner) i).nume, ((Chelner) i).prenume, ((Chelner) i).ID);
                    writer.println();
                }
            }catch (IOException ex){
                System.out.println("Eroare la scrierea in fisier");
            }
        }
        else{
            try{
                writer = new PrintWriter(new FileWriter(file));

                for(Produs i: Main.catalog.lista){
                    writer.printf("%s %s %s %s %s",i.nume,i.cantitate,i.unitateMasura,i.pret,i.categorie);
                    writer.println();
                }
            }catch (IOException ex){
                System.out.println("Eroare la scrierea in fisier");
            }
        }
        writer.close();
    }

    public static void encodeFile(){
        try{
            XMLEncoder encoder = new XMLEncoder(new FileOutputStream("comenzi.xml"));
            encoder.writeObject(Main.comenzi);
            encoder.close();
        }catch (IOException e){
            System.out.println("Eroare la encodarea din fisier");
        }
    }

    public static ArrayList<Comanda> decodeFile(){
        try{
            XMLDecoder decoder = new XMLDecoder(new FileInputStream("comenzi.xml"));
            ArrayList<Comanda> temp = (ArrayList<Comanda>) decoder.readObject();
            decoder.close();
            return temp;
        }catch (IOException e){
            System.out.println("Eroare la decodarea din fisier");
            return null;
        }
    }
}
