package ru.practicum.shareit.item.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.practicum.shareit.validation.Create;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class ItemDto {
    private final Long id;
    @NotBlank(groups = Create.class)
    private final String name;
    @NotBlank(groups = Create.class)
    private final String description;
    @NotNull(groups = Create.class)
    private final Boolean available;
}