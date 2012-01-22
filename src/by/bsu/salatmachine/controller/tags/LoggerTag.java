package by.bsu.salatmachine.controller.tags;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 29.11.11
 * Time: 22:23
 */
public class LoggerTag extends BodyTagSupport {
    private static final String FILE_SEPARATOR = "file.separator";
    private static final String WEB_INF_CLASSES = "WEB-INF/classes/";
    private static final String THE_VALUE_GIVEN_FOR_THE_LEVEL_ATTRIBUTE_IS_INVALID = "The value given for the level attribute is invalid.";
    private Logger log = null;
    private String configFile = null;
    private String level = null;
    private static final String[] LEVELS =
            {"debug", "info", "warn", "error", "fatal"};

    public void setConfigFile(String fileName) {

        this.configFile = fileName;

    }

    public void setLevel(String level) {

        this.level = level;

    }

    public int doEndTag() throws JspException {

        String realPath = pageContext.getServletContext().getRealPath("/");
        String fileSep = System.getProperty(FILE_SEPARATOR);

        if (realPath != null && (!realPath.endsWith(fileSep))) {
            realPath = realPath + fileSep;
        }

        //configure the logger if the user provides this optional attribute
        if (configFile != null)
            PropertyConfigurator.configure(realPath +
                    WEB_INF_CLASSES + configFile);

        //throw an exception if the tag user provides an invalid level,
        //something other than DEBUG, INFO, WARN, ERROR, or FATAL

        level = level.toLowerCase();

        if (!contains(level))
            throw new JspException(
                    THE_VALUE_GIVEN_FOR_THE_LEVEL_ATTRIBUTE_IS_INVALID);


        log = Logger.getLogger(LoggerTag.class);

        String message = getBodyContent().getString().trim();
        Method method;

        try {
            method = log.getClass().getMethod(level, new Class[]{Object.class});
            method.invoke(log, message);
        } catch (Exception e) {
            throw new JspTagException(e.getMessage());
        }

        return EVAL_PAGE;
    } // doEndTag

    public void release() {

        //release resources used by instance variables
        log = null;
        configFile = null;
        level = null;

    }// release

    private boolean contains(String str) {
        for (String LEVEL : LEVELS) {
            if (LEVEL.equals(str))
                return true;
        }
        return false;
    }// contains
}
