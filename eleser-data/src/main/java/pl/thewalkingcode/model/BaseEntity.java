package pl.thewalkingcode.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String uuid = UUID.randomUUID().toString();

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof BaseEntity &&
                Objects.equals(uuid, ((BaseEntity) o).uuid);
    }

    public Long getId() {
        return id;
    }

}
