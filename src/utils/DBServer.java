package utils;

import java.io.IOException;

/*
 * Class that defines an Database Server.
 * Handle driverName and driverClass from 2015/10/22.
 * @author Thierry Baribaud
 * @version 1.1
 */
public class DBServer {

    /**
     * Database server name.
     */
    private String name;

    /**
     * Database server IP address.
     */
    private String ipAddress;

    /**
     * Database server port number.
     */
    private long portNumber;

    /**
     * Database name.
     */
    private String dbName;

    /**
     * User's login to access the database.
     */
    private String userLogin;

    /**
     * User's password.
     */
    private String userPassword;

    /**
     * INFORMIXSERVER value.
     */
    private String informixServer;

    /**
     * driverName value.
     */
    private String driverName;

    /**
     * driverClass value.
     */
    private String driverClass;

    /**
     * connectionURL value.
     */
    private String connectionURL;

    /**
     * DBServer first class builder.
     *
     * @param serverType type of server : dev/prod.
     * @param applicationProperties Application properties.
     * @throws DBServerException en cas d'erreur sur le serveur de base de
     * données.
     */
    public DBServer(String serverType, ApplicationProperties applicationProperties)
            throws DBServerException {
        this(serverType, "dbserver", applicationProperties);
    }

    /**
     * DBServer second class builder.
     *
     * @param ServerType type of server : dev/pre-prod/prod.
     * @param service service on server.
     * @param applicationProperties Application properties.
     * @throws DBServerException en cas d'erreur sur le serveur de base de
     * données.
     */
    public DBServer(String ServerType, String service, ApplicationProperties applicationProperties)
            throws DBServerException {
        String Value;

        Value = applicationProperties.getProperty(ServerType + "." + service + ".name");
        if (Value != null) {
            setName(Value);
        } else {
            throw new DBServerException("Database name undefined");
        }

        Value = applicationProperties.getProperty(ServerType + "." + service + ".ip");
        if (Value != null) {
            setIpAddress(Value);
        } else {
            throw new DBServerException("Database IP address undefined");
        }

        Value = applicationProperties.getProperty(ServerType + "." + service + ".port");
        if (Value != null) {
            try {
                setPortNumber(Long.parseLong(Value));
            } catch (Exception MyException) {
                throw new DBServerException("Database port number must be an integer");
            }
        } else {
            throw new DBServerException("Database port number undefined");
        }

        Value = applicationProperties.getProperty(ServerType + "." + service + ".dbname");
        if (Value != null) {
            setDbName(Value);
        } else {
            throw new DBServerException("Database name undefined");
        }

        Value = applicationProperties.getProperty(ServerType + "." + service + ".login");
        if (Value != null) {
            setUserLogin(Value);
        } else {
            throw new DBServerException("User's login undefined");
        }

        Value = applicationProperties.getProperty(ServerType + "." + service + ".passwd");
        if (Value != null) {
            setUserPassword(Value);
        } else {
            throw new DBServerException("User's password undefined");
        }

        Value = applicationProperties.getProperty(ServerType + "." + service + ".drivername");
        if (Value != null) {
            setDriverName(Value);
        } else {
            throw new DBServerException("Driver name undefined");
        }

        Value = applicationProperties.getProperty(ServerType + "." + service + ".driverclass");
        if (Value != null) {
            setDriverClass(Value);
        } else {
            throw new DBServerException("Driver class undefined");
        }

        Value = applicationProperties.getProperty(ServerType + "." + service + ".informixserver");
        if (Value != null) {
            setInformixServer(Value);
        } else {
            if (driverName.equals("Informix")) {
                throw new DBServerException("INFORMIXSERVER undefined");
            }
        }
        setConnectionURL();
    }

    /**
     * Get Database server name
     *
     * @return Database server name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get database server's IP address
     *
     * @return Database server's IP address.
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * Get Database server's port number.
     *
     * @return Database server's port number.
     */
    public long getPortNumber() {
        return portNumber;
    }

    /**
     * Get database server's name.
     *
     * @return Database server's name.
     */
    public String getDbName() {
        return dbName;
    }

    /**
     * Get user's login.
     *
     * @return user's login.
     */
    public String getUserLogin() {
        return userLogin;
    }

    /**
     * Get user's password.
     *
     * @return user's password.
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * Get INFORMIXSERVER value.
     *
     * @return INFORMIXSERVER value.
     */
    public String getInformixServer() {
        return informixServer;
    }

    /**
     * Get driverName value.
     *
     * @return driverName value.
     */
    public String getDriverName() {
        return driverName;
    }

    /**
     * Get driverClass value.
     *
     * @return driverClass value.
     */
    public String getDriverClass() {
        return driverClass;
    }

    /**
     * Get connectionURL value.
     *
     * @return connectionURL value.
     */
    public String getConnectionURL() {
        return connectionURL;
    }

    /**
     * Set database server's name
     *
     * @param name define Database server's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set database server's IP address IPV4
     *
     * @param ipAddress define Database server's IP address.
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * Set database server's port number.
     *
     * @param portNumber define Database server's port number.
     */
    public void setPortNumber(long portNumber) {
        this.portNumber = portNumber;
    }

    /**
     * Set database server's name.
     *
     * @param dbName define Database server's name.
     */
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    /**
     * Set user's login.
     *
     * @param userLogin define user's login.
     */
    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    /**
     * Set user's password.
     *
     * @param userPassword define user's password.
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * Set INFORMIXSERVER value.
     *
     * @param informixServer define INFORMIXSERVER value.
     */
    public void setInformixServer(String informixServer) {
        this.informixServer = informixServer;
    }

    /**
     * Set driverName value.
     *
     * @param driverName define driverName value.
     */
    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    /**
     * Set driverClass value.
     *
     * @param driverClass define driverClass value.
     */
    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    /**
     * Set connectionURL value.
     */
    public void setConnectionURL() {

        if (driverName.equals("MySQL")) {
            connectionURL = "jdbc:mysql://" + ipAddress + ":" + portNumber + "/"
                    + dbName + "?user=" + userLogin + "&password=" + userPassword;
        } else if (driverName.equals("Informix")) {
            connectionURL = "jdbc:informix-sqli:" + ipAddress + ":" + portNumber + "/"
                    + dbName + ":INFORMIXSERVER=" + informixServer
                    + ";user=" + userLogin + ";password=" + userPassword;
        } else {
            connectionURL = null;
        }
    }

    /**
     * Displays current values.
     *
     * For security reason, password is displayed as ********.
     *
     * @return current values as a string.
     */
    @Override
    public String toString() {
        return (name + ", IP=" + ipAddress + ":" + portNumber + ", DBNAME=" + dbName
                + ", User=" + userLogin + ", Password=********"
                + ", DriverName=" + driverName + ", DriverClass=" + driverClass
                + ", ConnectionURL=" + connectionURL
                + ", [INFORMIXSERVER=" + informixServer + "]");
    }

    /**
     * Main method to test DBServer class.
     *
     * @param Args command line arguments. First argument must be the type of
     * Database server : dev/pre-prod/prod. Second argument must be the name of
     * the application properties file to load.
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
        } catch (IOException | DBServerException MyException) {
            System.out.println("Problem while creating DBServer " + MyException);
            MyException.printStackTrace();
        }
    }
}
