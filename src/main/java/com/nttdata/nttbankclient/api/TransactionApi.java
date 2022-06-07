package com.nttdata.nttbankclient.api;

import com.nttdata.nttbankclient.model.TransactionModel;
import com.nttdata.nttbankclient.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "transaction")
public class TransactionApi {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public Flux<TransactionModel> findAll() {
        return transactionService.findAll();
    }

    @PostMapping
    public Mono<TransactionModel> create(@RequestBody TransactionModel transaction){
        return transactionService.create(transaction);
    }

    @PutMapping
    public Mono<TransactionModel> update(@RequestBody TransactionModel transaction){
        return transactionService.update(transaction);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") String id){
        transactionService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public Mono<TransactionModel> findByDni(@PathVariable("id") String id){
        return transactionService.findById(id);
    }
}
