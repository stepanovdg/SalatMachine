package by.bsu.salatmachine.model.registration;

/**
 * Created by IntelliJ IDEA.
 * User: Stepanov Dmitriy
 * Date: 01.12.11
 * Time: 12:52
 *
 */

// uses our connection pool

public class RegistrationBean  {/*

    protected transient EntityContext ctx;
    public String theuser, password, creditcard, emailaddress;
    public double balance;

    static {
        try {
            new JDCConnectionDriver("COM.cloudscape.core.JDBCDriver",
                    "jdbc:cloudscape:ejbdemo", "none", "none");
        } catch (Exception e) {
        }
    }


    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:jdc:jdcpool");
    }


    public boolean verifyPassword(String password) throws RemoteException {
        if (this.password.equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    public String getEmailAddress() throws RemoteException {
        return emailaddress;
    }

    public String getUser() throws RemoteException {
        return theuser;
    }

    public int adjustAccount(double amount) throws RemoteException {
        balance = balance + amount;
        return (0);
    }

    public double getBalance() throws RemoteException {
        return balance;
    }

    public RegistrationPK ejbCreate(String theuser, String password,
                                    String emailaddress, String creditcard)
            throws CreateException, RemoteException {

        System.out.println("registration create");
        this.theuser = theuser;
        this.password = password;
        this.emailaddress = emailaddress;
        this.creditcard = creditcard;
        this.balance = 0;

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = getConnection();
            ps = con.prepareStatement("insert into registration (theuser,password, emailaddress," +
                    " creditcard, balance)values (?, ?, ?, ?, ?)");
            ps.setString(1, theuser);
            ps.setString(2, password);
            ps.setString(3, emailaddress);
            ps.setString(4, creditcard);
            ps.setDouble(5, balance);
            if (ps.executeUpdate() != 1) {
                System.out.println("registration create failed");
                throw new CreateException("JDBC did not create any row");
            }
            RegistrationPK primaryKey = new RegistrationPK();
            primaryKey.theuser = theuser;
            return primaryKey;
        } catch (CreateException ce) {
            throw ce;
        } catch (SQLException sqe) {
            throw new CreateException(sqe.getMessage());
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception ignore) {
            }
        }
    }


    public void ejbPostCreate(String theuser, String password,
                              String emailaddress, String creditcard)
            throws CreateException, RemoteException {
    }

    public void setEntityContext(javax.ejb.EntityContext ctx)
            throws RemoteException {
        this.ctx = ctx;
    }

    public void unsetEntityContext() throws RemoteException {
        ctx = null;
    }

    public void ejbRemove() throws RemoteException, RemoveException {
    }

    public void ejbActivate() throws RemoteException {
    }

    public void ejbPassivate() throws RemoteException {
    }

    public void ejbLoad() throws RemoteException {
        System.out.println("registration load");
        try {
            refresh((RegistrationPK) ctx.getPrimaryKey());
        } catch (FinderException fe) {
            throw new RemoteException(fe.getMessage());
        }
    }

    public void ejbStore() throws RemoteException {
        System.out.println("registration store");
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps = con.prepareStatement("update registration set password = ?,emailaddress = ?, " +
                    "creditcard = ?, balance = ? where theuser = ?");
            ps.setString(1, password);
            ps.setString(2, emailaddress);
            ps.setString(3, creditcard);
            ps.setDouble(4, balance);
            ps.setString(5, theuser);
            int i = ps.executeUpdate();
            if (i == 0) {
                throw new RemoteException("ejbStore: Registration (" + theuser
                        + ") not updated");
            }
        } catch (RemoteException re) {
            throw re;
        } catch (SQLException sqe) {
            throw new RemoteException(sqe.getMessage());
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception ignore) {
            }
        }
    }

    public RegistrationPK ejbFindByPrimaryKey(RegistrationPK pk)
            throws FinderException, RemoteException {

        if ((pk == null) || (pk.theuser == null)) {
            throw new FinderException("primary key cannot be null");
        }
        refresh(pk);
        return pk;
    }

    private void refresh(RegistrationPK pk)
            throws FinderException, RemoteException {

        if (pk == null) {
            throw new RemoteException("primary key cannot be null");
        }
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps = con.prepareStatement("select password, emailaddress,creditcard," +
                    " balance from registration where theuser = ?");
            ps.setString(1, pk.theuser);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                theuser = pk.theuser;
                password = rs.getString(1);
                emailaddress = rs.getString(2);
                creditcard = rs.getString(3);
                balance = rs.getDouble(4);
            } else {
                throw new FinderException("Refresh: Registration ("
                        + pk.theuser + ") not found");
            }
        } catch (SQLException sqe) {
            throw new RemoteException(sqe.getMessage());
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception ignore) {
            }
        }
    }
                         */
}
