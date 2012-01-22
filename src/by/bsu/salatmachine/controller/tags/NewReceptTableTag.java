package by.bsu.salatmachine.controller.tags;

import by.bsu.salatmachine.controller.manager.ConfigurationManager;
import by.bsu.salatmachine.enums.VegetEnum;
import by.bsu.salatmachine.enums.VegetMethodOfPreparationEnum;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 28.12.11
 * Time: 21:00
 */
public class NewReceptTableTag extends TagSupport {

    private static final String ADD_RECEPT = "addRecept";
    private static final String SERVLET = "SERVLET";

    @Override
    public int doStartTag() throws JspException {
        try {
            Boolean add = (Boolean) pageContext.getSession().getAttribute(ADD_RECEPT);
            if (add == null) {
                add = false;
            }
            if (add) {
                pageContext.getOut().write("<tr>\n" +
                        "                                <form name=\"addRecept\" method=\"POST\" action=\""+ ConfigurationManager.getInstance().getProperty(SERVLET)+"\">\n " +
                        "                                    <td>\n" +
                        "                                        <input type=\"text\" name=\"receptPosition\" value=\"\">" +
                        "                                    </td>\n " +
                        "                                    <td>\n" +
                        "                                        <select name='idVegetEnum'  required>\n");
                for (VegetEnum e : VegetEnum.values()) {
                    pageContext.getOut().write("<option "+"value='"+e.ordinal()+"'>" + e + "</option>\n");
                }
                pageContext.getOut().write("</select>\n" +
                        "                </td>\n" +
                        "                <td>\n" +
                        "                    <input type=\"text\" name=\"receptCount\" value=\"\">\n" +
                        "                </td>\n" +
                        "                <td>\n" +
                        "                    <select name='process' required>\n"
                );
                for (VegetMethodOfPreparationEnum e : VegetMethodOfPreparationEnum.values()) {
                    pageContext.getOut().write("<option "+"value='"+e+"'>" + e + "</option>\n");
                }
                pageContext.getOut().write("</select>\n" +
                        "                </td>\n" +
                        "                <td>\n" +
                        "                    <input type=\"text\" name=\"receptTime\" value=\"\">\n" +
                        "                </td>\n" +
                        "                <td align=\"center\">\n" +
                        "                    <input type=\"reset\" value=\"Reset\">\n" +
                        "                    <input type=\"hidden\" name=\"command\" value=\"newRecept\">\n" +
                        "                    <input type=\"submit\" value=\"Add\">\n" +
                        "            </form>\n" +
                        "            </td>\n" +
                        "        </tr>\n");
                pageContext.getSession().setAttribute(ADD_RECEPT,false);
            }

        } catch (IOException e) {
           throw new JspTagException(e.getMessage());
        }

        return SKIP_BODY;
    }
}
