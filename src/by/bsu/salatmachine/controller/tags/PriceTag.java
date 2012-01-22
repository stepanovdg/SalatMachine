package by.bsu.salatmachine.controller.tags;

import by.bsu.salatmachine.enums.VegetEnum;
import by.bsu.salatmachine.enums.VegetMethodOfPreparationEnum;
import by.bsu.salatmachine.model.entity.Recept;
import by.bsu.salatmachine.model.entity.TreeSetOfEntity;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 28.12.11
 * Time: 19:44
 */
public class PriceTag extends TagSupport {

    private static final String RECEPT = "recept";

    public TreeSetOfEntity getRecept() {
        return recept;
    }

    public void setRecept(TreeSetOfEntity recept) {
        this.recept = recept;
    }

    private TreeSetOfEntity recept;
    @Override
    public int doStartTag() throws JspException {
        try {
            recept = (TreeSetOfEntity) pageContext.getSession().getAttribute(RECEPT);
            Double sum = 0.;
            for (Object rec: recept){
                  if(((Recept) rec).getProcess() == VegetMethodOfPreparationEnum.CHOOSE){
                      sum+= VegetEnum.values()[((Recept) rec).getIdVegetEnum()].pricePerKG()*((Recept) rec).getCount();
                  }
            }
            pageContext.getOut().write(sum.toString());
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return SKIP_BODY;
    }

}
