package by.bsu.salatmachine.model.entity;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 04.12.11
 * Time: 15:16
 *
 */
public class User extends AbstractEntity {

    private static final String ADMIN = "Admin";
    private static final String USER = "User";
    private String login;
    private transient String password;
    private Integer money;
    private boolean type;

    public boolean isType() {
        return type;
    }
    public String getType() {
        if (type){
            return ADMIN;
        }else
        return USER;
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

    @Override
    public int compareTo(Object o) {
        User u1 = (User) o;
        return this.getLogin().compareTo(u1.getLogin());
    }
}
