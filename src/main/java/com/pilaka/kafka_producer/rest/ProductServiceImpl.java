package com.pilaka.kafka_producer.rest;

import com.pilaka.core.rest.ProductCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;

    private Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public String createProduct(CreateProductRestModel createProductRestModel) throws ExecutionException, InterruptedException {
        String productId = UUID.randomUUID().toString();

        ProductCreatedEvent productCreatedEvent =
         new ProductCreatedEvent(productId, createProductRestModel.getTitle(), createProductRestModel.getPrice(), createProductRestModel.getQuantity());

      // CompletableFuture<SendResult<String, ProductCreatedEvent>> future = kafkaTemplate.send("product-created-event-topic", productId, productCreatedEvent);

//        this change will make the operation asynchronous
        SendResult<String, ProductCreatedEvent> result=
                kafkaTemplate.send("product-created-events-topic", productId, productCreatedEvent).get();
//        log.info("Partition: "+result.getRecordMetadata().partition());
//        log.info("Topic: "+result.getRecordMetadata().topic());
//        log.info("Offset: "+result.getRecordMetadata().offset());

//        result.whenComplete((result, error) -> {
//            if (error != null) {
//                log.error("Failed to send");
//
//            } else {
//                log.info("New product successfully created");
//            }
//            log.info("******* Returning product id");
//        });

        //makes the code synchronous
     //    future.join();
        return productId;
    }
}
