package utils;

import java.io.*;
import java.util.*;

/**
 * ApplicationProperties is a class that derives from Properties class and
 * that get its values from a file whose name is passed as parameter. 
 * This file is supposed to be in project directory.
 * If an error is encountered, an IOException is thrown.
 * @version July 2015.
 * @author Thierry Baribaud.
 */
public class ApplicationProperties extends Properties {

  /**
   * @param FileName the name of the file containing application properties.
   * @throws java.io.IOException
   */
  public ApplicationProperties(String FileName) throws IOException {
    FileInputStream MyFileInputStream;

    System.out.println("Creating ApplicationProperties object ...");
 
    MyFileInputStream = new FileInputStream(FileName);
    System.out.println("Reading " + FileName + " ...");
    load(MyFileInputStream);
    MyFileInputStream.close();
  }

  /**
   * Main method to test ApplicationProperties class.
   * @param Args command line arguments.
   * First argument must be the name of the file to load.
   */
  public static void main(String[] Args) {
    ApplicationProperties MyApplicationProperties;
    Enumeration Names;
    String Name;

    if (Args.length != 1) {
      System.out.println("Usage : java ApplicationProperties filename");
      System.exit(0);
    }

    try {
      MyApplicationProperties = new ApplicationProperties(Args[0]);
      Names = MyApplicationProperties.propertyNames();

      while (Names.hasMoreElements()) {
        Name = Names.nextElement().toString();
        System.out.println(Name + " = " + MyApplicationProperties.getProperty(Name));
      }
    }
    catch (Exception MyException) {
      System.out.println("Problem while creating ApplicationProperties" + MyException);
      MyException.printStackTrace();
    }
  }
}
