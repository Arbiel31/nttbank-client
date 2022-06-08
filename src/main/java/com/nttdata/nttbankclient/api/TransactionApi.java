package com.nttdata.nttbankclient.api;

import com.nttdata.nttbankclient.model.TransactionModel;
import com.nttdata.nttbankclient.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "transaction")
public class TransactionApi {

    private final Logger LOGGER = LoggerFactory.getLogger(TransactionApi.class);

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public Flux<TransactionModel> findAll() {
        LOGGER.info("Call to Transaction findAll method");
        return transactionService.findAll();
    }

    @PostMapping
    public Mono<TransactionModel> create(@RequestBody TransactionModel transaction){
        LOGGER.info("Call to Transaction create method");
        return transactionService.create(transaction);
    }

    @PutMapping
    public Mono<TransactionModel> update(@RequestBody TransactionModel transaction){
        LOGGER.info("Call to Transaction update method");
        return transactionService.update(transaction);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteById(@PathVariable("id") String id){
        LOGGER.info("Call to Transaction deleteById method");
        return transactionService.deleteById(id);
    }

    @GetMapping("/{id}")
    public Mono<TransactionModel> findById(@PathVariable("id") String id){
        LOGGER.info("Call to Transaction findById method");
        return transactionService.findById(id);
    }

    //Lista todas las operaciones del cliente según su DNI/RUC
    @GetMapping("/client/{doc}")
    public Flux<TransactionModel> findByClient(@PathVariable("doc") String doc){
        LOGGER.info("Call to Transaction findByClient method");
        return transactionService.findByClient(doc);
    }

    //Lista todas las operaciones realizadas en una cuenta/producto
    @GetMapping("/account/{number}")
    public Flux<TransactionModel> findByAccount(@PathVariable("number") String number){
        LOGGER.info("Call to Transaction findByAccount method");
        return transactionService.findByAccount(number);
    }
}
