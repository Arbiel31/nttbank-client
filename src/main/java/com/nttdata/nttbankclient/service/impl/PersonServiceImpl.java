package com.nttdata.nttbankclient.service.impl;

import com.nttdata.nttbankclient.exception.CustomException;
import com.nttdata.nttbankclient.model.PersonModel;
import com.nttdata.nttbankclient.repository.PersonRepository;
import com.nttdata.nttbankclient.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;


@Service
public class PersonServiceImpl implements PersonService {
    private final Logger LOGGER = LoggerFactory.getLogger(PersonServiceImpl.class);

    @Autowired
    PersonRepository personRepository;

    @Value("${dup.person.exception}")
    private String message;

    @Override
    public Flux<PersonModel> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Mono<PersonModel> create(PersonModel person) {
        person.setReg_date(new java.sql.Timestamp(new Date().getTime()));

        LOGGER.info("Verifying person duplicates");

        //Se verifica la no duplicidad del cliente a registrar
        return personRepository.findByDni(person.getDni())
                .flatMap(__ -> Mono.error(new CustomException(message)))
                .switchIfEmpty(Mono.defer(()->personRepository.save(person)))
                .cast(PersonModel.class);
    }

    @Override
    public Mono<PersonModel> update(PersonModel person) {
        return personRepository.save(person);
    }

    @Override
    public Mono<Void> deleteByDni(String dni) {
        return personRepository.deleteByDni(dni);
    }

    @Override
    public Mono<PersonModel>  findByDni(String dni) {
        return personRepository.findByDni(dni);
    }
}
