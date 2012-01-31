package by.bsu.salatmachine.controller.manager;

import org.apache.log4j.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 20.01.12
 * Time: 21:18
 */
public class LoggerManager {

    private static LoggerManager instance;
    public static final String ERROR = "error";
    public static final String DEBUG = "debug";
    public static final String INFO = "info";
    public static final String WARN = "warn";
    public static final String FATAL = "fatal";
    private Logger logger = null;
    private String configFile = "C:\\Users\\Dmitriy\\Documents\\Java\\Intelig.Idea\\EpamWebProject\\web\\WEB-INF\\classes\\log4j.properties";
    private String level = null;



    public static LoggerManager getInstance() {
        if (instance == null) {
            instance = new LoggerManager();
            instance.logger = Logger.getLogger(LoggerManager.class);

        }
        return instance;
    }

    public void logg(String level, String message){
        switch (level.toLowerCase()) {
            case ERROR: {
                logger.error(message);
                break;
            }
            case DEBUG: {
                logger.debug(message);
                break;
            }
            case INFO: {
                logger.info(message);
                break;
            }
            case WARN: {
                logger.warn(message);
                break;
            }
            case FATAL: {
                logger.fatal(message);
                break;
            }
            default: {
                logger.info(message);
            }
        }

    }
}
