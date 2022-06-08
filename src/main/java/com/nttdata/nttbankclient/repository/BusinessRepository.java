package com.nttdata.nttbankclient.repository;

import com.nttdata.nttbankclient.model.BusinessModel;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface BusinessRepository extends ReactiveMongoRepository<BusinessModel,String> {
    Mono<Void> deleteByRuc(String ruc);

    @Query("{ruc: ?0}")
    Mono<BusinessModel> findByRuc(String ruc);
}
