package by.bsu.salatmachine.model.commands;

import by.bsu.salatmachine.model.entity.TreeSetOfEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 20.12.11
 * Time: 17:21
 */
public class DeleteReceptCommand implements Command{

    private static final String ID_ORDER_TO_DEL = "idOrderToDel";
    private static final String RECEPT = "recept";
    private static final String PAGE = "page";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       TreeSetOfEntity entityIF= (TreeSetOfEntity) request.getSession().getAttribute(RECEPT);
       Integer integer = Integer.valueOf(request.getParameter(ID_ORDER_TO_DEL));
       entityIF.delete(integer);
       request.getSession().setAttribute(RECEPT,entityIF);
       return request.getParameter(PAGE);
    }
}
