package com.nttdata.nttbankclient.service.impl;

import com.nttdata.nttbankclient.exception.CustomException;
import com.nttdata.nttbankclient.model.TransactionModel;
import com.nttdata.nttbankclient.repository.BusinessRepository;
import com.nttdata.nttbankclient.repository.PersonRepository;
import com.nttdata.nttbankclient.repository.TransactionRepository;
import com.nttdata.nttbankclient.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    BusinessRepository businessRepository;

    @Value("${type.transaction.exception}")
    private String message;

    @Value("${balance.transaction.exception}")
    private String message_balance;

    @Override
    public Flux<TransactionModel> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Mono<TransactionModel> create(TransactionModel transaction) {
        float balance=0;
        LOGGER.info("Calculating balance");
        switch(transaction.getType()) {
            case "1":
                balance=transaction.getFunds()-transaction.getAmount();
                LOGGER.info("Withdraw, calculating balance");
                break;
            case "2":
                balance=transaction.getFunds()+transaction.getAmount();
                LOGGER.info("Deposit, calculating balance");
                break;
            default:
                LOGGER.error(message);
                throw new CustomException(message);
        }

        if(balance<0){
            LOGGER.error(message_balance);
            throw new CustomException(message_balance);
        }

        transaction.setBalance(balance);
        return transactionRepository.save(transaction);
    }

    @Override
    public Mono<TransactionModel> update(TransactionModel transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return transactionRepository.deleteById(id);
    }

    @Override
    public Mono<TransactionModel> findById(String id) {
        return transactionRepository.findById(id);
    }

    @Override
    public Flux<TransactionModel> findByClient(String doc) {
        return transactionRepository.findByClient(doc);
    }

    @Override
    public Flux<TransactionModel> findByAccount(String number) {
        return transactionRepository.findByAccount(number);
    }
}
