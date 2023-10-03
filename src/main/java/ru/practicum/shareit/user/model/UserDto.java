package ru.practicum.shareit.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.practicum.shareit.validation.Create;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserDto {
    private final Long id;
    @NotNull(groups = {Create.class})
    private final String name;
    @Email(groups = {Create.class})
    @NotNull(groups = {Create.class})
    private final String email;
}