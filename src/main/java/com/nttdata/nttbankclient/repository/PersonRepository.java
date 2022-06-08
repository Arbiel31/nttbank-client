package com.nttdata.nttbankclient.repository;

import com.nttdata.nttbankclient.model.PersonModel;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

public interface PersonRepository extends ReactiveMongoRepository<PersonModel,String> {
    Mono<Void> deleteByDni(String dni);

    @Query("{dni: ?0}")
    Mono<PersonModel> findByDni(String dni);
}

