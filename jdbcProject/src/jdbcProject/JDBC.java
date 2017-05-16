package jdbcProject;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class JDBC {
	public static void main(String[] args) throws Exception {
		    
		Statement conn = GetJdbc.getPostgreConnection();
		String sql = "select codevalue, formalname from t_code where codekbn = '07' and status = '0'";
		ResultSet result = conn.executeQuery(sql);
		LinkedList<Map> list = new LinkedList<Map>();
		int i = 1;
		while (result.next()) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("codevalue",result.getString("codevalue"));
			map.put("formalname",result.getString("formalname"));
			System.out.println("加载地 "+i+"条数据");
			list.add(map);
			i++;
        }
		
		Connection connMysql = GetJdbc.getMysqlConnection();
		for(int j = 0 ; j < list.size() ; j++){
			Map<String, String> map = list.get(j);
			sql = "select uuid() as uuid";
			result = connMysql.createStatement().executeQuery(sql);
			while (result.next()) {
				map.put("uuid",result.getString("uuid"));
				System.out.println("获取uuid："+map.get("uuid"));
	        }
			sql = "UPDATE "
				+"user_info "
				+"SET "
				+"post_id = '"+map.get("uuid")+"' "
				+"WHERE "
				+"post_name = '"+map.get("formalname")+"' ";
			System.out.println(sql);
			PreparedStatement preparedStatement = connMysql.prepareStatement(sql);  
			preparedStatement.executeUpdate(sql);
			
			sql = "INSERT	INTO "
				+"framework_post "
				+"(post_id, post_name) "
				+"VALUE "
				+"('"+map.get("uuid")+"','"+map.get("formalname")+"') ";
			System.out.println(sql);
			preparedStatement = connMysql.prepareStatement(sql);  
			preparedStatement.executeUpdate(sql);
			System.out.println("提交第"+j+"条数据成功");
			System.out.println();
		}
	}
}
