package by.bsu.salatmachine.model.logic;


/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 13.12.11
 * Time: 19:14
 */
public final class ManagerDAO {
    private static ManagerDAO instance;
    private static LoginDAO loginDAO;
    private static ReceptStorageDAO receptStorageDAO;
    private static ReceptDAO receptDAO;
    private static VegetableDAO vegetableDAO;
    public static final String USER = "user";
    public static final String RECEPT_STORAGE = "receptStorage";
    public static final String RECEPT = "recept";
    public static final String VEGETABLE = "vegetable";

    public static ManagerDAO getInstance() {
        if (instance == null) {
            instance = new ManagerDAO();
            loginDAO = new LoginDAO();
            receptStorageDAO = new ReceptStorageDAO();
            receptDAO = new ReceptDAO();
            vegetableDAO = new VegetableDAO();
        }
        return instance;
    }

    public AbstractDAO getDao(String entity) {
        switch (entity) {
            case USER: {
                return loginDAO;
            }
            case RECEPT_STORAGE: {
                return receptStorageDAO;
            }
            case RECEPT: {
                return receptDAO;
            }
            case VEGETABLE: {
                return vegetableDAO;
            }
            default:
                return null;
        }


    }
}
