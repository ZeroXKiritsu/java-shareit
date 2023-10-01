package ru.practicum.shareit.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.AlreadyExistsException;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<User> getAll() {
        log.debug("UserService: выполнено getAll.");
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException(User.class.toString(), id));
        log.debug("UserService: выполнено getById - {}.", user);
        return user;
    }

    @Override
    public User create(User user) {
        if (user.getId() != null && userRepository.isExists(user.getId())) {
            throw new AlreadyExistsException(User.class.toString(), user.getId());
        }
        if (userRepository.findAll().contains(user)) {
            throw new AlreadyExistsException(User.class.toString(), user.getEmail());
        }
        user = userRepository.create(user);
        log.debug("UserService: выполнено create - {}.", user);
        return user;
    }

    @Override
    public User update(User user, Long id) {
        if (!userRepository.isExists(id)) {
            throw new NotFoundException(User.class.toString(), id);
        }
        User oldUser = getById(id);
        Optional<User> emailUser = userRepository.findByEmail(user.getEmail());
        if (emailUser.isPresent() && !emailUser.get().getId().equals(id)) {
            throw new AlreadyExistsException(User.class.toString(), user.getEmail());
        }
        if (user.getName() != null) {
            oldUser.setName(user.getName());
        }
        if (user.getEmail() != null) {
            oldUser.setEmail(user.getEmail());
        }
        log.debug("UserService: выполнено update - {}.", user);
        return userRepository.update(id, oldUser);
    }

    @Override
    public void delete(Long id) {
        if (!userRepository.isExists(id)) {
            throw new NotFoundException(User.class.toString(), id);
        }
        userRepository.delete(id);
        log.debug("UserService: выполнено delete - ID {}.", id);
    }
}