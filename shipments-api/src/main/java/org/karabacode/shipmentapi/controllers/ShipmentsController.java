package org.karabacode.shipmentapi.controllers;


import org.karabacode.shipmentapi.model.ShipmentModel;
import org.karabacode.shipmentapi.request.ShipmentCreateRequest;
import org.karabacode.shipmentapi.responses.GenericResponse;
import org.karabacode.shipmentapi.responses.ShipmentCreateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShipmentsController {


    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Value("${shipment.topic.name}")
    private String shipmentTopicName;


    @PostMapping(value = "/shipment")
    public ResponseEntity<GenericResponse> createShipment(@RequestBody ShipmentCreateRequest shipment)  {
        ShipmentCreateResponse shipmentResponse = new ShipmentCreateResponse();
        GenericResponse<ShipmentCreateResponse> response = new GenericResponse<>();
        ShipmentModel sm = new ShipmentModel();
        sm.setAddress(shipment.getAddress());
        sm.setProducts(shipment.getOrders());

        kafkaTemplate.send(shipmentTopicName,sm);
        response.setBody(shipmentResponse);
        return ResponseEntity.ok(response);
    }
}
