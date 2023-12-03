package Rv;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RvDAOImplSer implements RvDAOInterface, Serializable {
    private static final long serialVersionUID = 1L;
    private static final String FILE_PATH = "rvs.ser";
    private List<Rv> rvList;

    public RvDAOImplSer() {
        this.rvList = loadRvsFromFile();
    }

    @Override
    public List<Rv> getAllRvs() {
        return new ArrayList<>(rvList);
    }

    @Override
    public Rv getRvById(long id) {
        for (Rv rv : rvList) {
            if (rv.getId() == id) {
                return rv;
            }
        }
        return null;
    }

    @Override
    public void addRv(Rv rv) {
        long newId = System.currentTimeMillis();
        rv.setId(newId);

        rvList.add(rv);
        saveRvsToFile();
    }

    @Override
    public void updateRv(Rv updatedRv) {
        for (int i = 0; i < rvList.size(); i++) {
            if (rvList.get(i).getId() == updatedRv.getId()) {
                rvList.set(i, updatedRv);
                saveRvsToFile();
                return;
            }
        }
    }

    @Override
    public void deleteRv(long id) {
        rvList.removeIf(rv -> rv.getId() == id);
        saveRvsToFile();
    }

    private List<Rv> loadRvsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            Object obj = ois.readObject();

            if (obj instanceof List<?>) {
                return (List<Rv>) obj;
            } else {
                System.err.println("Unexpected object type found while deserializing Rvs.");
                return new ArrayList<>();
            }
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    private void saveRvsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(rvList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
