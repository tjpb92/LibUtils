package utils;

import java.io.IOException;
import java.sql.*;

/**
 * Class that build a Connection to a given database.
 * Handle Informix or MySQL drivers.
 * @author Thierry Baribaud.
 * @version October 2015.
 */
public class DBManager {

  /**
   * Connection to the database.
   */
  private static Connection MyConnection;

  /**
   * Class builder.
   * @param MyDBServer Database server to connect to.
   * @throws ClassNotFoundException
   * @throws java.sql.SQLException
   */
  public DBManager(DBServer MyDBServer)
    throws ClassNotFoundException, SQLException {

    System.out.println("Trying to connect to database " + 
      MyDBServer.getDbName() + " on " + 
      MyDBServer.getName() + " ...");

//  "jdbc:mysql://localhost:3306/fildb"

    System.out.println("Trying to load " + MyDBServer.getDriverName() + " driver ...");
    Class.forName(MyDBServer.getDriverClass());
    System.out.println(MyDBServer.getDriverName() + " driver loaded.");
    System.out.println("Trying to establish connection to database ...");
    MyConnection = DriverManager.getConnection(MyDBServer.getConnectionURL());
    System.out.println("Connection to database established.");

/*
    Enumeration Names = DriverManager.getDrivers();
    Object DriverAsObject;

    while (Names.hasMoreElements()) {
      DriverAsObject = Names.nextElement();
      System.out.println("JDBC Driver=" + DriverAsObject);
    }
*/
  }

  /** 
   * @return MyConnection ressource to connect to a given database.
   */
 public Connection getConnection() {
   return(MyConnection);
   }

  /**
   * Main method to test DBManager class.
   * @param Args command line arguments.
   * First argument must be the type of Database server : dev/pre-prod/prod.
   * Second argument must be the name of the application properties file to load.
   */
  public static void main(String[] Args) {
    ApplicationProperties MyApplicationProperties;
    DBServer MyDBServer;
    DBManager MyDBManager;
    Connection MyDBConnection;
    long i;

    if (Args.length != 2) {
      System.out.println("Usage : java DBManager server-type filename");
      System.exit(0);
    }

    try {
      MyApplicationProperties = new ApplicationProperties(Args[1]);

      MyDBServer = new DBServer(Args[0], MyApplicationProperties);

      MyDBManager = new DBManager(MyDBServer);
      MyDBConnection = MyDBManager.getConnection();
      }
    catch (IOException | DBServerException | ClassNotFoundException | SQLException MyException) {
      System.out.println("Problem while creating DBManager " + MyException);
      MyException.printStackTrace();
    }
  }
}
