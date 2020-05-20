package org.karabacode.shipments.repositories;


import org.karabacode.shipments.entities.Shipment;
import org.springframework.data.repository.CrudRepository;

public interface ShipmentRepository extends CrudRepository<Shipment, Long> {

}