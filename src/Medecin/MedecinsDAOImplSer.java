package Medecin;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MedecinsDAOImplSer implements MedecinsDAOInterface, Serializable {
    private static final long serialVersionUID = 1L;
    private static final String FILE_PATH = "medecins.ser";
    private List<Medecin> medecinsList;

    public MedecinsDAOImplSer() {
        this.medecinsList = loadMedecinsFromFile();
    }

    @Override
    public List<Medecin> getAllMedecins() {
        return new ArrayList<>(medecinsList);
    }

    @Override
    public Medecin getMedecinById(long id) {
        for (Medecin medecin : medecinsList) {
            if (medecin.getId() == id) {
                return medecin;
            }
        }
        return null;
    }

    @Override
    public void addMedecin(Medecin medecin) {
        long newId = System.currentTimeMillis();
        medecin.setId(newId);

        medecinsList.add(medecin);
        saveMedecinsToFile();
    }

    @Override
    public void updateMedecin(Medecin updatedMedecin) {
        for (int i = 0; i < medecinsList.size(); i++) {
            if (medecinsList.get(i).getId() == updatedMedecin.getId()) {
                medecinsList.set(i, updatedMedecin);
                saveMedecinsToFile();
                return;
            }
        }
    }

    @Override
    public void deleteMedecin(long id) {
        medecinsList.removeIf(medecin -> medecin.getId() == id);
        saveMedecinsToFile();
    }

    private List<Medecin> loadMedecinsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            Object obj = ois.readObject();

            if (obj instanceof List<?>) {
                return (List<Medecin>) obj;
            } else {
                System.err.println("Unexpected object type found while deserializing Medecins.");
                return new ArrayList<>();
            }
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    private void saveMedecinsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(medecinsList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
