package com.nttdata.nttbankclient.api;

import com.nttdata.nttbankclient.model.BusinessModel;
import com.nttdata.nttbankclient.service.BusinessService;
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
@RequestMapping(value = "business")
public class BusinessApi {
  private final Logger logger = LoggerFactory.getLogger(BusinessApi.class);

  @Autowired
  private BusinessService businessService;

  @GetMapping
  public Flux<BusinessModel> findAll() {
    logger.info("Call to Business findAll method");
    return businessService.findAll();
  }

  @PostMapping
  public Mono<BusinessModel> create(@RequestBody BusinessModel business) {
    logger.info("Call to Business create method");
    return businessService.create(business);
  }

  @PutMapping
  public Mono<BusinessModel> update(@RequestBody BusinessModel business) {
    logger.info("Call to Business update method");
    return businessService.update(business);
  }

  @DeleteMapping("/{ruc}")
  public Mono<Void> delete(@PathVariable("ruc") String ruc) {
    logger.info("Call to Business deleteByRuc method");
    return businessService.deleteByRuc(ruc);
  }

  @GetMapping("/{ruc}")
  public Mono<BusinessModel> findByRuc(@PathVariable("ruc") String ruc) {
    logger.info("Call to Business findByRuc method");
    return businessService.findByRuc(ruc);
  }
}
