package service;

import entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class TestService {

    private UserRepository userRepository;

    public List<User> getUserList() {
        return userRepository.findAll();
    }

    public String getUser(Long userId) {
        return "{\n" +
                "\t\t\"id\": 1,\n" +
                "\t\t\"name\": \"김길동\",\n" +
                "\t}";

    }
}