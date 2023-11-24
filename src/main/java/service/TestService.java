package service;

import entity.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TestService {

    private final UserRepository userRepository;
    // private final DataSource dataSource; // 더 이상 필요하지 않음

    public List<User> getUserList() {
        return userRepository.findAll();
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    // Methods from MemberService
    public Long join(User user) {
        validateDuplicateMember(user);
        userRepository.save(user);
        return user.getId();
    }

    public List<User> findMembers() {
        return userRepository.findAll();
    }

    public Optional<User> findOne(Long memberId) {
        return userRepository.findById(memberId);
    }

    // Additional method for finding a member by name
    public Optional<User> findMemberByName(String name) {
        return userRepository.findByName(name);
    }

    // Extracted method for validating duplicate members
    private void validateDuplicateMember(User user) {
        userRepository.findByName(user.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("Member already exists.");
                });
    }

    // Spring Data JPA를 사용하므로 직접적인 데이터베이스 연결 및 쿼리 수행 로직은 주석처리
//    public void databaseLogic() {
//        try (Connection connection = dataSource.getConnection()) {
//            // 여기에서 데이터베이스와 관련된 로직 수행
//            // 예: PreparedStatement를 사용하여 쿼리 실행
//        } catch (SQLException e) {
//            // SQLException 처리
//            e.printStackTrace();
//        }
//    }
}
