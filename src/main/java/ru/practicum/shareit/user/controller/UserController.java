package ru.practicum.shareit.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.service.UserService;
import ru.practicum.shareit.user.validator.Create;
import ru.practicum.shareit.user.validator.Update;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserDto> getAll() {
        log.debug("UserController: выпонлено getAll.");
        return UserMapper.toUserDtoList(userService.getAll());
    }

    @GetMapping("/{userId}")
    public UserDto getById(@PathVariable Long userId) {
        log.debug("UserController: выпонлено getById - {}.", userId);
        return UserMapper.toUserDto(userService.getById(userId));
    }

    @PostMapping
    public UserDto create(@Validated(Create.class) @RequestBody UserDto userDto) {
        log.debug("UserController: выпонлено create - {}.", userDto);
        User user = UserMapper.toUser(userDto);
        return UserMapper.toUserDto(userService.create(user));
    }

    @PatchMapping("/{userId}")
    public UserDto update(@PathVariable Long userId, @Validated(Update.class) @RequestBody UserDto userDto) {
        log.debug("UserController: выпонлено update - {}.", userDto);
        User user = UserMapper.toUser(userDto);
        return UserMapper.toUserDto(userService.update(user, userId));
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable Long userId) {
        log.debug("UserController: выпонлено delete - {}.", userId);
        userService.delete(userId);
    }
}