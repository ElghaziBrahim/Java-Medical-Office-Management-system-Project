package Client;
import java.io.Serializable;

public class Client  implements Serializable{
	private static final long serialVersionUID = 1L;
	private long id;
    private int version;
    private String titre;
    private String nom;
    private String prenom;

    public Client(long id, int version, String titre, String nom, String prenom) {
        this.id = id;
        
        this.version = version;
        this.titre = titre;
        this.nom = nom;
        this.prenom = prenom;
    }
    public Client(int version, String titre, String nom, String prenom) {
        this.id = 0;
        this.version = version;
        this.titre = titre;
        this.nom = nom;
        this.prenom = prenom;
    }

    public long getId(){  return id; }

    public void setId(long id){  this.id = id; }

    public int getVersion(){  return version; }

    public void setVersion(int version){  this.version = version;  }

    public String getTitre(){   return titre;  }

    public void setTitre(String titre){     this.titre = titre;  }

    public String getNom(){     return nom; }

    public void setNom(String nom){    this.nom = nom; }

    public String getPrenom(){      return prenom;   }

    public void setPrenom(String prenom){     this.prenom = prenom; }

}
