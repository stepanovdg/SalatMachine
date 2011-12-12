package by.bsu.salatmachine.model.entity;

/**
 * Created by IntelliJ IDEA.
 * UserDAO: Stepanov Dmitriy
 * Date: 04.12.11
 * Time: 15:16
 *
 */
public class UserDAO extends AbstractDAO{

    private String login,password;
    private Integer money;
    private boolean type;

    public boolean isType() {
        return type;
    }
    public String getType() {
        if (type){
            return "Admin";
        }else
        return "UserDAO";
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

}
