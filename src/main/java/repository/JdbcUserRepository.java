package repository;

import entity.User;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcUserRepository implements UserRepository {

    private final DataSource dataSource;

    public JdbcUserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    @Transactional
    public User save(User user) {
        String sql = "INSERT INTO user(user_name) VALUES(?)";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, user.getName()); // user_name으로 변경
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                user.setId(rs.getLong(1)); // user_id로 변경
            } else {
                throw new SQLException("ID 조회 실패");
            }
            return user;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public Optional<User> findById(Long userId) {
        String sql = "SELECT * FROM user WHERE user_id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, userId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("user_id"));
                user.setName(rs.getString("user_name"));
                return Optional.of(user);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM user";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            List<User> users = new ArrayList<>();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("user_id"));
                user.setName(rs.getString("user_name"));
                users.add(user);
            }
            return users;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public Optional<User> findByName(String userName) {
        String sql = "SELECT * FROM user WHERE user_name = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userName);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("user_id"));
                user.setName(rs.getString("user_name"));
                return Optional.of(user);
            }
            return Optional.empty();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }

    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                close(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }
}



//public class JdbcUserRepository implements UserRepository {
//    private final DataSource dataSource;
//
//    public JdbcUserRepository(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
//    @Override
//    public User save(User user) {
//        // SQL 문
//        String sql = "insert into user(user_name) values(?)";
//
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;        // 결과를 가져옴
//
//        try {
//            // connection을 가져옴
//            conn = getConnection();
//
//            // sql을 가져오고 option을 줌
//            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//
//            // ? 랑 matching, member,getName()으로 이름 넣어줌
//            pstmt.setString(1, user.getUserName());
//
//            pstmt.executeUpdate();
//            rs = pstmt.getGeneratedKeys();      // 키 값을 꺼내줌
//
//            // 값이 존재할 시
//            if (rs.next()) {
//                // 값을 꺼내고 세팅해줌
//                user.setUserId(rs.getLong(1));
//            } else {
//                throw new SQLException("id 조회 실패");
//            }
//            return user;
//        } catch (Exception e) {
//            throw new IllegalStateException(e);
//        } finally {
//            // close(resource)
//            close(conn, pstmt, rs);
//        }
//    }
//
//    // 조회
//    @Override
//    public Optional<User> findById(Long user_id) {
//        String sql = "select * from user where user_id = ?";
//
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//
//        try {
//            conn = getConnection();
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setLong(1, user_id);
//            rs = pstmt.executeQuery();
//            if (rs.next()) {
//                User user = new User();
//                user.setUserId(rs.getLong("user_id"));
//                user.setUserName(rs.getString("user_name"));
//                return Optional.of(user);
//            } else {
//                return Optional.empty();
//            }
//        } catch (Exception e) {
//            throw new IllegalStateException(e);
//        } finally {
//            close(conn, pstmt, rs);
//        }
//    }
//
//    @Override
//    public List<User> findAll() {
//        String sql = "select * from user";
//
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//
//        try {
//            conn = getConnection();
//            pstmt = conn.prepareStatement(sql);
//            rs = pstmt.executeQuery();
//            List<User> users = new ArrayList<>();
//
//            while (rs.next()) {
//                User user = new User();
//                user.setUserId(rs.getLong("user_id"));
//                user.setUserName(rs.getString("user_name"));
//                users.add(user);
//            }
//            return users;
//        } catch (Exception e) {
//            throw new IllegalStateException(e);
//        } finally {
//            close(conn, pstmt, rs);
//        }
//    }
//
//    @Override
//    public Optional<User> findByName(String user_name) {
//        String sql = "select * from user where user_name = ?";
//
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//
//        try {
//            conn = getConnection();
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, user_name);
//            rs = pstmt.executeQuery();
//
//            if (rs.next()) {
//                User user = new User();
//                user.setUserId(rs.getLong("user_id"));
//                user.setUserName(rs.getString("user_name"));
//                return Optional.of(user);
//            }
//            return Optional.empty();
//        } catch (Exception e) {
//            throw new IllegalStateException(e);
//        } finally {
//            close(conn, pstmt, rs);
//        }
//    }
//
//    /**
//     * dataSource.getConnection(); 할 수도 있지만
//     * spring 프레임 워크를 사용해 데이터를 얻어오려면
//     * DataSourceUtils를 통해 connection을 획득을 해야함
//     * DB 트랜젝션 등이 걸릴 경우 -> connection을 똑같이 유지시켜야 하기에
//     */
//    private Connection getConnection() {
//        return DataSourceUtils.getConnection(dataSource);
//    }
//
//    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
//        try {
//            if (rs != null) {
//                rs.close();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        try {
//            if (pstmt != null) {
//                pstmt.close();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        try {
//            if (conn != null) {
//                close(conn);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void close(Connection conn) throws SQLException {
//        DataSourceUtils.releaseConnection(conn, dataSource);
//    }
//}
