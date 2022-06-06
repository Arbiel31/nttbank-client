package com.nttdata.nttbankclient.service.impl;

import com.nttdata.nttbankclient.model.PersonModel;
import com.nttdata.nttbankclient.repository.PersonRepository;
import com.nttdata.nttbankclient.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Override
    public Flux<PersonModel> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Mono<PersonModel> create(PersonModel person) {
        return personRepository.save(person);
    }

    @Override
    public Mono<PersonModel> update(PersonModel person) {
        return personRepository.save(person);
    }

    @Override
    public void deleteByDni(String dni) {
        personRepository.deleteByDni(dni);
    }

    @Override
    public Mono<PersonModel>  findByDni(String dni) {
        return personRepository.findByDni(dni);
    }
}
