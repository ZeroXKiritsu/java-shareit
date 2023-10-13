package ru.practicum.shareit.item;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "items")
public class Item {
    public static final int MAX_DESCRIPTION_LENGTH = 512;
    public static final String NAME_COLUMN_NAME = "name";
    public static final String ID_COLUMN_NAME = "item_id";
    public static final String OWNER_COLUMN_NAME = "owner";
    public static final String AVAILABLE_COLUMN_NAME = "available";
    public static final String DESCRIPTION_COLUMN_NAME = "description";

    @Id
    @Column(name = ID_COLUMN_NAME)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = NAME_COLUMN_NAME, nullable = false)
    private String name;
    @Column(name = DESCRIPTION_COLUMN_NAME, nullable = false, length = MAX_DESCRIPTION_LENGTH)
    private String description;
    @Column(name = AVAILABLE_COLUMN_NAME, nullable = false)
    private Boolean available;
    @Column(name = OWNER_COLUMN_NAME, nullable = false)
    private Long owner;
}
