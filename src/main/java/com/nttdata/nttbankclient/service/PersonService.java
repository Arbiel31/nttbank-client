package com.nttdata.nttbankclient.service;

import com.nttdata.nttbankclient.model.PersonModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface PersonService {
    Flux<PersonModel> findAll();

    Mono<PersonModel> create(PersonModel person);

    Mono<PersonModel> update(PersonModel person);

    Mono<Void> deleteByDni(String dni);

    Mono<PersonModel>  findByDni(String dni);
}
