package pl.thewalkingcode.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Role extends BaseEntity implements Serializable {

    @Column
    private String name;

    @Column
    private String description;

    public Role() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
