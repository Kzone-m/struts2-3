package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector{
	
    private String driverName;
    private String url;
    private String dataBaseName;
    private String user;
    private String password;

    public DBConnector(String driverName, String url, String dataBaseName, String user, String password){
        this.driverName = driverName;
        this.url = url;
        checkUrl();
        this.dataBaseName = dataBaseName;
        this.user = user;
        this.password = password;
    }

    private void checkUrl(){
        String tmp[] = new String[url.length()];
        for(int i = 0; i < tmp.length; i++)
            tmp[i] = url.substring(i, i + 1);

        if(!tmp[4].equals(":") || !tmp[10].equals(":") || !tmp[11].equals("/") || !tmp[12].equals("/") || !tmp[tmp.length - 1].equals("/"))
            url = "jdbc:mysql://localhost/";
    }

    public Connection getConnection(){
        Connection conn = null;
        try{
            Class.forName(driverName);
            conn = DriverManager.getConnection((new StringBuilder(String.valueOf(url))).append(dataBaseName).toString(), user, password);
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return conn;
    }

    public void setDataBaseName(String dataBaseName){
        this.dataBaseName = dataBaseName;
    }
}

