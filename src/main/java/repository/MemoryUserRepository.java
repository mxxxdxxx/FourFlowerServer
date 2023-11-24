package repository;

import entity.User;

import java.util.*;


public class MemoryUserRepository implements UserRepository {

    private static Map<Long, User> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public User save(User user) {
        user.setId(++sequence);
        store.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> findById(Long userId) {
        return Optional.ofNullable(store.get(userId));
    }

    @Override
    public Optional<User> findByName(String userName) {
        return store.values().stream()
                .filter(user -> user.getName().equals(userName))
                .findAny();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
        sequence = 0L;
    }

}



//    private static Map<Long, User> store = new HashMap<>();
//    private static long sequence = 0L;
//
//    @Override
//    public User save(User user) {
//        user.setUserId(++sequence);
//        store.put(user.getUserId(), user);
//        return user;
//    }
//
//    @Override
//    public Optional<User> findById(Long user_id) {
//        return Optional.ofNullable(store.get(user_id));
//    }
//
//    @Override
//    public Optional<User> findByName(String user_name) {
//        return store.values().stream()
//                .filter(user -> user.getUserName().equals(user_name))
//                .findAny();
//    }
//
//    @Override
//    public List<User> findAll() {
//        return new ArrayList<>(store.values());
//    }
//
//    public void clearStore() {
//        store.clear();
//        sequence = 0L; // clearStore 시에 sequence도 초기화
//    }
