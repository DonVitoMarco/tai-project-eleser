package pl.thewalkingcode.service;

import pl.thewalkingcode.model.Device;
import pl.thewalkingcode.repository.DeviceRepository;

import javax.annotation.Resource;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Stateless
public class DeviceService {

    @Inject
    private DeviceRepository deviceRepository;

    public List<Device> findAll() {
        return deviceRepository.findAll();
    }

    public void save(Device device) {
        deviceRepository.create(device);
    }

}
