package com.nttdata.nttbankclient.api;

import com.nttdata.nttbankclient.model.BusinessModel;
import com.nttdata.nttbankclient.service.BusinessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "business")
public class BusinessApi {
    private final Logger LOGGER = LoggerFactory.getLogger(BusinessApi.class);

    @Autowired
    private BusinessService businessService;

    @GetMapping
    public Flux<BusinessModel> findAll() {
        LOGGER.info("Call to Business findAll method");
        return businessService.findAll();
    }

    @PostMapping
    public Mono<BusinessModel> create(@RequestBody BusinessModel business){
        LOGGER.info("Call to Business create method");
        return businessService.create(business);
    }

    @PutMapping
    public Mono<BusinessModel> update(@RequestBody BusinessModel business){
        LOGGER.info("Call to Business update method");
        return businessService.update(business);
    }

    @DeleteMapping("/{dni}")
    public Mono<Void> delete(@PathVariable("ruc") String ruc){
        LOGGER.info("Call to Business deleteByRuc method");
        return businessService.deleteByRuc(ruc);
    }

    @GetMapping("/{ruc}")
    public Mono<BusinessModel> findByRuc(@PathVariable("ruc") String ruc){
        LOGGER.info("Call to Business findByRuc method");
        return businessService.findByRuc(ruc);
    }
}
