package ru.practicum.shareit.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class RequestDto {
    private Long id;
    private String description;
    private Long requestor;
    private LocalDateTime created;
}
