package com.pilaka.kafka_producer.rest;

public interface ProductService {
    String createProduct(CreateProductRestModel model) throws Exception;
}
