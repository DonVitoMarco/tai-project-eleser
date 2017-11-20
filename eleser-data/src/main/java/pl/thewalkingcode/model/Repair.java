package pl.thewalkingcode.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.sql.Date;

@Entity
public class Repair extends BaseEntity implements Serializable {

    @Column
    private String description;

    @Column
    private Date date;

    @ManyToOne
    private Device device;

    public Repair() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

}
