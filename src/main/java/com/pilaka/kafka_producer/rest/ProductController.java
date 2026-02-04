package com.pilaka.kafka_producer.rest;

import com.pilaka.kafka_producer.errors.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;

@RestController
@RequestMapping(("/products"))
public class ProductController {

    private Logger log = LoggerFactory.getLogger(ProductController.class);
    ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody CreateProductRestModel product) throws Exception {
        try {
            productService.createProduct(product);
            log.info("Product created successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(product.getTitle());
        } catch (Exception e) {
            log.info("failed to create Product");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage((Timestamp) new Date(), e.getMessage(), "/producs"));
        }
    }
}
