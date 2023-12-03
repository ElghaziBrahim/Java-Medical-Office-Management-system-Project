package Creneau;

import java.io.Serializable;
 
public class Creneau implements Serializable{
	private static final long serialVersionUID = 1L;
	private long id;
    private int version;
    private int hdebut;
    private int mdebut;
    private int mfin;
    private int hfin;
    private long id_medecin;
    public Creneau(long id, int version, int hdebut, int mdebut, int mfin, int hfin, long id_medecin) {
        this.id = id;
        this.version = version;
        this.hdebut = hdebut;
        this.mdebut = mdebut;
        this.mfin = mfin;
        this.hfin = hfin;
        this.id_medecin = id_medecin;
    }
    public Creneau(int version, int hdebut, int mdebut, int mfin, int hfin, long id_medecin) {
        this.id = 0;
        this.version = version;
        this.hdebut = hdebut;
        this.mdebut = mdebut;
        this.mfin = mfin;
        this.hfin = hfin;
        this.id_medecin = id_medecin;
    }
    public long getId(){  return id; }

    public void setId(long id){   this.id = id; }

    public int getVersion() { return version;  }

    public void setVersion(int version){ this.version = version; }

    public int getHdebut(){ return hdebut; }

    public void setHdebut(int hdebut) { this.hdebut = hdebut; }

    public int getMdebut(){  return mdebut; }

    public void setMdebut(int mdebut){ this.mdebut = mdebut; }

    public int getMfin(){  return mfin; }

    public void setMfin(int mfin){ this.mfin = mfin; }

    public int getHfin(){ return hfin; }

    public void setHfin(int hfin) { this.hfin = hfin; }

    public long getId_medecin() { return id_medecin; }

    public void setId_medecin(long id_medecin) {  this.id_medecin = id_medecin; }

}