package ma.enset.tp4.model;
import java.io.Serializable;
public class Etudiant implements Serializable {
    private  int age;
    private  String Nom;
    private  String Prenom;
    public Etudiant() {

    }
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "age=" + age +
                ", Nom='" + Nom + '\'' +
                ", Prenom='" + Prenom + '\'' +
                '}';
    }

}
