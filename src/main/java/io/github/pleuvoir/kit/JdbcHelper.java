package io.github.pleuvoir.kit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class JdbcHelper {

	private static Logger logger = LoggerFactory.getLogger(JdbcHelper.class);

	private JdbcHelper() {}
	

	private static class JdbcHelperHolder {

		private static Connection conn;

		static {
			init();
		}

		private static void init() {
			try {
				conn = DriverManager.getConnection(Constant.DB_URL, Constant.DB_USER, Constant.DB_PASSWORD);
			} catch (SQLException e) {
				logger.error("exception：{}", e);
			}
		}
	}

	public static Connection getConnection() {
		return JdbcHelperHolder.conn;
	}

	
	public static List<Map<String, Object>> executeSql(String sql) {
		PreparedStatement ps = null;
		ResultSet         rs = null;
		Connection 	      conn = getConnection();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
		   //获得结果集中列的信息
            ResultSetMetaData meta = rs.getMetaData();
            //获得结果集的列的数量
            int column = meta.getColumnCount();
            //创建List容器
            List<Map<String,Object>> rstList = new ArrayList<Map<String,Object>>();
            //处理结果
            while(rs.next()){
                //创建Map容器存取每一列对应的值
				Map<String, Object> m = new HashMap<String, Object>();
				for (int i = 1; i <= column; i++) {
					m.put(meta.getColumnName(i), rs.getObject(i));
				}
                //将Map容器放入List容器中
                rstList.add(m);
            }
            return rstList;
		} catch (SQLException e) {
			logger.error("exception：{}", e);
		} 
		return null;
	}

}
