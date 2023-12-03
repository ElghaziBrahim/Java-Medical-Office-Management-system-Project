package Creneau;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CreneauxDAOImplSer implements CreneauxDAOInterface, Serializable {
    private static final long serialVersionUID = 1L;
    private static final String FILE_PATH = "creneaux.ser";
    private List<Creneau> creneauxList;

    public CreneauxDAOImplSer() {
        this.creneauxList = loadCreneauxFromFile();
    }

    @Override
    public List<Creneau> getAllCreneaux() {
        return new ArrayList<>(creneauxList);
    }

    @Override
    public Creneau getCreneauById(long id) {
        for (Creneau creneau : creneauxList) {
            if (creneau.getId() == id) {
                return creneau;
            }
        }
        return null;
    }

    @Override
    public void addCreneau(Creneau creneau) {
        long newId = System.currentTimeMillis();
        creneau.setId(newId);

        creneauxList.add(creneau);
        saveCreneauxToFile();
    }

    @Override
    public void updateCreneau(Creneau updatedCreneau) {
        for (int i = 0; i < creneauxList.size(); i++) {
            if (creneauxList.get(i).getId() == updatedCreneau.getId()) {
                creneauxList.set(i, updatedCreneau);
                saveCreneauxToFile();
                return;
            }
        }
    }

    @Override
    public void deleteCreneau(long id) {
        creneauxList.removeIf(creneau -> creneau.getId() == id);
        saveCreneauxToFile();
    }

    private List<Creneau> loadCreneauxFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            Object obj = ois.readObject();

            if (obj instanceof List<?>) {
                return (List<Creneau>) obj;
            } else {
                System.err.println("Unexpected object type found while deserializing Creneaux.");
                return new ArrayList<>();
            }
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    private void saveCreneauxToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(creneauxList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
