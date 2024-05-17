public class Chelner extends Angajat{

    protected int ID;
    protected static int globalID = 1;

    Chelner(String nume, String prenume){
        super(nume,prenume);
        this.ID = globalID++;
    }

    Chelner(String nume, String prenume, int ID){
        super(nume,prenume);
        this.ID = ID;
        globalID++;
    }

    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append(super.nume);
        sb.append(" ");
        sb.append(super.prenume);
        sb.append(" ");
        sb.append(this.ID);
        return sb.toString();
    }



}
