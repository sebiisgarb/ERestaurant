import java.util.Objects;

public class Produs {

    public String nume, unitateMasura;
    public float pret, cantitate;

    enum Categorie{

        Racoritoare,
        Pizza,
        Burger,
        Salata,
        Desert
    }

    public Categorie categorie;

    public Produs(String nume, float cantitate, String unitateMasura, float pret, String categorie){

        this.nume=nume;
        this.unitateMasura=unitateMasura;
        this.pret=pret;
        this.cantitate=cantitate;

        switch (categorie){
            case "Racoritoare" -> this.categorie = Categorie.Racoritoare;
            case "Pizza" -> this.categorie = Categorie.Pizza;
            case "Burger" -> this.categorie = Categorie.Burger;
            case "Salata" -> this.categorie = Categorie.Salata;
            case "Desert" -> this.categorie = Categorie.Desert;
        }
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produs other = (Produs) obj;
        return Objects.equals(this.nume, other.nume);
    }

    @Override
    public int hashCode(){
        return (int)pret + (int)cantitate;
    }


}

