package by.bsu.salatmachine.controller;

import by.bsu.salatmachine.controller.commands.Command;
import by.bsu.salatmachine.controller.commands.LoginCommand;
import by.bsu.salatmachine.controller.commands.NoCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 28.11.11
 * Time: 23:01
 */
public class RequestHelper {
    private static RequestHelper instance = null;
    HashMap<String, Command> commands =
            new HashMap<String, Command>();

    private RequestHelper() {
//заполнение таблицы командами
        commands.put("login", new LoginCommand());
    }

    public Command getCommand(HttpServletRequest request) {
//извлечение команды из запроса
        String action = request.getParameter("command");
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
