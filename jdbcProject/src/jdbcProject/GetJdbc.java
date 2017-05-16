package jdbcProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class GetJdbc {
	//加载Mysql连接
	
	public static void main(String[] args) throws Exception {
		getPostgreConnection();
	}
	public static Connection getMysqlConnection() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");

        String url = "jdbc:mysql://127.0.0.1:3306/oabase";
        String username = "root";
        String password = "root";

        // 创建与MySQL数据库的连接类的实例
        Connection conn = DriverManager.getConnection(url, username, password);
        
        return conn;
	}
	
	public static Statement getPostgreConnection()  throws Exception{
		Class.forName("org.postgresql.Driver");

        String url = "jdbc:postgresql://127.0.0.1:5432/dmHr";
        String username = "postgres";
        String password = "Md2017";

        // 创建与MySQL数据库的连接类的实例
        Connection conn = DriverManager.getConnection(url, username, password);
        
        return conn.createStatement();
	}
}
