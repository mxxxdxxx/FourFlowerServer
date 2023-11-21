package service;

import entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class TestService {

    private final UserRepository userRepository;
    private final DataSource dataSource;

    public List<User> getUserList() {
        return userRepository.findAll();
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public void databaseLogic() {
        try (Connection connection = dataSource.getConnection()) {
            // 여기에서 데이터베이스와 관련된 로직 수행
            // 예: PreparedStatement를 사용하여 쿼리 실행
        } catch (SQLException e) {
            // SQLException 처리
            e.printStackTrace();
        }
    }
}
