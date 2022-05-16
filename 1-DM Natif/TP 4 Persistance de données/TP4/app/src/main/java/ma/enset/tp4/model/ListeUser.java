package ma.enset.tp4.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListeUser implements Serializable {
    List<Etudiant> etudiants=new ArrayList<Etudiant>();

    public ListeUser() {
    }
    public ListeUser(List<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }

    public List<Etudiant> getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(List<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }

    @Override
    public String toString() {
        return "ListeUser{" +
                "etudiants=" + etudiants +
                '}';
    }
}
