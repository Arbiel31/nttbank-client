package com.nttdata.nttbankclient.api;

import com.nttdata.nttbankclient.model.BusinessModel;
import com.nttdata.nttbankclient.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "business")
public class BusinessApi {

    @Autowired
    private BusinessService businessService;

    @GetMapping
    public Flux<BusinessModel> findAll() {
        return businessService.findAll();
    }

    @PostMapping
    public Mono<BusinessModel> create(@RequestBody BusinessModel business){
        return businessService.create(business);
    }

    @PutMapping
    public Mono<BusinessModel> update(@RequestBody BusinessModel business){
        return businessService.update(business);
    }

    @DeleteMapping("/{dni}")
    public ResponseEntity<Void> delete(@PathVariable("ruc") String ruc){
        businessService.deleteByRuc(ruc);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{dni}")
    public Mono<BusinessModel> findByDni(@PathVariable("ruc") String ruc){
        return businessService.findByRuc(ruc);
    }
}
