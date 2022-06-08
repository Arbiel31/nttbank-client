package com.nttdata.nttbankclient.repository;

import com.nttdata.nttbankclient.model.TransactionModel;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface TransactionRepository extends ReactiveMongoRepository<TransactionModel,String> {

    @Query("{client: ?0}")
    Flux<TransactionModel> findByClient(String doc);

    @Query("{account: ?0}")
    Flux<TransactionModel> findByAccount(String number);
}
