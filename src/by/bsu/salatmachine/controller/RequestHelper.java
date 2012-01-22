package by.bsu.salatmachine.controller;

import by.bsu.salatmachine.model.commands.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 28.11.11
 * Time: 23:01
 */
public final class RequestHelper {
    private static RequestHelper instance = null;
    private static final String COMMAND = "command";
    private static final String LOGIN = "login";
    private static final String RECEPTST = "receptst";
    private static final String RECEPT = "recept";
    private static final String UNLOGIN = "unlogin";
    private static final String SAVE = "save";
    private static final String COOK = "cook";
    private static final String REFRESH = "refresh";
    private static final String DELETE_REC = "deleteRec";
    private static final String DELETE_RECEPT_ST = "deleteReceptSt";
    private static final String NEW_RECEPT = "newRecept";
    private static final String NEW_RECEPT_ST = "newReceptSt";
    private static final String MONEY_AMOUNT = "moneyAmount";
    private static final String VISIBILITY_CHANGE = "visibilityChange";
    HashMap<String, Command> commands =
            new HashMap<>();

    private RequestHelper() {
//заполнение таблицы командами
        commands.put(LOGIN, new LoginCommand());
        commands.put(RECEPTST, new ReceptStorageCommand());
        commands.put(RECEPT, new ReceptCommand());
        commands.put(UNLOGIN, new UnloginCommand());
        commands.put(SAVE, new SaveCommand());
        commands.put(COOK, new CookCommand());
        commands.put(REFRESH, new RefreshCommand());
        commands.put(DELETE_REC, new DeleteReceptCommand());
        commands.put(DELETE_RECEPT_ST, new DeleteReceptStorageCommand());
        commands.put(NEW_RECEPT, new NewReceptCommand());
        commands.put(NEW_RECEPT_ST, new NewReceptStCommand());
        commands.put(MONEY_AMOUNT, new MoneyAmountCommand());
        commands.put(VISIBILITY_CHANGE, new VisibilityChangeCommand());
    }

    public Command getCommand(HttpServletRequest request) {
        //извлечение команды из запроса
        String action = request.getParameter(COMMAND);
        //получение объекта, соответствующего команде
        Command command = commands.get(action);
        if (command == null) {
            //если команды не существует в текущем объекте
            command = new NoCommand();
        }
        return command;
    }

    //создание единственного объекта по шаблону Singleton
    public static RequestHelper getInstance() {
        if (instance == null) {
            instance = new RequestHelper();
        }
        return instance;
    }
}
