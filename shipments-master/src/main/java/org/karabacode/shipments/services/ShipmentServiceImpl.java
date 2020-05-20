package org.karabacode.shipments.services;


import org.karabacode.shipments.entities.Product;
import org.karabacode.shipments.entities.Shipment;
import org.karabacode.shipments.entities.ShipmentState;
import org.karabacode.shipments.model.ShipmentModel;
import org.karabacode.shipments.repositories.ProductRepository;
import org.karabacode.shipments.repositories.ShipmentRepository;
import org.karabacode.shipments.repositories.ShipmentStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
public class ShipmentServiceImpl implements ShipmentsService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private ShipmentStateRepository shipmentStateRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Value("${shipmentcreated.topic.name}")
    private String shipmentCreatedTopicName;

    @Override
    @Transactional
    public ShipmentModel orderShipment(ShipmentModel shipmentModel) {
        Optional<ShipmentState> oss =  shipmentStateRepository.findById(ShipmentState.States.ordered.label);
        if (oss.isPresent()){
            System.out.println("Estado encontrado: " + ShipmentState.States.ordered.label + "::" + oss.get().getName());
            ShipmentState state = oss.get();
            shipmentModel.getProducts().forEach(product -> {
                Optional<Product> op = productRepository.findById(product.getProductId());
                if (op.isPresent()) {
                    System.out.println("Producto encontrado: " + product.getProductId() + "::" + op.get().getId());
                    Shipment shipment = new Shipment();
                    shipment.setShipmentState(state);
                    shipment.setProduct(op.get());
                    shipmentRepository.save(shipment);
                    kafkaTemplate.send(shipmentCreatedTopicName,shipmentModel);
                }
            });
        }
        return shipmentModel;
    }
}
