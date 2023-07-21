package ro.msg.learning.shop.domain;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.util.UUID;

@Data
@MappedSuperclass
public class EntityBase {
    @Id
    @GeneratedValue
    private UUID id;
}
