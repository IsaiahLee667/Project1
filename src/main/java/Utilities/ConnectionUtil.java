package Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    public static Connection createConnection(){

        try {
            //"jdbc:postgresql://ranieri-db.cwrqnnecy1to.us-east-2.rds.amazonaws.com/project1?user=postgres&password=Saintanger12!"
            Connection conn = DriverManager.getConnection("jdbc:postgresql://revature-awsdb.cww3rzwfr5hv.us-east-2.rds.amazonaws.com/project1?user=postgres&password=Saintanger12!");
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}
