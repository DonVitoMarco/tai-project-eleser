package pl.thewalkingcode.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
public class Device extends BaseEntity implements Serializable {

    @Column
    private String name;

    @Column
    private Date warrantyDate;

    @OneToMany(mappedBy = "device")
    private List<Repair> repairList;

    public Device() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
