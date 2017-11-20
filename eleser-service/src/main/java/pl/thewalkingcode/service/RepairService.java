package pl.thewalkingcode.service;

import pl.thewalkingcode.repository.RepairRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class RepairService {

    @Inject
    private RepairRepository repairRepository;

}
