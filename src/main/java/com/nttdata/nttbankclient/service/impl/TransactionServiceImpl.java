package com.nttdata.nttbankclient.service.impl;

import com.nttdata.nttbankclient.model.TransactionModel;
import com.nttdata.nttbankclient.repository.TransactionRepository;
import com.nttdata.nttbankclient.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public Flux<TransactionModel> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Mono<TransactionModel> create(TransactionModel transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Mono<TransactionModel> update(TransactionModel transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public void deleteById(String id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public Mono<TransactionModel> findById(String id) {
        return transactionRepository.findById(id);
    }
}
