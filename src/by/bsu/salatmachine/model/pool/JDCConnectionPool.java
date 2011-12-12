package by.bsu.salatmachine.model.pool;

/**
 * Created by IntelliJ IDEA.
 * UserDAO: Stepanov Dmitriy
 * Date: 01.12.11
 * Time: 12:46
 *
 */
import java.sql.*;
import java.util.*;

class ConnectionReaper extends Thread {

    private JDCConnectionPool pool;
    private final long delay=300000;

    ConnectionReaper(JDCConnectionPool pool) {
        this.pool=pool;
    }

    public void run() {
        while(true) {
           try {
              sleep(delay);
           } catch( InterruptedException e) { }
           pool.reapConnections();
        }
    }
}

public class JDCConnectionPool {

   private Vector<JDCConnection> connections;
   private String url, user, password;
   final private long timeout=60000;
   private ConnectionReaper reaper;
   final private int poolsize=10;

   public JDCConnectionPool(String url, String user, String password) {
      this.url = url;
      this.user = user;
      this.password = password;
      connections = new Vector<JDCConnection>(poolsize);
      reaper = new ConnectionReaper(this);
      reaper.start();
   }

   public synchronized void reapConnections() {

      long stale = System.currentTimeMillis() - timeout;
      Enumeration<JDCConnection> connlist = connections.elements();

      while((connlist != null) && (connlist.hasMoreElements())) {
          JDCConnection conn = connlist.nextElement();

          if((conn.inUse()) && (stale >conn.getLastUse()) &&
                                            (!conn.validate())) {
 	      removeConnection(conn);
         }
      }
   }

   public synchronized void closeConnections() {

      Enumeration<JDCConnection> connlist = connections.elements();

      while((connlist != null) && (connlist.hasMoreElements())) {
          JDCConnection conn = connlist.nextElement();
          removeConnection(conn);
      }
   }

   private synchronized void removeConnection(JDCConnection conn) {
       connections.removeElement(conn);
   }


   public synchronized Connection getConnection() throws SQLException {

       JDCConnection c;
       for(int i = 0; i < connections.size(); i++) {
           c = connections.elementAt(i);
           if (c.lease()) {
              return c;
           }
       }

       Connection conn = DriverManager.getConnection(url, user, password);
       c = new JDCConnection(conn, this);
       c.lease();
       connections.addElement(c);
       return c;
  }

   public synchronized void returnConnection(JDCConnection conn) {
      conn.expireLease();
   }
}
