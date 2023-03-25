package hw01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySqlDAO {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        String driverName="com.mysql.cj.jdbc.Driver";
        String url="jdbc:mysql://localhost:3306/demo01";
        String userName="root";
        String pw="root";
        Class.forName(driverName);
        Connection con= DriverManager.getConnection(url,userName,pw);
        return  con;
    }
    public static PreparedStatement  preparedStatement(String sql) throws SQLException, ClassNotFoundException {
        return getConnection().prepareStatement(sql);
    }
}
