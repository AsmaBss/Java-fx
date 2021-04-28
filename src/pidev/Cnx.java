package pidev;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Cnx {
    private String url = "jdbc:mysql://localhost:3306/pidev";
    private String user = "root";
    private String password = "";
    private Connection cnx;
    public static Cnx ct;

    private Cnx(){
        try {
            cnx = DriverManager.getConnection(url,user, password);
            System.out.println("Connexion établie");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Cnx getinstance(){
        if(ct == null){
            ct = new Cnx();
        }
        return ct;
    }

    public Connection getCnx() {
        return cnx;
    }

    
}
