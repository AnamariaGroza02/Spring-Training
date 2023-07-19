package ro.msg.learning.shop.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@MappedSuperclass
public class EntityBase {
    @Id
    private UUID id;
}
