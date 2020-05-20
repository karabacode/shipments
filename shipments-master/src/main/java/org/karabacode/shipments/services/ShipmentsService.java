package org.karabacode.shipments.services;


import org.karabacode.shipments.model.ShipmentModel;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public interface ShipmentsService {


    @Transactional
    ShipmentModel orderShipment(ShipmentModel shipmentRequest);
}
