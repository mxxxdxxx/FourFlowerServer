package repository;

import entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class JdbcTemplateUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public User save(User user) {
        String sql = "INSERT INTO user(user_name) VALUES(?)";

        jdbcTemplate.update(sql, user.getName());

        return user;
    }

    @Override
    public Optional<User> findById(Long userId) {
        String sql = "SELECT * FROM user WHERE user_id = ?";

        List<User> result = jdbcTemplate.query(sql, userRowMapper(), userId);

        return result.stream().findAny();
    }

    @Override
    public Optional<User> findByName(String userName) {
        String sql = "SELECT * FROM user WHERE user_name = ?";

        List<User> result = jdbcTemplate.query(sql, userRowMapper(), userName);

        return result.stream().findAny();
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM user";

        return jdbcTemplate.query(sql, userRowMapper());
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getLong("user_id"));     // changed from user_id to id
            user.setName(rs.getString("user_name")); // changed from user_name to name
            return user;
        };
    }
}