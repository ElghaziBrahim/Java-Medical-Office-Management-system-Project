package Factory;

public class DAOFactorySelector {
    public static DAOFactory getDAOFactory(String storageType) {
        if (storageType.equals("database")) {
            return new DatabaseDAOFactory();
        } else if (storageType.equals("serialized")) {
            return new SerializedDAOFactory();
        } else {
            throw new IllegalArgumentException("Invalid storage type: " + storageType);
        }
    }
}