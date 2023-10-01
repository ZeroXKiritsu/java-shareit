package ru.practicum.shareit.user.repository;

import org.springframework.stereotype.Repository;

import ru.practicum.shareit.user.model.User;

import java.util.*;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final Map<Long, User> users = new HashMap<>();
    private Long id = 1L;

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public Optional<User> findById(Long id) {
        return users.values().stream()
                .filter(user -> id.equals(user.getId()))
                .findFirst();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return users.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public User create(User user) {
        if (user.getId() == null) {
            user.setId(id++);
        }
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public User update(Long id, User user) {
        users.replace(id, user);
        user.setId(id);
        return user;
    }

    @Override
    public void delete(Long id) {
        users.remove(id);
    }

    @Override
    public boolean isExists(Long id) {
        return users.containsKey(id);
    }
}