package com.nttdata.nttbankclient.api;

import com.nttdata.nttbankclient.model.PersonModel;
import com.nttdata.nttbankclient.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Api.
 *
 * @author Percy
 */
@RestController
@RequestMapping(value = "person")
public class PersonApi {

  private final Logger logger = LoggerFactory.getLogger(PersonApi.class);
  @Autowired
  private PersonService personService;

  @GetMapping
  public Flux<PersonModel> findAll() {
    logger.info("Call to Person findAll method");
    return personService.findAll();
  }

  @PostMapping
  public Mono<PersonModel> create(@RequestBody PersonModel person) {
    logger.info("Call to Person create method");
    return personService.create(person);
  }

  @PutMapping
  public Mono<PersonModel> update(@RequestBody PersonModel person) {
    logger.info("Call to Person update method");
    return personService.update(person);
  }

  @DeleteMapping("/{dni}")
  public Mono<Void> delete(@PathVariable("dni") String dni) {
    logger.info("Call to Person deleteByDni method");
    return personService.deleteByDni(dni);
  }

  @GetMapping("/{dni}")
  public Mono<PersonModel> findByDni(@PathVariable("dni") String dni) {
    logger.info("Call to Person findByDni method");
    return personService.findByDni(dni);
  }
}
