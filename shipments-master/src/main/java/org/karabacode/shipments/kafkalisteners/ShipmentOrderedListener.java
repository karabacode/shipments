package org.karabacode.shipments.kafkalisteners;

import org.karabacode.shipments.model.ShipmentModel;
import org.karabacode.shipments.services.ShipmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ShipmentOrderedListener {

    @Autowired
    private ShipmentsService shipmentsService;

    @KafkaListener(topics = "${shipment.topic.name}", containerFactory = "shipmentModelKafkaListenerContainerFactory")
    public void greetingListener(ShipmentModel shipmentModel) {
        System.out.println("Recibido pedido: " + shipmentModel.toString());
        shipmentsService.orderShipment(shipmentModel);
        System.out.println("FIN Recibido pedido: ");


    }

}
