package pl.thewalkingcode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.thewalkingcode.model.Device;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Named
public class DeviceRepository  {

    @Inject
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<Device> findAll() {
        List<Device> list = null;
        Query query = entityManager.createQuery("SELECT d FROM Device d");
        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Device findBySerialNumber(String serialNumber) {
        List<Device> list = null;
        Query query = entityManager.createQuery("SELECT d FROM Device d WHERE d.serialNumber = :sn").setParameter("sn", serialNumber);
        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public Device merge(Device device) {
        return entityManager.merge(device);
    }

    public void create(Device device) {
        entityManager.persist(device);
    }

    public void delete(Long id) {
        Device device = entityManager.find(Device.class, id);
        if (device != null) {
            entityManager.remove(device);
        }
    }

}
