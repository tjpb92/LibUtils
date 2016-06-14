package utils;

import java.io.IOException;

/*
 * Class that defines an Database Server.
 * Handle DriverName and DriverClass from 2015/10/22.
 * @author Thierry Baribaud
 * @version 1.1
 */
public class DBServer {

  /**
   * Database server name.
   */
  private String Name;

  /**
   * Database server IP address.
   */
  private String IpAddress;

  /**
   * Database server port number.
   */
  private long portNumber;

  /**
   * Database name.
   */
  private String DbName;

  /**
   * User's login to access the database.
   */
  private String UserLogin;

  /**
   * User's password.
   */
  private String UserPassword;

  /**
   * INFORMIXSERVER value.
   */
  private String InformixServer;
  
  /**
   * DriverName value.
   */
  private String DriverName;
  
  /**
   * DriverClass value.
   */
  private String DriverClass;
  
  /**
   * ConnectionURL value.
   */
  private String ConnectionURL;
  
  /**
  * DBServer builder.
  * @param ServerType type of server : dev/prod.
  * @param MyApplicationProperties Application properties.
  * @throws DBServerException en cas d'erreur sur le serveur de base de données.
  */
  public DBServer (String ServerType, ApplicationProperties MyApplicationProperties) 
    throws DBServerException {
    String Value;

    Value = MyApplicationProperties.getProperty(ServerType + ".dbserver.name");
    if (Value != null) {
      setName(Value);
      }
    else {
      throw new DBServerException("Database name undefined");
      }

    Value = MyApplicationProperties.getProperty(ServerType + ".dbserver.ip");
    if (Value != null) {
      setIpAddress(Value);
      }
    else {
      throw new DBServerException("Database IP address undefined");
      }

    Value = MyApplicationProperties.getProperty(ServerType + ".dbserver.port");
    if (Value != null) {
      try {
        setPortNumber(Long.parseLong(Value));
        }
      catch (Exception MyException) {
        throw new DBServerException("Database port number must be an integer");
        }
      }
    else {
      throw new DBServerException("Database port number undefined");
      }

    Value = MyApplicationProperties.getProperty(ServerType + ".dbserver.dbname");
    if (Value != null) {
      setDbName(Value);
      }
    else {
      throw new DBServerException("Database name undefined");
      }

    Value = MyApplicationProperties.getProperty(ServerType + ".dbserver.login");
    if (Value != null) {
      setUserLogin(Value);
      }
    else {
      throw new DBServerException("User's login undefined");
      }

    Value = MyApplicationProperties.getProperty(ServerType + ".dbserver.passwd");
    if (Value != null) {
      setUserPassword(Value);
      }
    else {
      throw new DBServerException("User's password undefined");
      }

    Value = MyApplicationProperties.getProperty(ServerType + ".dbserver.drivername");
    if (Value != null) {
      setDriverName(Value);
      }
    else {
      throw new DBServerException("Driver name undefined");
      }

    Value = MyApplicationProperties.getProperty(ServerType + ".dbserver.driverclass");
    if (Value != null) {
      setDriverClass(Value);
      }
    else {
      throw new DBServerException("Driver class undefined");
      }

    Value = MyApplicationProperties.getProperty(ServerType + ".dbserver.informixserver");
    if (Value != null) {
      setInformixServer(Value);
      }
    else {
      if (DriverName.equals("Informix")) {
        throw new DBServerException("INFORMIXSERVER undefined");
        }
      }
    setConnectionURL();
  }
  
  /**
  * Get Database server name
  * @return Database server name.
  */
  public String getName() {
    return Name;
  }
  
  /**
  * Get database server's IP address
  * @return Database server's IP address.
  */
  public String getIpAddress() {
    return IpAddress;
  }
  
  /**
  * Get Database server's port number.
  * @return Database server's port number.
  */
  public long getPortNumber() {
    return portNumber;
  }
  
  /**
  * Get database server's name.
  * @return Database server's name.
  */
  public String getDbName() {
    return DbName;
  }
  
  /**
  * Get user's login.
  * @return user's login.
  */
  public String getUserLogin() {
    return UserLogin;
  }
  
  /**
  * Get user's password.
  * @return user's password.
  */
  public String getUserPassword() {
    return UserPassword;
  }
  
  /**
  * Get INFORMIXSERVER value.
  * @return INFORMIXSERVER value.
  */
  public String getInformixServer() {
    return InformixServer;
  }
  
  /**
  * Get DriverName value.
  * @return DriverName value.
  */
  public String getDriverName() {
    return DriverName;
  }
  
  /**
  * Get DriverClass value.
  * @return DriverClass value.
  */
  public String getDriverClass() {
    return DriverClass;
  }
  
  /**
  * Get ConnectionURL value.
  * @return ConnectionURL value.
  */
  public String getConnectionURL() {
    return ConnectionURL;
  }
  
  /**
  * Set database server's name
  * @param Name define Database server's name.
  */
  public void setName(String Name) {
    this.Name = Name;
  }
  
  /**
  * Set database server's IP address IPV4
  * @param IpAddress define Database server's IP address.
  */
  public void setIpAddress(String IpAddress) {
    this.IpAddress = IpAddress;
  }
  
  /**
  * Set database server's port number.
  * @param portNumber define Database server's port number.
  */
  public void setPortNumber(long portNumber) {
    this.portNumber = portNumber;
  }

  /**
  * Set database server's name.
  * @param DbName define Database server's name.
  */
  public void setDbName(String DbName) {
    this.DbName = DbName;
  }
  
  /**
  * Set user's login.
  * @param UserLogin define user's login.
  */
  public void setUserLogin(String UserLogin) {
    this.UserLogin = UserLogin;
  }
  
  /**
  * Set user's password.
  * @param UserPassword define user's password.
  */
  public void setUserPassword(String UserPassword) {
    this.UserPassword = UserPassword;
  }
  
  /**
  * Set INFORMIXSERVER value.
  * @param InformixServer define INFORMIXSERVER value.
  */
  public void setInformixServer(String InformixServer) {
    this.InformixServer = InformixServer;
  }
  
  /**
  * Set DriverName value.
  * @param DriverName define DriverName value.
  */
  public void setDriverName(String DriverName) {
    this.DriverName = DriverName;
  }
  
  /**
  * Set DriverClass value.
  * @param DriverClass define DriverClass value.
  */
  public void setDriverClass(String DriverClass) {
    this.DriverClass = DriverClass;
  }
  
  /**
  * Set ConnectionURL value.
  */
  public void setConnectionURL() {

    if (DriverName.equals("MySQL")) {
      ConnectionURL = "jdbc:mysql://" + IpAddress + ":" + portNumber + "/" +
                      DbName + "?user=" + UserLogin + "&password=" + UserPassword; 
      }
    else if (DriverName.equals("Informix")) {
      ConnectionURL = "jdbc:informix-sqli:" + IpAddress + ":" + portNumber + "/" +
                      DbName + ":INFORMIXSERVER=" + InformixServer + 
                      ";user=" + UserLogin + ";password=" + UserPassword; 
      }
    else {
      ConnectionURL = null;
     }
  }
  
  /**
  * Displays current values.
  * 
  * For security reason, password is displayed as ********.
  * @return current values as a string.
  */
  @Override
  public String toString() {
      return(Name + ", IP=" + IpAddress + ":" + portNumber + ", DBNAME=" + DbName +
             ", User=" + UserLogin + ", Password=********" +
             ", DriverName=" + DriverName + ", DriverClass=" + DriverClass +
             ", ConnectionURL=" + ConnectionURL + 
             ", [INFORMIXSERVER=" + InformixServer + "]");
  }

  /**
   * Main method to test DBServer class.
   * @param Args command line arguments.
   * First argument must be the type of Database server : dev/pre-prod/prod.
   * Second argument must be the name of the application properties file to load.
   */
  public static void main(String[] Args) {
    ApplicationProperties MyApplicationProperties;
    DBServer MyDBServer;

    if (Args.length != 2) {
      System.out.println("Usage : java DBServer server-type filename");
      System.exit(0);
    }

    try {
      MyApplicationProperties = new ApplicationProperties(Args[1]);
      MyDBServer = new DBServer(Args[0], MyApplicationProperties);
      System.out.println(MyDBServer);
      }
    catch (IOException | DBServerException MyException) {
      System.out.println("Problem while creating DBServer " + MyException);
      MyException.printStackTrace();
    }
  }
}
