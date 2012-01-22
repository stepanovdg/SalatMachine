package by.bsu.salatmachine.model.commands;

import by.bsu.salatmachine.controller.manager.ConfigurationManager;
import by.bsu.salatmachine.controller.manager.MessageManager;
import by.bsu.salatmachine.model.entity.Recept;
import by.bsu.salatmachine.model.entity.TreeSetOfEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 29.12.11
 * Time: 10:42
 */
public class NewReceptCommand implements Command{

    private static final String RECEPT_POSITION = "receptPosition";
    private static final String ID_VEGET_ENUM = "idVegetEnum";
    private static final String RECEPT_COUNT = "receptCount";
    private static final String PROCESS = "process";
    private static final String RECEPT_TIME = "receptTime";
    private static final String ERROR_MESSAGE = "errorMessage";
    private static final String RECEPT_CREATING_ERROR_MESSAGE = "RECEPT_CREATING_ERROR_MESSAGE";
    private static final String RECEPT = "recept";
    private static final String RECEPT_PAGE_PATH = "RECEPT_PAGE_PATH";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        TreeSetOfEntity receptst;
        Integer idRecept;
        Integer idOrder;
        Integer idVegetEnum;
        Double count;
        String process;
        Integer time;
        try {
            receptst = (TreeSetOfEntity) request.getSession().getAttribute(RECEPT);
            idRecept = ((Recept) receptst.toArray()[0]).getIdRecept();
            idOrder = Integer.valueOf(request.getParameter(RECEPT_POSITION));
            idVegetEnum = Integer.valueOf(request.getParameter(ID_VEGET_ENUM));
            count = Double.valueOf(request.getParameter(RECEPT_COUNT));
            process = (request.getParameter(PROCESS));
            time = Integer.valueOf(request.getParameter(RECEPT_TIME));
        } catch (NumberFormatException e) {
           request.getSession().setAttribute(ERROR_MESSAGE,
                            MessageManager.getInstance()
                                    .getProperty(RECEPT_CREATING_ERROR_MESSAGE));
                  return  ConfigurationManager.getInstance()
                            .getProperty(RECEPT_PAGE_PATH);
        }
        Recept recept = new Recept(idRecept,idOrder,idVegetEnum,count,process,time);
        receptst.add(recept);
        request.getSession().setAttribute(RECEPT, receptst);
        page = ConfigurationManager.getInstance().getProperty(RECEPT_PAGE_PATH);
        return page;

    }
}
