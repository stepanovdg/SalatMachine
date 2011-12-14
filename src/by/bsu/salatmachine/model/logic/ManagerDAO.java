package by.bsu.salatmachine.model.logic;


/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 13.12.11
 * Time: 19:14
 */
public class ManagerDAO {
    private static ManagerDAO instance;
    private  static LoginDAO loginDAO;
    private static ReceptStorageDAO receptStorageDAO;
    private  static ReceptDAO receptDAO;

    public static ManagerDAO getInstance() {
        if (instance == null) {
            instance = new ManagerDAO();
            loginDAO =new LoginDAO();
            receptStorageDAO= new ReceptStorageDAO();
            receptDAO= new ReceptDAO();
        }
        return instance;
    }

    public AbstractDAO getDao(String entity) {
        switch (entity) {
            case "user": {
                return loginDAO;
            }
            case "receptStorage": {
                return receptStorageDAO;
            }
             case "recept": {
                return receptDAO;
            }
            default: return  null;
         }


    }
}
