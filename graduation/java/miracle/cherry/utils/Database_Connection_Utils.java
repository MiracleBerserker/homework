package miracle.cherry.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database_Connection_Utils {

    //url
    private static String url = "";
    //user
    private static String user = "";
    //password
    private static String password = "";
    //driverclass
    private static String driverclass = "";
    //thread
     private static ThreadLocal<Connection> con=new ThreadLocal<Connection>();
    static {

        Properties p = new Properties();
        try {
            //加载配置文件
            p.load(Database_Connection_Utils.class.getResourceAsStream("/database.properties"));
            url = p.getProperty("url");
            user = p.getProperty("user");
            password = p.getProperty("password");
            driverclass = p.getProperty("driverclass");
            try {
                Class.forName(driverclass);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.print("driverclass exeception");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private static Connection  getConnection() {

        if(con.get()==null){

            try {
                con.set(DriverManager.getConnection(url,user,password));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return con.get();
    }







    public static void main(String[] args) {
        System.out.print(Database_Connection_Utils.getConnection());
    }

}



