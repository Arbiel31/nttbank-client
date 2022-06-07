package com.nttdata.nttbankclient.service;

import com.nttdata.nttbankclient.model.TransactionModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionService {
    Flux<TransactionModel> findAll();

    Mono<TransactionModel> create(TransactionModel transaction);

    Mono<TransactionModel> update(TransactionModel transaction);

    void deleteById(String id);

    Mono<TransactionModel>  findById(String id);
}