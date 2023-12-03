package Rv;

import java.util.Date;
import java.io.Serializable;


public class Rv implements Serializable{
	private static final long serialVersionUID = 1L;
	private long id;
    private Date jour;
    private long id_client;
    private long id_creneau;

    public Rv(long id, Date jour, long id_client, long id_creneau) {
        this.id = id;
        this.jour = jour;
        this.id_client = id_client;
        this.id_creneau = id_creneau;
    }
    public Rv(Date jour, long id_client, long id_creneau) {
        this.id = 0;
        this.jour = jour;
        this.id_client = id_client;
        this.id_creneau = id_creneau;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getJour() {
        return jour;
    }

    public void setJour(Date jour) {
        this.jour = jour;
    }

    public long getId_client() {
        return id_client;
    }

    public void setId_client(long id_client) {
        this.id_client = id_client;
    }

    public long getId_creneau() {
        return id_creneau;
    }

    public void setId_creneau(long id_creneau) {
        this.id_creneau = id_creneau;
    }
}
