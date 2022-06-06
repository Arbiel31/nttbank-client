package com.nttdata.nttbankclient.api;

import com.nttdata.nttbankclient.model.PersonModel;
import com.nttdata.nttbankclient.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "person")
public class PersonApi {
    @Autowired
    private PersonService personService;

    @GetMapping
    public Flux<PersonModel> findAll() {
        return personService.findAll();
    }

    @PostMapping
    public Mono<PersonModel> create(@RequestBody PersonModel person){
        return personService.create(person);
    }

    @PutMapping
    public Mono<PersonModel> update(@RequestBody PersonModel person){
        return personService.update(person);
    }

    @DeleteMapping("/{dni}")
    public ResponseEntity<Void> delete(@PathVariable("dni") String dni){
        personService.deleteByDni(dni);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{dni}")
    public Mono<PersonModel> findByDni(@PathVariable("dni") String dni){
        return personService.findByDni(dni);
    }
}
