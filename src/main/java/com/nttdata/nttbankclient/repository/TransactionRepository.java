package com.nttdata.nttbankclient.repository;

import com.nttdata.nttbankclient.model.TransactionModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TransactionRepository extends ReactiveMongoRepository<TransactionModel,String> {
}
