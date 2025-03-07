package thu_model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class thu_DBConnection {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/hieuthuoc_thu";
    private static final String JDBC_USER = "root";  // Thay bằng user của MySQL
    private static final String JDBC_PASSWORD = "";  // Nếu có mật khẩu thì nhập vào đây

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver"); // Nạp driver MySQL
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    public static void main(String[] args) {
        try {
            Connection conn = thu_DBConnection.getConnection();
            if (conn != null) {
                System.out.println("Kết nối đến MySQL thành công!");
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
