package pl.thewalkingcode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.thewalkingcode.model.Device;

public interface DeviceRepository extends JpaRepository<Device, Long> {

    public Device findBySerialNumber(String serialNumber);

}
