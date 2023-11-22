package service;

import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DatabaseConnection {
    private final DataSource dataSource;

    public DatabaseConnection(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * public Connection getConnection() throws SQLException {
     *         return dataSource.getConnection();
     * }
     */

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        // 드라이버 로드
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 데이터베이스 연결 정보 설정
        String url = "jdbc:mysql://127.0.0.1:3306/fourflowersdb?useSSL=true&requireSSL=true";
        String username = "root";
        String password = "root";

        // 연결 생성 및 반환
        return DriverManager.getConnection(url, username, password);
    }
}
