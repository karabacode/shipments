package org.karabacode.shipments.repositories;


import org.karabacode.shipments.entities.ShipmentState;
import org.springframework.data.repository.CrudRepository;

public interface ShipmentStateRepository extends CrudRepository<ShipmentState, Long> {

}
