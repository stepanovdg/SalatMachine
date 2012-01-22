package by.bsu.salatmachine.exceptions;

import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 15.12.11
 * Time: 15:55
 */
public class DatabaseConnectionException extends SQLException {
    private boolean correctable = false;

    public boolean isCorrectable() {
        return correctable;
    }

    public void setCorrectable(boolean correctable) {
        this.correctable = correctable;
    }


    public DatabaseConnectionException() {
        super();
    }

    public DatabaseConnectionException(String message) {
        super(message);
    }
    public DatabaseConnectionException(SQLException sql) {
        super(sql);
    }
}
