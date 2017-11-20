package pl.thewalkingcode.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.thewalkingcode.model.Repair;

public interface RepairRepository extends JpaRepository<Repair, Long> {
}
