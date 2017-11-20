package pl.thewalkingcode.service;

import pl.thewalkingcode.repository.DeviceRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class DeviceService {

    @Inject
    private DeviceRepository deviceRepository;

}
