package org.karabacode.shipmentapi.kafkalistener;

import org.karabacode.shipmentapi.model.ShipmentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ShipmentCreatedListener {

    @Value("${shipmentcreated.topic.name}")
    private String shipmentCreatedTopicName;


    @Autowired
    private KafkaTemplate kafkaTemplate;

    @KafkaListener(topics = "${shipmentcreated.topic.name}", containerFactory = "shipmentModelKafkaListenerContainerFactory")
    public void greetingListener(ShipmentModel shipmentModel) {
        System.out.println("Creado pedido: " + shipmentModel.getAddress());
        System.out.println("FIN Recibido creado: ");


    }
}
